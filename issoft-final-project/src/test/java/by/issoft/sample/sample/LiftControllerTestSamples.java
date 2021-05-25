package by.issoft.sample.sample;


import by.issoft.sample.domain.Floor;
import by.issoft.sample.domain.Lift;
import by.issoft.sample.domain.LiftController;

import java.util.Arrays;

import static by.issoft.sample.sample.FloorTestSamples.anyFloors;
import static by.issoft.sample.sample.LiftTestSamples.anyLift;

public class LiftControllerTestSamples {
    public static LiftController anyValidLiftController() {
        return LiftController.of(anyLift(), anyFloors(10));
    }

    public static LiftController validLiftController(Lift lift) {
        return LiftController.of(lift, anyFloors(10));
    }

    public static LiftController anyLiftController() {
        return anyValidLiftController();
    }

    public static LiftController[] anyLiftControllers(Lift[] lifts, Floor[] floors) {
        return Arrays
                .stream(lifts)
                .map(lift -> LiftController.of(lift, floors))
                .toArray(LiftController[]::new);
    }

}
