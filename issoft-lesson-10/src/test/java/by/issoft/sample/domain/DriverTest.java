package by.issoft.sample.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class DriverTest {

    @Test
    public void of() {
        User user = User.of("Alex", "Kondrashov", Age.of(40));

        Driver firstDriver = new Driver(user, true);
        Driver secondDriver = Driver.of(user, true);

        assertEquals(firstDriver.getUser(), secondDriver.getUser());

        assertEquals(firstDriver.hasLicence(), secondDriver.hasLicence());

        assertNotSame(firstDriver, secondDriver);
    }

    @Test
    public void hasLicence() {
        Driver driver = Driver.of(User.of("Alex", "Kondrashov", Age.of(40)), true);

        assertTrue(driver.hasLicence());

        driver.setLicence(false);

        assertFalse(driver.hasLicence());
    }
}