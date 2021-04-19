package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CargoCarriageTest {

    @Test
    public void of() {
        Cargo firstCargo = Cargo.of(CargoType.FLUID, 12);
        Cargo secondCargo = Cargo.of(CargoType.GAS, 9);

        CargoCarriage cargoCarriage = CargoCarriage.of(40, List.of(firstCargo, secondCargo));

        assertEquals(cargoCarriage.getCapacity(), 40);
        assertEquals(cargoCarriage.getFullness(), 21);

        assertNull(cargoCarriage.getNextCarriage());

        assertEquals(cargoCarriage.getCargos(), List.of(firstCargo, secondCargo));
    }

    @Test
    public void addCargo() {
        CargoCarriage cargoCarriage = CargoCarriage.of(50);

        Cargo cargo = Cargo.of(CargoType.SOLID, 24);

        cargoCarriage.loadCargo(cargo);
        Assert.assertTrue(cargoCarriage.contains(cargo));
    }

    @Test
    public void addCargos() {
        CargoCarriage cargoCarriage = CargoCarriage.of(50);

        Cargo firstCargo = Cargo.of(CargoType.GAS, 17);
        Cargo secondCargo = Cargo.of(CargoType.FLUID, 10);

        cargoCarriage.loadCargos(List.of(firstCargo, secondCargo));

        Assert.assertTrue(cargoCarriage.contains(firstCargo));
        Assert.assertTrue(cargoCarriage.contains(secondCargo));
    }

    @Test
    public void pickUpCargo() {
        CargoCarriage cargoCarriage = CargoCarriage.of(50);

        Cargo cargo = Cargo.of(CargoType.SOLID, 18);

        cargoCarriage.loadCargo(cargo);
        Assert.assertTrue(cargoCarriage.contains(cargo));

        cargoCarriage.uploadCargo(cargo);
        Assert.assertFalse(cargoCarriage.contains(cargo));
    }

    @Test
    public void pickUpCargos() {
        CargoCarriage cargoCarriage = CargoCarriage.of(50);

        Cargo firstCargo = Cargo.of(17);
        Cargo secondCargo = Cargo.of(24);

        cargoCarriage.loadCargos(List.of(firstCargo, secondCargo));

        Assert.assertTrue(cargoCarriage.contains(firstCargo));
        Assert.assertTrue(cargoCarriage.contains(secondCargo));

        cargoCarriage.uploadCargos(List.of(firstCargo, secondCargo));

        Assert.assertFalse(cargoCarriage.contains(firstCargo));
        Assert.assertFalse(cargoCarriage.contains(secondCargo));
    }

    @Test
    public void containsCargo() {
        CargoCarriage cargoCarriage = CargoCarriage.of(50);

        Cargo firstCargo = Cargo.of(CargoType.SOLID, 20);
        Cargo secondCargo = Cargo.of(CargoType.GAS, 10);

        Assert.assertFalse(cargoCarriage.contains(firstCargo));
        Assert.assertFalse(cargoCarriage.contains(secondCargo));

        cargoCarriage.loadCargo(firstCargo);
        Assert.assertTrue(cargoCarriage.contains(firstCargo));

        cargoCarriage.loadCargo(secondCargo);
        Assert.assertTrue(cargoCarriage.contains(secondCargo));
    }

    @Test
    public void getFullness() {
        CargoCarriage firstCargoCarriage = CargoCarriage.of(70, List.of(Cargo.of(21),
                Cargo.of(CargoType.GAS, 2)));

        Assert.assertEquals(firstCargoCarriage.getFullness(), 23);

        firstCargoCarriage.loadCargo(Cargo.of(17));
        Assert.assertEquals(firstCargoCarriage.getFullness(), 40);

        firstCargoCarriage.loadCargo(Cargo.of(24));
        Assert.assertEquals(firstCargoCarriage.getFullness(), 64);

        firstCargoCarriage.loadCargo(Cargo.of(6));
        Assert.assertEquals(firstCargoCarriage.getFullness(), 70);
    }

    @Test
    public void link() {
        CargoCarriage firstCargoCarriage = CargoCarriage.of(50);
        CargoCarriage secondCargoCarriage = CargoCarriage.of(50);
        CargoCarriage thirdCargoCarriage = CargoCarriage.of(50);

        firstCargoCarriage.setNextCarriage(secondCargoCarriage);
        assertEquals(firstCargoCarriage.getNextCarriage(), secondCargoCarriage);

        secondCargoCarriage.setNextCarriage(thirdCargoCarriage);
        assertEquals(secondCargoCarriage.getNextCarriage(), thirdCargoCarriage);

        assertNull(thirdCargoCarriage.getNextCarriage());
    }
}