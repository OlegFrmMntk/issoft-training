package by.issoft.sample.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class DriverTest {

    @Mock
    Driver firstDriver;

    @Mock
    Driver secondDriver;


    @Before
    public void createData() {

        firstDriver = new Driver("Alex", "Kondrashov", Age.of(40), true);

        secondDriver = Driver.of("Alex", "Kondrashov", Age.of(40), true);

    }

    @Test
    public void of() {
        assertEquals(firstDriver.getFirstName(), secondDriver.getFirstName());
        assertEquals(firstDriver.getLastName(), secondDriver.getLastName());

        assertEquals(firstDriver.getAge(), secondDriver.getAge());

        assertEquals(firstDriver.hasLicence(), secondDriver.hasLicence());

        assertNotSame(firstDriver, secondDriver);
    }

    @Test
    public void hasLicence() {
        assertTrue(firstDriver.hasLicence());
        assertTrue(secondDriver.hasLicence());

        firstDriver.setLicenceStatus(false);
        secondDriver.setLicenceStatus(false);

        assertFalse(firstDriver.hasLicence());
        assertFalse(secondDriver.hasLicence());
    }
}