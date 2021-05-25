package by.issoft.sample.sample;

import by.issoft.sample.domain.Floor;
import by.issoft.sample.domain.LiftButton;

import java.util.stream.IntStream;

import static by.issoft.sample.sample.LiftButtonTestSamples.anyLiftButton;

public class FloorTestSamples {

    public static Floor anyValidFloor() {
        return Floor.of(7, anyLiftButton(), anyLiftButton());
    }

    public static Floor validFloor(int number) {
        return Floor.of(number, anyLiftButton(), anyLiftButton());
    }

    public static Floor anyFloor() {
        return anyValidFloor();
    }

    public static Floor[] anyFloors(int floorsNumber) {
        return IntStream
                .range(0, floorsNumber)
                .mapToObj(i -> Floor.of(i + 1, LiftButton.of((i + 1) + "UP"), LiftButton.of((i + 1) + "DOWN")))
                .toArray(Floor[]::new);
    }

}
