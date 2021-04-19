package by.issoft.sample.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class PassengerCarriage extends Carriage {

    private final int capacity;

    private int fullness;

    private final Passenger[] passengers;

    public PassengerCarriage(int capacity) {
        this(capacity, null, List.of());
    }

    public PassengerCarriage(int capacity, List<Passenger> passengers) {
        this.capacity = capacity;
        this.passengers = new Passenger[capacity + 1];
        addPassengers(passengers);
    }

    public PassengerCarriage(int capacity, Carriage nextCarriage, List<Passenger> passengers) {
        super(nextCarriage);
        this.capacity = capacity;
        this.passengers = new Passenger[capacity + 1];
        addPassengers(passengers);
    }

    public static PassengerCarriage of(int capacity) {
        return new PassengerCarriage(capacity);
    }

    public static PassengerCarriage of(int capacity, Carriage nextCarriage) {
        return new PassengerCarriage(capacity, nextCarriage, List.of());
    }

    public static PassengerCarriage of(int capacity, Carriage nextCarriage, List<Passenger> passengers) {
        return new PassengerCarriage(capacity, nextCarriage, passengers);
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

    public int getCapacity() {
        return capacity;
    }

    public int getFullness() {
        return fullness;
    }

    public boolean contains(Passenger passenger) {
        return this.passengers[passenger.getTicket().getPlaceNumber()] == passenger;
    }


}
