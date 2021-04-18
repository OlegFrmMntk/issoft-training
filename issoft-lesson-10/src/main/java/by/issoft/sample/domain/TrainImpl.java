package by.issoft.sample.domain;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public class TrainImpl implements Train {

    final private String number;

    private Locomotive locomotive;

    private Carriage firstCarriage;

    private static final Logger logger = Logger.getLogger(TrainImpl.class);

    public TrainImpl(String number, Locomotive locomotive) {
        this(number, locomotive, null);
    }

    public TrainImpl(String number, Locomotive locomotive, Carriage firstCarriage) {
        this.number = number;
        this.locomotive = locomotive;
        this.firstCarriage = firstCarriage;
    }

    public static TrainImpl of(String number, Locomotive locomotive) {
        return new TrainImpl(number, locomotive, null);
    }

    public static TrainImpl of(String number, Locomotive locomotive, Carriage firstCarriage) {
        return new TrainImpl(number, locomotive, firstCarriage);
    }

    public String getNumber() {
        return number;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public Carriage getFirstCarriage() {
        return firstCarriage;
    }

    public void setFirstCarriage(Carriage firstCarriage) {
        this.firstCarriage = firstCarriage;
    }

    public void addCarriage(Carriage carriage) {
        addCarriages(List.of(carriage));
    }

    public void addCarriages(List<Carriage> carriages) {

        Carriage tail = firstCarriage;
        while (tail.getNextCarriage() != null) {
            tail = tail.getNextCarriage();
        }

        tail.setNextCarriage(carriages.get(0));
        logger.info(String.format("%s = %s added to train", carriages.get(0).getClass(), carriages.get(0).getId()));

        for (int i = 1; i < carriages.size(); i++) {
            carriages.get(i - 1).setNextCarriage(carriages.get(i));
            logger.info(String.format("%s = %s added to train", carriages.get(i).getClass(), carriages.get(i).getId()));
        }
    }

    public Carriage getCarriage(int number) {

        Carriage carriage = firstCarriage;
        number--;

        while (number > 0 && carriage != null) {
            carriage = carriage.getNextCarriage();
            number--;
        }

        checkArgument(carriage != null, "by.issoft.sample.domain.Train the carriage doesn't exist");

        return carriage;
    }

    public Carriage unhookLastCarriage() {

        Carriage prevLastCar = firstCarriage;

        checkArgument(firstCarriage != null,
                "by.issoft.sample.domain.Train carriage number must be > 0");

        while (prevLastCar.getNextCarriage().getNextCarriage() != null) {
            prevLastCar = prevLastCar.getNextCarriage();
        }

        Carriage lastCar = prevLastCar.getNextCarriage();
        prevLastCar.setNextCarriage(null);

        logger.info(String.format("%s = %s detached from the train", lastCar.getClass(), lastCar.getId()));

        return lastCar;
    }

    public void loadCargo(Cargo cargo) {
        Carriage carriage = firstCarriage;

        boolean key = false;
        while (carriage != null) {
            if (carriage.getClass().equals(CargoCarriageImpl.class) &&
                    carriage.getCapacity() - carriage.getFullness() >= cargo.getWeight()) {

                ((CargoCarriageImpl) carriage).loadCargo(cargo);
                key = true;

                logger.info(String.format("%s = %s, Cargo = %s added", carriage.getClass(), carriage.getId(), cargo));

                break;
            }

            carriage = carriage.getNextCarriage();
        }

        checkArgument(key, "by.issoft.sample.domain.Train Not enough space");
    }

    public void loadCargo(int carriageNumber, Cargo cargo) {
        Carriage carriage = getCarriage(carriageNumber);

        checkArgument(carriage.getClass().equals(CargoCarriageImpl.class),
                "by.issoft.sample.domain.Train The carriage is not a cargo carriage");

        ((CargoCarriageImpl) carriage).loadCargo(cargo);

        logger.info(String.format("%s = %s, Cargo = %s added", carriage.getClass(), carriage.getId(), cargo));
    }

    public void loadCagros(int carriageNumber, List<Cargo> cargos) {

        int weight = 0;
        for (Cargo cargo : cargos) {
            weight += cargo.getWeight();
        }

        checkArgument(weight <= (getCarriage(carriageNumber).getCapacity() - getCarriage(carriageNumber).getFullness()),
                "by.issoft.sample.domain.Train Not enough space");

        for (Cargo cargo : cargos) {
            loadCargo(carriageNumber, cargo);
        }
    }

    public void loadCagros(List<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            loadCargo(cargo);
        }
    }

    public void uploadCargo(int carriageNumber, Cargo cargo) {
        Carriage carriage = getCarriage(carriageNumber);

        checkArgument(carriage.getClass().equals(CargoCarriageImpl.class),
                "by.issoft.sample.domain.Train The carriage is not a cargo carriage");

        ((CargoCarriageImpl) carriage).uploadCargo(cargo);

        logger.info(String.format("%s = %s, Cargo = %s uploaded", carriage.getClass(), carriage.getId(), cargo));
    }

    public void uploadCargos(int carriageNumber, List<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            uploadCargo(carriageNumber, cargo);
        }
    }

    public void addPassenger(Passenger passenger) {
        Carriage carriage = getCarriage(passenger.getTicket().getCarriageNumber());

        checkArgument(carriage.getClass().equals(PassengerCarriageImpl.class),
                "by.issoft.sample.domain.Train The carriage is not a passenger carriage");

        ((PassengerCarriageImpl) carriage).addPassenger(passenger);

        logger.info(String.format("%s = %s, Passenger = %s added", carriage.getClass(), carriage.getId(), passenger.getId()));
    }

    public void addPassengers(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            addPassenger(passenger);
        }
    }

    public void dropOfPassenger(Passenger passenger) {
        Carriage carriage = getCarriage(passenger.getTicket().getCarriageNumber());

        checkArgument(carriage.getClass().equals(PassengerCarriageImpl.class),
                "by.issoft.sample.domain.Train The carriage is not a passenger carriage");

        ((PassengerCarriageImpl) carriage).dropOffPassenger(passenger);

        logger.info(String.format("%s = %s, Passenger = %s dropped", carriage.getClass(), carriage.getId(), passenger.getId()));
    }

    public void dropOfPassengers(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            dropOfPassenger(passenger);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainImpl train = (TrainImpl) o;
        return Objects.equals(number, train.number) && Objects.equals(locomotive, train.locomotive) &&
                Objects.equals(firstCarriage, train.firstCarriage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, locomotive, firstCarriage);
    }

    @Override
    public String toString() {
        return String.format("Train{number = %s, locomotive = %s, firstCarriage = %s}",
                number, locomotive.getId(), firstCarriage.getId());
    }
}
