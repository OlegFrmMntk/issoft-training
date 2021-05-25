package by.issoft.sample.domain;

import org.junit.Test;

import java.util.Optional;

import static by.issoft.sample.sample.LiftControllerTestSamples.anyLiftController;
import static by.issoft.sample.sample.PersonTestSamples.anyPerson;
import static by.issoft.sample.sample.PersonTestSamples.validPerson;
import static org.junit.Assert.*;

public class LiftControllerTest {

    @Test
    public void pickUpPassengers() {
        LiftController liftController = anyLiftController();
        assertEquals(liftController.getLift().getPassengers().size(), 0);

        liftController.getFloors()[0].addPersonToUpQueue(validPerson(50, 7));
        assertEquals(liftController.getFloors()[0].getUpQueue().size(), 1);

        liftController.getFloors()[0].checkAndPressUpButton();
        assertTrue(liftController.getFloors()[0].getUpButton().isPressed());

        assertEquals(liftController.getLift().getFloorNumber(), liftController.getFloors()[0].getNumber());


        liftController.getLift().setDirection(Direction.UP);

        liftController.pickUpPassengers();

        assertEquals(liftController.getLift().getPassengers().size(), 1);

        assertEquals(liftController.getFloors()[0].getUpQueue().size(), 0);

    }

    @Test
    public void moveLiftToTheFloor() {
        LiftController liftController = anyLiftController();
        assertEquals(liftController.getLift().getPassengers().size(), 0);

        Floor needFloor = liftController.getFloors()[4];

        needFloor.addPersonToUpQueue(validPerson(50, 7));
        assertEquals(needFloor.getUpQueue().size(), 1);

        needFloor.checkAndPressUpButton();
        assertTrue(liftController.getFloors()[4].getUpButton().isPressed());

        liftController.moveLiftToTheFloor(needFloor.getNumber());

        assertEquals(liftController.getLift().getFloorNumber(), needFloor.getNumber());

        assertEquals(liftController.getLift().getPassengers().size(), 1);

        assertEquals(liftController.getFloors()[4].getUpQueue().size(), 0);

    }

    @Test
    public void setLiftDirectionToFloor() {
        LiftController liftController = anyLiftController();

        liftController.setLiftDirectionToFloor(liftController.getFloors()[3].getNumber());
        assertEquals(liftController.getLift().getDirection(), Direction.UP);

        liftController.moveLiftToTheFloor(liftController.getFloors()[5].getNumber());
        liftController.setLiftDirectionToFloor(liftController.getFloors()[3].getNumber());
        assertEquals(liftController.getLift().getDirection(), Direction.DOWN);
    }

    @Test
    public void needToPickUpPassengers() {
        LiftController liftController = anyLiftController();
        liftController.getLift().setDirection(Direction.UP);

        assertFalse(liftController.needToPickUpPassengers());

        Person passenger = validPerson(50, 7);
        liftController.getFloors()[0].addPersonToQueue(passenger);

        assertTrue(liftController.needToPickUpPassengers());
    }

    @Test
    public void depressButton() {
        LiftController liftController = anyLiftController();
        liftController.getLift().setDirection(Direction.UP);

        liftController.getFloors()[0].addPersonToQueue(validPerson(50, 7));
        liftController.getFloors()[0].checkAndPressDownButton();
        liftController.getFloors()[0].checkAndPressUpButton();

        assertTrue(liftController.getFloors()[0].getUpButton().isPressed());
        assertFalse(liftController.getFloors()[0].getDownButton().isPressed());

        liftController.depressButton(liftController.getLift());

        assertFalse(liftController.getFloors()[0].getUpButton().isPressed());
        assertFalse(liftController.getFloors()[0].getDownButton().isPressed());
    }
}