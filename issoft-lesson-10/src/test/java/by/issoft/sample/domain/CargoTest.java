package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CargoTest {

    @Test
    public void of() {
        Cargo firstCargo = new Cargo(CargoType.GAS, 30);
        Cargo secondCargo = Cargo.of(CargoType.GAS, 30);

        assertEquals(firstCargo.getType(), secondCargo.getType());
        assertEquals(firstCargo.getWeight(), secondCargo.getWeight());

        assertNotSame(firstCargo, secondCargo);
    }

    @Test
    public void getWeight() {
        Cargo cargo;
        for (int weight = 1; weight < 1000; weight++) {
            cargo = new Cargo(weight);

            Assert.assertEquals(cargo.getWeight(), weight);
        }
    }

    @Test
    public void getType() {
        Cargo cargo = new Cargo(1);
        Assert.assertEquals(cargo.getType(), CargoType.SOLID);

        cargo = new Cargo(CargoType.SOLID, 1);
        Assert.assertEquals(cargo.getType(), CargoType.SOLID);

        cargo = new Cargo(CargoType.GAS, 1);
        Assert.assertEquals(cargo.getType(), CargoType.GAS);

        cargo = new Cargo(CargoType.FLUID, 1);
        Assert.assertEquals(cargo.getType(), CargoType.FLUID);
    }

    @Test
    public void equals() {

        Cargo firstCargo = new Cargo(CargoType.SOLID, 1);
        Cargo secondCargo = new Cargo(CargoType.SOLID, 1);
        Cargo thirdCargo = new Cargo(CargoType.SOLID, 1);

        assertEquals(firstCargo, firstCargo);

        Assert.assertEquals(firstCargo.equals(secondCargo), secondCargo.equals(firstCargo));

        if (firstCargo.equals(secondCargo) == secondCargo.equals(thirdCargo)) {
            assertEquals(firstCargo, thirdCargo);
        }
        else {
            assertNotEquals(firstCargo, thirdCargo);
        }

        assertEquals(firstCargo.equals(secondCargo), firstCargo.equals(secondCargo));

        assertNotEquals(null, firstCargo);

        secondCargo = new Cargo(CargoType.GAS, 1);

        assertNotEquals(firstCargo, secondCargo);
        assertNotEquals(thirdCargo, secondCargo);

        thirdCargo = new Cargo(CargoType.SOLID, 2);

        assertNotEquals(firstCargo, thirdCargo);
        assertNotEquals(secondCargo, thirdCargo);

    }
}