package by.issoft.sample.domain;

import static by.issoft.sample.sample.LiftButtonTestSamples.anyLiftButton;
import static org.junit.Assert.*;

import org.junit.Test;

public class LiftButtonTest {

    @Test
    public void press() {
        LiftButton button = anyLiftButton();
        assertFalse(button.isPressed());

        button.press();
        assertTrue(button.isPressed());
    }


    @Test
    public void depress() {
        LiftButton button = anyLiftButton();
        assertFalse(button.isPressed());

        button.press();
        assertTrue(button.isPressed());

        button.depress();
        assertFalse(button.isPressed());
    }

    @Test
    public void isPressed() {
        LiftButton button = anyLiftButton();
        assertFalse(button.isPressed());

        button.press();
        assertTrue(button.isPressed());
    }

}