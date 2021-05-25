package by.issoft.sample.domain;

import org.junit.Test;

import static by.issoft.sample.sample.PeopleSpawnerTestSamples.anyPeopleSpawner;
import static org.junit.Assert.*;

public class PeopleSpawnerTest {

    @Test
    public void getRandomPerson() {
        PeopleSpawner peopleSpawner = anyPeopleSpawner();
        assertNotSame(peopleSpawner.getRandomPerson(), peopleSpawner.getRandomPerson());

        Person person = peopleSpawner.getRandomPerson();

        assertTrue(person.getNeedFloor() > 0);
        assertTrue(person.getNeedFloor() <= peopleSpawner.getFloorsNumber());
    }

    @Test
    public void addRandomPeopleToQueue() {
        PeopleSpawner peopleSpawner = anyPeopleSpawner();
        Floor floor = peopleSpawner.getFloor();

        assertEquals(0, floor.getDownQueue().size() + floor.getUpQueue().size());

        peopleSpawner.addRandomPeopleToQueue();

        assertEquals(peopleSpawner.getIntensity(), floor.getDownQueue().size() + floor.getUpQueue().size());

    }
}