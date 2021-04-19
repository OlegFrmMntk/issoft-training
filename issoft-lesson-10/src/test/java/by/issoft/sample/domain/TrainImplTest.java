package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TrainImplTest {

    @Test
    public void of() {
        Locomotive locomotive = new Locomotive(400, 10,
                Driver.of(User.of("Nikita", "Puzikov", Age.of(40)), true));

        Carriage firstCarriage = new CargoCarriage(50);

        TrainImpl train = new TrainImpl("123", locomotive, firstCarriage);

        assertEquals(train.getFirstCarriage(), firstCarriage);
        assertEquals(train.getLocomotive(), locomotive);

        assertEquals(train, new TrainImpl("123", locomotive, firstCarriage));
    }

    @Test
    public void addCarriage() {
        TrainImpl train = anyTrain();

        Carriage carriage = CargoCarriage.of(30);
        train.addCarriage(carriage);

        assertEquals(train.getCarriage(2), carriage);
    }

    @Test
    public void addCarriages() {
        TrainImpl train = anyTrain();

        Carriage secondCarriage = PassengerCarriage.of(30);
        Carriage thirdCarriage = CargoCarriage.of(50);
        Carriage fourthCarriage = PassengerCarriage.of(60);

        train.addCarriages(List.of(secondCarriage, thirdCarriage, fourthCarriage));

        assertEquals(train.getCarriage(2), secondCarriage);
        assertEquals(train.getCarriage(3), thirdCarriage);
        assertEquals(train.getCarriage(4), fourthCarriage);
    }

    @Test
    public void loadCargo() {
        TrainImpl train = anyTrain();

        Carriage secondCarriage = PassengerCarriage.of(30);
        Carriage thirdCarriage = CargoCarriage.of(50);
        Carriage fourthCarriage = PassengerCarriage.of(60);
        Carriage fifthCarriage = CargoCarriage.of(40);

        train.getFirstCarriage().setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);
        fourthCarriage.setNextCarriage(fifthCarriage);

        List<Cargo> cargos = List.of(
                Cargo.of(CargoType.SOLID, 2),
                Cargo.of(CargoType.GAS, 4),
                Cargo.of(CargoType.FLUID, 6)
        );

        int i = 1;
        for (Cargo cargo : cargos) {
            train.loadCargo(i, cargo);
            assertTrue(((CargoCarriage) train.getCarriage(i)).contains(cargo));
            i += 2;
        }
    }


    @Test
    public void loadCargos() {
        TrainImpl train = anyTrain();

        List<Cargo> cargos = List.of(
                Cargo.of(CargoType.SOLID, 2),
                Cargo.of(CargoType.GAS, 4),
                Cargo.of(CargoType.FLUID, 6)
        );

        train.loadCagros(1, cargos);
        for (Cargo cargo : cargos) {
            assertTrue(((CargoCarriage) train.getFirstCarriage()).contains(cargo));
        }
    }

    @Test
    public void addPassenger() {
        TrainImpl train = anyTrain();

        Carriage secondCarriage = PassengerCarriage.of(30);
        train.getFirstCarriage().setNextCarriage(secondCarriage);

        for (Passenger passenger : anyPassengers()) {
            train.addPassenger(passenger);
            assertTrue(((PassengerCarriage) train.getCarriage(2)).contains(passenger));
        }
    }

    @Test
    public void addPassengers() {
        TrainImpl train = anyTrain();

        Carriage secondCarriage = PassengerCarriage.of(30);
        train.getFirstCarriage().setNextCarriage(secondCarriage);

        List<Passenger> passengers = anyPassengers();

        train.addPassengers(passengers);
        for (Passenger passenger : passengers) {
            assertTrue(((PassengerCarriage) train.getCarriage(2)).contains(passenger));
        }
    }

    @Test
    public void uploadCargo() {
        TrainImpl train = anyTrain();

        List<Cargo> cargos = List.of(Cargo.of(8),
                                     Cargo.of(CargoType.GAS, 15),
                                     Cargo.of(CargoType.FLUID, 4)
                                    );

        for (Cargo cargo : cargos) {
            train.loadCargo(1, cargo);
            Assert.assertTrue(((CargoCarriage) train.getCarriage(1)).contains(cargo));

            train.uploadCargo(1, cargo);
            Assert.assertFalse(((CargoCarriage) train.getCarriage(1)).contains(cargo));
        }

    }

    @Test
    public void uploadCargos() {
        TrainImpl train = anyTrain();

        List<Cargo> cargos = List.of(Cargo.of(8),
                Cargo.of(CargoType.GAS, 15),
                Cargo.of(CargoType.FLUID, 4)
        );

        train.loadCagros(1, cargos);
        for (Cargo cargo : cargos) {
            Assert.assertTrue(((CargoCarriage) train.getCarriage(1)).contains(cargo));
        }

        train.uploadCargos(1, cargos);
        for (Cargo cargo : cargos) {
            Assert.assertFalse(((CargoCarriage) train.getCarriage(1)).contains(cargo));
        }
    }

    @Test
    public void dropOfPassenger() {
        TrainImpl train = anyTrain();

        Carriage secondCarriage = PassengerCarriage.of(30);
        Carriage thirdCarriage = CargoCarriage.of(50);
        Carriage fourthCarriage = PassengerCarriage.of(60);

        train.getFirstCarriage().setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);

        List<Passenger> passengers = anyPassengers();

        for (Passenger passenger : passengers) {
            train.addPassenger(passenger);
            Assert.assertTrue(((PassengerCarriage) train.getCarriage(2)).contains(passenger));

            train.dropOfPassenger(passenger);
            Assert.assertFalse(((PassengerCarriage) train.getCarriage(2)).contains(passenger));
        }
    }

    @Test
    public void dropOfPassengers() {
        TrainImpl train = anyTrain();

        Carriage secondCarriage = PassengerCarriage.of(30);
        Carriage thirdCarriage = CargoCarriage.of(50);
        Carriage fourthCarriage = PassengerCarriage.of(60);

        train.getFirstCarriage().setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);

        List<Passenger> passengers = anyPassengers();


        train.addPassengers(passengers);
        for (Passenger passenger : passengers) {
            Assert.assertTrue(((PassengerCarriage) train.getCarriage(2)).contains(passenger));
        }

        train.dropOfPassengers(passengers);
        for (Passenger passenger : passengers) {
            Assert.assertFalse(((PassengerCarriage) train.getCarriage(2)).contains(passenger));
        }
    }

    private TrainImpl anyTrain() {
        Locomotive locomotive = new Locomotive(400, 10,
                Driver.of(User.of("Nikita", "Puzikov", Age.of(40)), true));

        return new TrainImpl("123", locomotive, CargoCarriage.of(50));
    }

    private List<Passenger> anyPassengers() {
        return List.of(
                Passenger.of(User.of("Kirill", "Kaban", Age.of(20)),
                        Ticket.of("Minsk", "Grodno", new Date(), 2, 11)),
                Passenger.of(User.of("Alex", "Kasper", Age.of(57)),
                        Ticket.of("Minsk", "Grodno", new Date(), 2, 12)),
                Passenger.of(User.of("Nikita", "Gurumin", Age.of(40)),
                        Ticket.of("Minsk", "Grodno", new Date(), 2, 13))
                     );

    }
}