package by.issoft.sample.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocomotiveTest {

    @Test
    public void of() {
        Driver driver = Driver.of(User.of("Alex", "Kondrashov", Age.of(40)), true);

        Locomotive locomotive = Locomotive.of(400, 10, driver);

        assertEquals(locomotive.getTraction(), 400);
        assertEquals(locomotive.getLifeTime(), 10);
        assertEquals(locomotive.getDriver(), driver);

        assertNotSame(locomotive, new Locomotive(400, 10, driver));
    }

    @Test
    public void setDriver() {

        Driver firstDriver = Driver.of(User.of("Alex", "Kondrashov", Age.of(40)), true);
        Driver secondDriver = Driver.of(User.of("Ivan", "Ivanov", Age.of(32)), true);

        Locomotive locomotive = Locomotive.of(400, 10, firstDriver);

        assertEquals(locomotive.getDriver(), firstDriver);
        locomotive.setDriver(secondDriver);
        assertEquals(locomotive.getDriver(), secondDriver);
    }

}