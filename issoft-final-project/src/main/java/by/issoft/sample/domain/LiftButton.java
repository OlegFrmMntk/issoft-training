package by.issoft.sample.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
@Getter
public class LiftButton {

    private final String id;

    private volatile boolean isPressed = false;

    public void press() {
        isPressed = true;
    }

    public void depress() {
        isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }
}