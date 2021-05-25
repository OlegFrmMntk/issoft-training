package by.issoft.sample.sample;

import by.issoft.sample.domain.Lift;

import java.util.stream.IntStream;

public class LiftTestSamples {

    public static Lift anyValidLift() {
        return Lift.of("1LIFT", 1000, 100, 50);
    }

    public static Lift validLift(int capacity) {
        return Lift.of("1LIFT", capacity, 100, 50);
    }

    public static Lift anyLift() {
        return anyValidLift();
    }

    public static Lift[] anyLifts(int number) {
        return  IntStream
                .range(0, number)
                .mapToObj(i -> Lift.of((i + 1) + "LIFT", (int) (Math.random() * 1000), (int) (Math.random() * 1000), (int) (Math.random() * 500)))
                .toArray(Lift[]::new);
    }
}
