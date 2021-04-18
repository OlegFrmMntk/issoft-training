package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TicketTest {

    @Mock
    Ticket firstTicket;

    @Mock
    Ticket secondTicket;

    @Mock
    Ticket thirdTicket;

    @Before
    public void createData() {
        firstTicket = new Ticket("Minsk", "Grodno", new Date(), 1, 1);
        secondTicket = new Ticket("Minsk", "Grodno", new Date(), 1, 1);
        thirdTicket = new Ticket("Minsk", "Grodno", new Date(), 1, 1);
    }

    @Test
    public void testEquals() {

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