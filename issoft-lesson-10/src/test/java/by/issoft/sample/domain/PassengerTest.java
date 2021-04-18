package by.issoft.sample.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.*;

public class PassengerTest {

    @Mock
    Passenger firstPassenger;

    @Mock
    Passenger secondPassenger;

    @Mock
    Ticket ticket;

    @Before
    public void createData() {

        ticket = Ticket.of("Minsk", "Grodno", new Date(), 1, 1);

        firstPassenger = new Passenger("Alex", "Kondrashov", Age.of(40), ticket);

        secondPassenger = Passenger.of("Alex", "Kondrashov", Age.of(40), ticket);
    }

    @Test
    public void of() {
        assertEquals(firstPassenger.getFirstName(), secondPassenger.getFirstName());
        assertEquals(firstPassenger.getLastName(), secondPassenger.getLastName());

        assertEquals(firstPassenger.getAge(), secondPassenger.getAge());

        assertEquals(firstPassenger.getTicket(), secondPassenger.getTicket());

        assertNotSame(firstPassenger, secondPassenger);

    }
}