package by.issoft.sample.domain;

import by.issoft.sample.persistence.LiftStatistics;
import by.issoft.sample.persistence.StatisticItem;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@RequiredArgsConstructor(staticName="of")
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"passengers"})
public class Lift {

    private final String id;

    private final int capacity;

    private final int speed;

    private final int doorOpenCloseSpeed;

    @Setter
    private volatile int startPosition = 1;

    private volatile int floorNumber = 1;

    @Setter
    private volatile int aimFloorNumber = 1;

    private volatile boolean doorsIsClosed = true;

    @Setter
    private volatile Direction direction = Direction.UP;

    private final Set<Person> passengers = new HashSet<>();

    private final LiftStatistics statistics = LiftStatistics.of();

    @SneakyThrows
    public void openDoor() {
        TimeUnit.MILLISECONDS.sleep(doorOpenCloseSpeed);
        doorsIsClosed = false;
        log.info(String.format("Lift %s opened the doors", id));
    }

    @SneakyThrows
    public void closeDoor() {
        TimeUnit.MILLISECONDS.sleep(doorOpenCloseSpeed);
        doorsIsClosed = true;
        log.info(String.format("Lift %s closed the doors", id));
    }

    @SneakyThrows
    public void move() {
        if (direction == Direction.UP) {
            TimeUnit.MILLISECONDS.sleep(speed);
            floorNumber++;
            log.info(String.format("Lift %s moved to the %d floor", id, floorNumber));
        }
        else if (direction == Direction.DOWN && floorNumber > 1) {
            TimeUnit.MILLISECONDS.sleep(speed);
            floorNumber--;
            log.info(String.format("Lift %s moved to the %d floor", id, floorNumber));
        }
    }


    public void addPassenger(Person person) {
        checkArgument(person.getWeight() < capacity - getFullness(),
                "by.issoft.sample.domain.Lift not enough space");

        synchronized (passengers) {
            passengers.add(person);
            log.info(String.format("Lift %s took %s", id, person));
            statistics.addStatistics(StatisticItem.of("add", person, floorNumber));
        }
    }

    public void dropOffPassengers() {
        synchronized (passengers) {
            int passengersNumber = 0;
            for (Person passenger : passengers) {
                if (passenger.getNeedFloor() == floorNumber) {
                    statistics.addStatistics(StatisticItem.of("drop off", passenger, floorNumber));
                    passengersNumber++;
                }
            }

            passengers.removeIf(passenger -> passenger.getNeedFloor() == floorNumber);

            log.info(String.format("Lift %s dropped off %d passengers", id, passengersNumber));
        }
    }

    public float getFullness() {
        synchronized (passengers) {
            return (float) passengers
                    .stream()
                    .mapToDouble(Person::getWeight)
                    .sum();
        }
    }

    public boolean needToDropPassengers() {
        synchronized (passengers) {
            return passengers.stream().anyMatch(person -> person.getNeedFloor() == floorNumber);
        }
    }

}
