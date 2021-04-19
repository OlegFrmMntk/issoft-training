package by.issoft.sample.domain;

public class Passenger {

    private final User user;

    private Ticket ticket;

    public Passenger(User user, Ticket ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    public static Passenger of(User user, Ticket ticket) {
        return new Passenger(user, ticket);
    }

    public User getUser() {
        return user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return String.format("%s{user = %s, ticketId = %s}", getClass(), user, ticket.getId());
    }
}
