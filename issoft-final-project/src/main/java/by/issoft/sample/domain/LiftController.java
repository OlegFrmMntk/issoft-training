package by.issoft.sample.domain;

import by.issoft.sample.persistence.LiftStatistics;
import by.issoft.sample.persistence.StatisticItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Optional;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class LiftController implements Runnable {

    private final Lift lift;

    private final Floor[] floors;

    private volatile boolean isFree = true;

    private boolean isFinished = false;

    @Override
    public void run() {

        while (!isFinished()) {
            if (!lift.getPassengers().isEmpty() || lift.getAimFloorNumber() != lift.getFloorNumber()) {

                Optional<Person> person = lift.getPassengers()
                        .stream()
                        .filter(passenger -> passenger.getNeedFloor() == lift.getFloorNumber())
                        .max(Comparator.comparingInt(Person::getNeedFloor));

                person.ifPresent(value -> lift.setAimFloorNumber(value.getNeedFloor() - 1));

                if (lift.getPassengers().isEmpty()) {
                    if (lift.getAimFloorNumber() == lift.getFloorNumber()) {
                        lift.setAimFloorNumber(lift.getStartPosition());
                    }

                }

                setLiftDirectionToFloor(lift.getAimFloorNumber());

                if (lift.needToDropPassengers() || needToPickUpPassengers()) {
                    lift.openDoor();

                    if (lift.needToDropPassengers()) {
                        lift.dropOffPassengers();
                    }
                    if (needToPickUpPassengers()) {
                        pickUpPassengers();
                        depressButton(lift);
                    }

                    lift.closeDoor();
                }

                lift.move();
            }
        }

    }

    public void finish() {
        isFinished = true;
    }

    public void busy() {
        isFree = false;
    }

    public void free() {
        isFree = true;
    }

    public void pickUpPassengers() {
        Optional<Person> passenger = floors[lift.getFloorNumber() - 1]
                .takePersonFromQueue((int) (lift.getCapacity() - lift.getFullness()), lift.getDirection());

        while (passenger.isPresent()) {
            lift.addPassenger(passenger.get());

            passenger = floors[lift.getFloorNumber() - 1]
                    .takePersonFromQueue((int) (lift.getCapacity() - lift.getFullness()), lift.getDirection());
        }
    }

    public void moveLiftToTheFloor(int floorNumber) {
        isFree = false;

        setLiftDirectionToFloor(floorNumber);

        while (lift.getFloorNumber() != floorNumber) {
            lift.move();

            if (lift.getFloorNumber() == 1) {
                lift.setDirection(Direction.UP);
            }
            else if (lift.getFloorNumber() == floors.length) {
                lift.setDirection(Direction.DOWN);
            }

            if (lift.needToDropPassengers() || needToPickUpPassengers()) {
                lift.openDoor();

                if (lift.needToDropPassengers()) {
                    lift.dropOffPassengers();
                }
                if (needToPickUpPassengers()) {
                    pickUpPassengers();
                    depressButton(lift);
                }

                lift.closeDoor();
            }
        }

        isFree = true;
    }

    public void setLiftDirectionToFloor(int floorNumber) {
        lift.setDirection(Direction.NO_DIRECTION);

        if (lift.getFloorNumber() < floorNumber) {
            lift.setDirection(Direction.UP);
        }
        else if (lift.getFloorNumber() > floorNumber) {
            lift.setDirection(Direction.DOWN);
        }
    }

    public boolean needToPickUpPassengers() {
        return (lift.getDirection() == Direction.UP && floors[lift.getFloorNumber() - 1].getUpQueue().size() > 0) ||
                (lift.getDirection() == Direction.DOWN && floors[lift.getFloorNumber() - 1].getDownQueue().size() > 0);
    }

    public void depressButton(Lift lift) {
        if (lift.getDirection() == Direction.UP) {
            floors[lift.getFloorNumber() - 1].getUpButton().depress();
        }
        else {
            floors[lift.getFloorNumber() - 1].getDownButton().depress();
        }
    }

}
