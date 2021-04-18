package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TrainImplTest {

    @Mock
    private TrainImpl firstTrain;

    @Mock
    private TrainImpl secondTrain;

    @Mock
    private Locomotive locomotive;

    @Mock
    private Carriage firstCarriage;

    @Mock
    private Carriage secondCarriage;

    @Mock
    private Carriage thirdCarriage;

    @Mock
    private Carriage fourthCarriage;

    @Mock
    private Carriage fifthCarriage;

    @Mock
    private List<Cargo> cargos;

    @Mock
    private List<Passenger> passengers;

    @Before
    public void createData() {

        cargos = List.of(Cargo.of(CargoType.SOLID, 2), Cargo.of(CargoType.GAS, 4),
                Cargo.of(CargoType.FLUID, 6));

        passengers = List.of(
                Passenger.of("Kirill", "Kaban", Age.of(20),
                        Ticket.of("Minsk", "Grodno", new Date(), 2, 11)),
                Passenger.of("Alex", "Kasper", Age.of(57),
                        Ticket.of("Minsk", "Grodno", new Date(), 2, 12)),
                Passenger.of("Nikita", "Gurumin", Age.of(40),
                        Ticket.of("Minsk", "Grodno", new Date(), 2, 13)));

        firstCarriage = new CargoCarriageImpl(50,
                List.of(new Cargo(21), new Cargo(CargoType.GAS, 2)));

        secondCarriage = new PassengerCarriageImpl(50,
                List.of(Passenger.of("Alex", "Kondrashov", Age.of(40),
                Ticket.of("Minsk", "Grodno", new Date(), 1, 1))));

        thirdCarriage = new CargoCarriageImpl(80,
                List.of(new Cargo(CargoType.FLUID, 5), new Cargo(15)));

        fourthCarriage = new PassengerCarriageImpl(50,
                List.of(Passenger.of("Ivan", "Ivanov", Age.of(40),
                        Ticket.of("Minsk", "Grodno", new Date(), 4, 1))));

        fifthCarriage = new CargoCarriageImpl(43);


        locomotive = new Locomotive(400, 10,
                Driver.of("Nikita", "Puzikov", Age.of(40), true));

        firstTrain = new TrainImpl("123", locomotive, firstCarriage);
        secondTrain = TrainImpl.of("123", locomotive, firstCarriage);
    }

    @Test
    public void of() {
        assertEquals(firstTrain.getNumber(), secondTrain.getNumber());
        assertEquals(firstTrain.getFirstCarriage(), secondTrain.getFirstCarriage());
        assertEquals(firstTrain.getCarriage(1), secondTrain.getCarriage(1));
        assertEquals(firstTrain.getLocomotive(), secondTrain.getLocomotive());

        assertEquals(firstTrain, secondTrain);
    }

    @Test
    public void addCarriage() {
        firstTrain.addCarriage(secondCarriage);

        assertEquals(firstTrain.getCarriage(2), secondCarriage);
    }

    @Test
    public void addCarriages() {
        firstTrain.addCarriages(List.of(thirdCarriage, fourthCarriage, fifthCarriage));

        assertEquals(firstTrain.getCarriage(2), thirdCarriage);
        assertEquals(firstTrain.getCarriage(3), fourthCarriage);
        assertEquals(firstTrain.getCarriage(4), fifthCarriage);
    }

    @Test
    public void loadCargo() {
        firstCarriage.setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);
        fourthCarriage.setNextCarriage(fifthCarriage);

        int i = 1;
        for (Cargo cargo : cargos) {
            firstTrain.loadCargo(i, cargo);
            assertTrue(((CargoCarriageImpl) firstTrain.getCarriage(i)).contains(cargo));
            i += 2;
        }
    }


    @Test
    public void loadCargos() {
        firstCarriage.setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);
        fourthCarriage.setNextCarriage(fifthCarriage);


        firstTrain.loadCagros(1, cargos);
        for (Cargo cargo : cargos) {
            assertTrue(((CargoCarriageImpl) firstTrain.getFirstCarriage()).contains(cargo));
        }
    }

    @Test
    public void addPassenger() {
        firstCarriage.setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);

        for (Passenger passenger : passengers) {
            firstTrain.addPassenger(passenger);
            assertTrue(((PassengerCarriageImpl) firstTrain.getCarriage(2)).contains(passenger));
        }
    }

    @Test
    public void addPassengers() {
        firstCarriage.setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);
        fourthCarriage.setNextCarriage(fifthCarriage);

        firstTrain.addPassengers(passengers);
        for (Passenger passenger : passengers) {
            assertTrue(((PassengerCarriageImpl) firstTrain.getCarriage(2)).contains(passenger));
        }
    }

    @Test
    public void uploadCargo() {

        for (Cargo cargo : cargos) {
            firstTrain.loadCargo(1, cargo);
            Assert.assertTrue(((CargoCarriageImpl) firstTrain.getCarriage(1)).contains(cargo));

            firstTrain.uploadCargo(1, cargo);
            Assert.assertFalse(((CargoCarriageImpl) firstTrain.getCarriage(1)).contains(cargo));
        }

    }

    @Test
    public void uploadCargos() {

        firstTrain.loadCagros(1, cargos);
        for (Cargo cargo : cargos) {
            Assert.assertTrue(((CargoCarriageImpl) firstTrain.getCarriage(1)).contains(cargo));
        }

        firstTrain.uploadCargos(1, cargos);
        for (Cargo cargo : cargos) {
            Assert.assertFalse(((CargoCarriageImpl) firstTrain.getCarriage(1)).contains(cargo));
        }

    }

    @Test
    public void dropOfPassenger() {
        firstCarriage.setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);
        fourthCarriage.setNextCarriage(fifthCarriage);

        for (Passenger passenger : passengers) {
            firstTrain.addPassenger(passenger);
            Assert.assertTrue(((PassengerCarriageImpl) firstTrain.getCarriage(2)).contains(passenger));

            firstTrain.dropOfPassenger(passenger);
            Assert.assertFalse(((PassengerCarriageImpl) firstTrain.getCarriage(2)).contains(passenger));
        }
    }

    @Test
    public void dropOfPassengers() {
        firstCarriage.setNextCarriage(secondCarriage);
        secondCarriage.setNextCarriage(thirdCarriage);
        thirdCarriage.setNextCarriage(fourthCarriage);
        fourthCarriage.setNextCarriage(fifthCarriage);

        firstTrain.addPassengers(passengers);
        for (Passenger passenger : passengers) {
            Assert.assertTrue(((PassengerCarriageImpl) firstTrain.getCarriage(2)).contains(passenger));
        }

        firstTrain.dropOfPassengers(passengers);
        for (Passenger passenger : passengers) {
            Assert.assertFalse(((PassengerCarriageImpl) firstTrain.getCarriage(2)).contains(passenger));
        }
    }
}