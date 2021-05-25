package by.issoft.sample.sample;

import by.issoft.sample.domain.LiftButton;

public class LiftButtonTestSamples {

    public static LiftButton anyValidLiftButton() {
        return LiftButton.of("test_button");
    }

    public static LiftButton validLiftButton(String id) {
        return LiftButton.of(id);
    }

    public static LiftButton anyLiftButton() {
        return anyValidLiftButton();
    }
}
