package by.issoft.sample.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.*;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class Floor implements Runnable {

    private final int number;

    private final LiftButton upButton;

    private final LiftButton downButton;

    private final List<Person> upQueue = new ArrayList<>();

    private final List<Person> downQueue = new ArrayList<>();

    private boolean isFinished = false;

    @Override
    public void run() {
        while (!isFinished()) {
            checkAndPressUpButton();
            checkAndPressDownButton();
        }
    }

    public void finish() {
        this.isFinished = true;
    }

    public void checkAndPressUpButton() {
        synchronized (upQueue) {
            if (!upQueue.isEmpty() && !upButton.isPressed()) {
                upButton.press();
            }
        }
    }

    public void checkAndPressDownButton() {
        synchronized (downQueue) {
            if (!downQueue.isEmpty() && !downButton.isPressed()) {
                downButton.press();
            }
        }
    }

    public void addPersonToUpQueue(@NonNull Person person) {
        synchronized (upQueue) {
            upQueue.add(person);
        }
    }

    public void addPersonToDownQueue(@NonNull Person person) {
        synchronized (downQueue) {
            downQueue.add(person);
        }
    }

    public Optional<Person> takePersonFromUpQueue(int maxWeight) {
        return takePersonFromQueue(upQueue, maxWeight);
    }

    public Optional<Person> takePersonFromDownQueue(int maxWeight) {
        return takePersonFromQueue(downQueue, maxWeight);
    }

    public Optional<Person> takePersonFromQueue(int maxWeight, Direction direction) {
        return direction == Direction.UP ? takePersonFromUpQueue(maxWeight) : takePersonFromDownQueue(maxWeight);
    }

    private Optional<Person> takePersonFromQueue(List<Person> queue, int maxWeight) {
        Optional<Person> passenger;
        synchronized (queue) {
            passenger = queue.stream()
                    .filter(person -> person.getWeight() <= maxWeight)
                    .findFirst();

            passenger.ifPresent(queue::remove);
        }

        return passenger;
    }

    public void addPersonToQueue(Person person) {
        if (number > person.getNeedFloor()) {
            synchronized (downQueue) {
                downQueue.add(person);
            }
        } else {
            synchronized (upQueue) {
                upQueue.add(person);
            }
        }
    }

}
