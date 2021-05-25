package by.issoft.sample.domain;

import org.junit.Test;

import static by.issoft.sample.sample.LiftsControllerTestSamples.anyLiftsController;
import static org.junit.Assert.*;

public class LiftsControllerTest {

    @Test
    public void getNearestDownLift() {
        LiftsController liftsController = anyLiftsController();

        liftsController.getControllers()[0].moveLiftToTheFloor(liftsController.getFloors()[5].getNumber());
        liftsController.getControllers()[1].moveLiftToTheFloor(liftsController.getFloors()[8].getNumber());

        assertEquals(liftsController.getControllers()[0].getLift().getFloorNumber(), 6);
        assertEquals(liftsController.getControllers()[1].getLift().getFloorNumber(), 9);

        liftsController.getFloors()[9].checkAndPressDownButton();
        liftsController.getFloors()[5].checkAndPressDownButton();

        assertSame(liftsController.getNearestDownLift(liftsController.getFloors()[4].getNumber()).get(), liftsController.getControllers()[0]);
    }

    @Test
    public void getNearestUpLift() {
        LiftsController liftsController = anyLiftsController();

        liftsController.getControllers()[0].moveLiftToTheFloor(liftsController.getFloors()[1].getNumber());
        liftsController.getControllers()[1].moveLiftToTheFloor(liftsController.getFloors()[4].getNumber());

        assertEquals(liftsController.getControllers()[0].getLift().getFloorNumber(), 2);
        assertEquals(liftsController.getControllers()[1].getLift().getFloorNumber(), 5);

        liftsController.getFloors()[9].checkAndPressUpButton();
        liftsController.getFloors()[5].checkAndPressUpButton();

        assertSame(liftsController.getNearestUpLift(liftsController.getFloors()[3].getNumber()).get(), liftsController.getControllers()[0]);
    }
}