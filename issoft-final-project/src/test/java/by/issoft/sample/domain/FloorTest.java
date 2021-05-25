package by.issoft.sample.domain;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static by.issoft.sample.sample.FloorTestSamples.anyFloor;
import static by.issoft.sample.sample.FloorTestSamples.validFloor;
import static by.issoft.sample.sample.PersonTestSamples.anyPerson;
import static by.issoft.sample.sample.PersonTestSamples.validPerson;
import static org.junit.Assert.*;

public class FloorTest {

    @Test
    public void checkAndPressUpButton() {
        Floor floor = anyFloor();
        assertFalse(floor.getUpButton().isPressed());

        floor.checkAndPressUpButton();
        assertFalse(floor.getUpButton().isPressed());

        floor.addPersonToUpQueue(anyPerson());
        floor.checkAndPressUpButton();
        assertTrue(floor.getUpButton().isPressed());
    }

    @Test
    public void checkAndPressDownButton() {
        Floor floor = anyFloor();
        assertFalse(floor.getDownButton().isPressed());

        floor.checkAndPressDownButton();
        assertFalse(floor.getDownButton().isPressed());

        floor.addPersonToDownQueue(anyPerson());
        floor.checkAndPressDownButton();;
        assertTrue(floor.getDownButton().isPressed());
    }

    @Test
    public void addPersonToUpQueue() {
        Floor floor = anyFloor();

        IntStream.range(1, 101).forEach(i -> {
            floor.addPersonToUpQueue(anyPerson());
            assertEquals(floor.getUpQueue().size(), i);
        });
    }

    @Test
    public void addPersonToDownQueue() {
        Floor floor = anyFloor();

        IntStream.range(1, 101).forEach(i -> {
            floor.addPersonToDownQueue(anyPerson());
            assertEquals(floor.getDownQueue().size(), i);
        });
    }

    @Test
    public void addPersonToQueue() {
        Floor floor = validFloor(10);

        IntStream.range(0, 20)
                .mapToObj(floorsNumber -> validPerson(10, floorsNumber))
                .forEach(floor::addPersonToQueue);

        assertEquals(floor.getDownQueue().size(), 10);
        assertEquals(floor.getUpQueue().size(), 10);
    }

    @Test
    public void takePersonFromUpQueue() {
        Floor floor = anyFloor();

        IntStream.range(1, 101).mapToObj(i -> anyPerson()).forEach(floor::addPersonToUpQueue);

        IntStream.range(1, 101).forEach(i -> {
            floor.takePersonFromUpQueue(400);
            assertEquals(floor.getUpQueue().size(), 100 - i);
        });

        Optional<Person> person = floor.takePersonFromUpQueue(400);
        assertTrue(person.isEmpty());
    }

    @Test
    public void takePersonFromDownQueue() {
        Floor floor = anyFloor();

        IntStream.range(1, 101).mapToObj(i -> anyPerson()).forEach(floor::addPersonToDownQueue);

        IntStream.range(1, 101).forEach(i -> {
            floor.takePersonFromDownQueue(400);
            assertEquals(floor.getDownQueue().size(), 100 - i);
        });

        Optional<Person> person = floor.takePersonFromDownQueue(400);
        assertTrue(person.isEmpty());
    }

    @Test
    public void takePersonFromQueue() {
        Floor floor = anyFloor();

        IntStream.range(1, 101).mapToObj(i -> anyPerson()).forEach(floor::addPersonToDownQueue);
        IntStream.range(1, 101).mapToObj(i -> anyPerson()).forEach(floor::addPersonToUpQueue);

        IntStream.range(1, 101).forEach(i -> {
            floor.takePersonFromQueue(400, Direction.DOWN);
            assertEquals(floor.getDownQueue().size(), 100 - i);
        });

        IntStream.range(1, 101).forEach(i -> {
            floor.takePersonFromQueue(400, Direction.UP);
            assertEquals(floor.getUpQueue().size(), 100 - i);
        });

        Optional<Person> person = floor.takePersonFromQueue(400, Direction.DOWN);
        assertTrue(person.isEmpty());

        person = floor.takePersonFromQueue(400, Direction.UP);
        assertTrue(person.isEmpty());
    }
}