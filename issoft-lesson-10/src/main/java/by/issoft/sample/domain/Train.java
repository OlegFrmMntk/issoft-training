package by.issoft.sample.domain;

import java.util.List;

public interface Train {

    void addCarriage(Carriage carriage);
    void addCarriages(List<Carriage> carriages);

    Carriage getCarriage(int number);

    Carriage unhookLastCarriage();

    void loadCargo(Cargo cargo);
    void loadCargo(int carriageNumber, Cargo cargo);

    void loadCagros(List<Cargo> cargos);
    void loadCagros(int carriageNumber, List<Cargo> cargos);

    void uploadCargo(int carriageNumber, Cargo cargo);

    void uploadCargos(int carriageNumber, List<Cargo> cargos);

    void addPassenger(Passenger passenger);

    void addPassengers(List<Passenger> passengers);

    void dropOfPassenger(Passenger passenger);

    void dropOfPassengers(List<Passenger> passengers);
}
