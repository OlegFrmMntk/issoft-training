package by.issoft.sample.domain;

public class Passenger extends User {

    private Ticket ticket;

    public Passenger(String firstName, String lastName, Age age, Ticket ticket) {
        super(firstName, lastName, age);
        this.ticket = ticket;
    }

    public static Passenger of(String firstName, String lastName, Age age, Ticket ticket) {
        return new Passenger(firstName, lastName, age, ticket);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


}
