package by.issoft.sample.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PassengerCarriage {
    void addPassenger(@NotNull Passenger passenger);

    void addPassengers(List<Passenger> passengers);

    void dropOffPassenger(@NotNull Passenger passenger);

    void dropOfPassengers(List<Passenger> passengers);

    boolean contains(Passenger passenger);
}
