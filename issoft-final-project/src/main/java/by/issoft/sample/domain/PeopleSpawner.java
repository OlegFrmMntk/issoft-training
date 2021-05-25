package by.issoft.sample.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class PeopleSpawner implements Runnable {

    private final Floor floor;

    private final int floorsNumber;

    private final int intensity;

    private boolean isFinished = false;

    @Override
    public void run() {
        while (!isFinished()) {
            addRandomPeopleToQueue();
        }
    }

    public void finish() {
        isFinished = true;
    }

    public Person getRandomPerson() {

        int needFloor = floor.getNumber();
        while (needFloor == floor.getNumber() && needFloor != 0) {
            needFloor = (int) (Math.random() * floorsNumber);
        }

        return Person.of(
                UUID.randomUUID().toString(),
                RandomStringUtils.random((int) (Math.random() * 10)),
                RandomStringUtils.random((int) (Math.random() * 15)),
                LocalDate.of(2000, 12, 21),
                Gender.MALE,
                (int) (Math.random() * 200),
                needFloor
        );
    }

    @SneakyThrows
    public void addRandomPeopleToQueue() {
        IntStream.range(0, intensity)
                .mapToObj(i -> getRandomPerson())
                .forEachOrdered(floor::addPersonToQueue);

        TimeUnit.SECONDS.sleep(1);
    }

}
