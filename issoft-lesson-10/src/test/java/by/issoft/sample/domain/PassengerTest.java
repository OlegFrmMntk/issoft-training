package by.issoft.sample.domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PassengerTest {

    @Test
    public void of() {

        Ticket ticket = Ticket.of("Minsk", "Grodno", new Date(), 1, 1);

        User user = User.of("Alex", "Kondrashov", Age.of(40));

        Passenger passenger = new Passenger(user, ticket);

        assertEquals(passenger.getUser(), user);

        assertEquals(passenger.getTicket(), ticket);

        assertNotSame(passenger, new Passenger(user, ticket));

    }

}