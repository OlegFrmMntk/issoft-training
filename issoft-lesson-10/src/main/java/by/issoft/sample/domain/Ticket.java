package by.issoft.sample.domain;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

public class Ticket {

    final private String id;

    final private String startStation;
    final private String finishStation;

    final private Date departureDate;
    final private Date purchaseDate;

    final private int carriageNumber;
    final private int placeNumber;

    public Ticket(String startStation, String finishStation, Date departureDate, int carNumber, int placeNumber) {

        this.id = UUID.randomUUID().toString();

        this.startStation = startStation;
        this.finishStation = finishStation;

        this.departureDate = departureDate;
        this.purchaseDate = new Date();

        checkArgument(carNumber >= 0, "by.issoft.sample.domain.Ticket car number must be positive");
        this.carriageNumber = carNumber;

        checkArgument(placeNumber >= 0, "by.issoft.sample.domain.Ticket place number must be positive");
        this.placeNumber = placeNumber;
    }

    public static Ticket of(String startStation, String finishStation, Date departureDate, int carNumber, int placeNumber) {
        return new Ticket(startStation, finishStation, departureDate, carNumber, placeNumber);
    }

    public String getId() {
        return id;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getFinishStation() {
        return finishStation;
    }

    public Date getDepartureDate() { return departureDate; }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ticket ticket = (Ticket) o;

        return carriageNumber == ticket.carriageNumber && placeNumber == ticket.placeNumber &&
                startStation.equals(ticket.startStation) && finishStation.equals(ticket.finishStation) &&
                departureDate.equals(ticket.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startStation, finishStation, departureDate, carriageNumber, placeNumber);
    }

    @Override
    public String toString() {
        return String.format("Ticket{id = %s, startStation = %s, finishStation = %s, departureDate = %s, " +
                             "purchaseDate = %s, carNumber = %d, " + "placeNumber = %d}",
                             id, startStation, finishStation, departureDate, purchaseDate, carriageNumber, placeNumber);
    }
}
