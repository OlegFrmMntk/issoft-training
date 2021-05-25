package by.issoft.sample.sample;

import by.issoft.sample.domain.Floor;
import by.issoft.sample.domain.Lift;
import by.issoft.sample.domain.LiftsController;

import static by.issoft.sample.sample.FloorTestSamples.anyFloors;
import static by.issoft.sample.sample.LiftControllerTestSamples.anyLiftControllers;
import static by.issoft.sample.sample.LiftTestSamples.anyLifts;

public class LiftsControllerTestSamples {
    public static LiftsController anyValidLiftsController() {
        Floor[] floors = anyFloors(10);
        return LiftsController.of(anyLiftControllers(anyLifts(2), floors), floors);
    }

    public static LiftsController validLiftsController(Lift[] lifts, Floor[] floors) {
        return LiftsController.of(anyLiftControllers(lifts, floors), floors);
    }

    public static LiftsController anyLiftsController() {
        return anyValidLiftsController();
    }

}
