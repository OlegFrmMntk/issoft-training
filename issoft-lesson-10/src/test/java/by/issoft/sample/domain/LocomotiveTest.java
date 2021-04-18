package by.issoft.sample.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class LocomotiveTest {

    @Mock
    Locomotive firstLocomotive;

    @Mock
    Locomotive secondLocomotive;

    @Mock
    Driver firstDriver;

    @Mock
    Driver secondDriver;

    @Before
    public void createData() {

        firstDriver = new Driver("Alex", "Kondrashov", Age.of(40), true);

        secondDriver = new Driver("Ivan", "Ivanov", Age.of(32), true);

        firstLocomotive = new Locomotive(400, 10, firstDriver);

        secondLocomotive = Locomotive.of(400, 10, firstDriver);

    }

    @Test
    public void of() {
        assertEquals(firstLocomotive.getTraction(), secondLocomotive.getTraction());
        assertEquals(firstLocomotive.getLifeTime(), secondLocomotive.getLifeTime());
        assertEquals(firstLocomotive.getDriver(), secondLocomotive.getDriver());

        assertNotSame(firstLocomotive, secondLocomotive);
    }

    @Test
    public void setDriver() {
        assertEquals(firstLocomotive.getDriver(), firstDriver);
        firstLocomotive.setDriver(secondDriver);
        assertEquals(firstLocomotive.getDriver(), secondDriver);
    }
}