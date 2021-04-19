package by.issoft.sample.domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TicketTest {

    @Test
    public void equals() {

        Ticket firstTicket = new Ticket("Minsk", "Grodno", new Date(), 1, 1);
        Ticket secondTicket = new Ticket("Minsk", "Grodno", new Date(), 1, 1);
        Ticket thirdTicket = new Ticket("Minsk", "Grodno", new Date(), 1, 1);

        assertEquals(firstTicket, firstTicket);

        assertEquals(firstTicket.equals(secondTicket), secondTicket.equals(firstTicket));

        if (firstTicket.equals(secondTicket) == secondTicket.equals(thirdTicket)) {
            assertEquals(firstTicket, thirdTicket);
        }
        else {
            assertNotEquals(firstTicket, thirdTicket);
        }

        assertEquals(firstTicket.equals(secondTicket), firstTicket.equals(secondTicket));

        assertNotEquals(null, firstTicket);

        secondTicket = Ticket.of("Minsk", "Grodno", new Date(), 1, 2);

        assertNotEquals(firstTicket, secondTicket);
        assertNotEquals(thirdTicket, secondTicket);

        secondTicket = Ticket.of("Minsk", "Grodno", new Date(), 2, 1);

        assertNotEquals(firstTicket, secondTicket);
        assertNotEquals(thirdTicket, secondTicket);

        secondTicket = Ticket.of("Minsk", "Ivye", new Date(), 1, 1);

        assertNotEquals(firstTicket, secondTicket);
        assertNotEquals(thirdTicket, secondTicket);

        secondTicket = Ticket.of("Ivye", "Grodno", new Date(), 1, 1);

        assertNotEquals(firstTicket, secondTicket);
        assertNotEquals(thirdTicket, secondTicket);
    }
}