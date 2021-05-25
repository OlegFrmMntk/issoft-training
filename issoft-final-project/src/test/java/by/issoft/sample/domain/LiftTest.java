package by.issoft.sample.domain;

import org.junit.Test;

import java.util.Comparator;

import static by.issoft.sample.sample.LiftTestSamples.anyLift;
import static by.issoft.sample.sample.PersonTestSamples.anyValidPerson;
import static by.issoft.sample.sample.PersonTestSamples.validPerson;
import static org.junit.Assert.*;

public class LiftTest {

    @Test
    public void openDoor() {
        Lift lift = anyLift();
        assertTrue(lift.isDoorsIsClosed());

        lift.openDoor();
        assertFalse(lift.isDoorsIsClosed());

        lift.closeDoor();
        assertTrue(lift.isDoorsIsClosed());
    }

    @Test
    public void closeDoor() {
        Lift lift = anyLift();
        lift.openDoor();
        assertFalse(lift.isDoorsIsClosed());

        lift.closeDoor();
        assertTrue(lift.isDoorsIsClosed());
    }

    @Test
    public void move() {
        Lift lift = anyLift();

        int nowPosition = lift.getFloorNumber();

        lift.setDirection(Direction.NO_DIRECTION);
        for (int i = 0; i < 10; i++) {
            lift.move();
            assertEquals(lift.getFloorNumber(), nowPosition);
        }

        lift.setDirection(Direction.UP);
        for (int i = 0; i < 10; i++) {
            lift.move();
            assertEquals(lift.getFloorNumber(), nowPosition + i + 1);
        }

        lift.setDirection(Direction.DOWN);
        for (int i = 10; i >= 0; i--) {
            lift.move();
            assertEquals(lift.getFloorNumber(), nowPosition + i - 1);
        }
    }

    @Test
    public void addPassenger() {
        Lift lift = anyLift();
        int passengerNumber = lift.getPassengers().size();

        Person passenger = anyValidPerson();

        lift.addPassenger(passenger);
        assertEquals(lift.getPassengers().size(), passengerNumber + 1);
    }

    @Test
    public void dropOffPassengers() {
        Lift lift = anyLift();

        Person passenger1 = validPerson(100, lift.getFloorNumber());
        Person passenger2 = validPerson(100, lift.getFloorNumber() + 1);

        lift.addPassenger(passenger1);
        lift.addPassenger(passenger2);

        assertEquals(lift.getPassengers().size(), 2);

        lift.dropOffPassengers();
        assertEquals(lift.getPassengers().size(), 1);
    }

    @Test
    public void getFullness() {
        Lift lift = anyLift();

        Person passenger1 = validPerson(100, lift.getFloorNumber());

        lift.addPassenger(passenger1);
    }

    @Test
    public void needToDropPassengers() {
        Lift lift = anyLift();

        Person passenger1 = validPerson(100, lift.getFloorNumber());
        Person passenger2 = validPerson(100, lift.getFloorNumber() + 1);

        lift.addPassenger(passenger1);
        lift.addPassenger(passenger2);

        assertTrue(lift.needToDropPassengers());
        lift.dropOffPassengers();

        assertFalse(lift.needToDropPassengers());
    }

}