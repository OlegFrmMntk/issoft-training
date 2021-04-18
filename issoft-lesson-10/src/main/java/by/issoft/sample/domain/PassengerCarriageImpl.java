package by.issoft.sample.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class PassengerCarriageImpl extends Carriage implements PassengerCarriage {

    final private Passenger[] passengers;

    public PassengerCarriageImpl(int capacity) {
        this(capacity, null, List.of());
    }

    public PassengerCarriageImpl(int capacity, List<Passenger> passengers) {
        super(capacity);
        this.passengers = new Passenger[capacity + 1];
        addPassengers(passengers);
    }

    public PassengerCarriageImpl(int capacity, Carriage nextCarriage, List<Passenger> passengers) {
        super(capacity, nextCarriage);
        this.passengers = new Passenger[capacity + 1];
        addPassengers(passengers);
    }

    public static PassengerCarriageImpl of(int capacity) {
        return new PassengerCarriageImpl(capacity);
    }

    public static PassengerCarriageImpl of(int capacity, Carriage nextCarriage) {
        return new PassengerCarriageImpl(capacity, nextCarriage, List.of());
    }

    public static PassengerCarriageImpl of(int capacity, Carriage nextCarriage, List<Passenger> passengers) {
        return new PassengerCarriageImpl(capacity, nextCarriage, passengers);
    }

    public void addPassengers(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            addPassenger(passenger);
        }
    }

    public void addPassenger(@NotNull Passenger passenger) {
        int placeNumber = passenger.getTicket().getPlaceNumber();

        checkArgument(placeNumber <= capacity,
                "by.issoft.sample.domain.PassengerCarriage place number must be <= capacity");

        checkArgument(passengers[placeNumber] == null,
                "by.issoft.sample.domain.PassengerCarriage seat occupied");

        this.passengers[placeNumber] = passenger;
        fullness++;
    }

    public void dropOffPassenger(@NotNull Passenger passenger) {
        int placeNumber = passenger.getTicket().getPlaceNumber();

        checkArgument(placeNumber <= capacity,
                "by.issoft.sample.domain.PassengerCarriage place number must be <= capacity");

        checkArgument(passengers[placeNumber] != null,
                "by.issoft.sample.domain.PassengerCarriage place is empty");

        this.passengers[placeNumber] = null;
        fullness--;
    }

    public void dropOfPassengers(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            dropOffPassenger(passenger);
        }
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public List<Passenger> getPassengerList() {
        return List.of(passengers);
    }

    public boolean contains(Passenger passenger) {
        return this.passengers[passenger.getTicket().getPlaceNumber()] == passenger;
    }


}
