package by.issoft.sample.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PassengerCarriageImplTest {

    @Mock
    private PassengerCarriageImpl firstPassengerCarriage;

    @Mock
    private PassengerCarriageImpl secondPassengerCarriage;

    @Mock
    private PassengerCarriageImpl thirdPassengerCarriage;

    @Mock
    Passenger firstPassenger;

    @Mock
    Passenger secondPassenger;

    @Before
    public void createData() {
        firstPassengerCarriage = new PassengerCarriageImpl(50);
        secondPassengerCarriage = new PassengerCarriageImpl(50);
        thirdPassengerCarriage = new PassengerCarriageImpl(50);

        firstPassengerCarriage.setNextCarriage(secondPassengerCarriage);
        secondPassengerCarriage.setNextCarriage(thirdPassengerCarriage);

        firstPassenger = new Passenger("Alex", "Kondrashov", Age.of(40),
                Ticket.of("Minsk", "Grodno", new Date(), 1, 10));

        secondPassenger = new Passenger("Ivan", "Ivanov", Age.of(55),
                Ticket.of("Minsk", "Grodno", new Date(), 1, 4));
    }

    @Test
    public void testAddPassengers() {
        firstPassengerCarriage.addPassengers(List.of(firstPassenger, secondPassenger));

        assertTrue(firstPassengerCarriage.contains(firstPassenger));
        assertTrue(firstPassengerCarriage.contains(secondPassenger));
    }

    @Test
    public void testAddPassenger() {
        secondPassengerCarriage.addPassenger(firstPassenger);
        assertTrue(secondPassengerCarriage.contains(firstPassenger));

        secondPassengerCarriage.addPassenger(secondPassenger);
        assertTrue(secondPassengerCarriage.contains(secondPassenger));
    }

    @Test
    public void testDropOffPassenger() {
        thirdPassengerCarriage.addPassengers(List.of(firstPassenger, secondPassenger));

        thirdPassengerCarriage.dropOffPassenger(firstPassenger);
        assertFalse(thirdPassengerCarriage.contains(firstPassenger));

        thirdPassengerCarriage.dropOffPassenger(secondPassenger);
        assertFalse(thirdPassengerCarriage.contains(secondPassenger));
    }

    @Test
    public void testDropOfPassengers() {
        thirdPassengerCarriage.addPassengers(List.of(firstPassenger, secondPassenger));

        thirdPassengerCarriage.dropOfPassengers(List.of(firstPassenger, secondPassenger));

        assertFalse(thirdPassengerCarriage.contains(firstPassenger));
        assertFalse(thirdPassengerCarriage.contains(secondPassenger));
    }

    @Test
    public void testLink() {
        assertEquals(firstPassengerCarriage.getNextCarriage(), secondPassengerCarriage);
        assertEquals(secondPassengerCarriage.getNextCarriage(), thirdPassengerCarriage);
        assertNull(thirdPassengerCarriage.getNextCarriage());
    }
}