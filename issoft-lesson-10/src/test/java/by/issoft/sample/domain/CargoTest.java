package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CargoTest {

    @Mock
    private Cargo firstCargo;

    @Mock
    private Cargo secondCargo;

    @Mock
    private Cargo thirdCargo;

    @Test
    public void of() {
        firstCargo = new Cargo(CargoType.GAS, 30);
        secondCargo = Cargo.of(CargoType.GAS, 30);

        assertEquals(firstCargo.getType(), secondCargo.getType());
        assertEquals(firstCargo.getWeight(), secondCargo.getWeight());

        assertNotSame(firstCargo, secondCargo);
    }

    @Test
    public void getWeight() {
        for (int weight = 1; weight < 1000; weight++) {
            firstCargo = new Cargo(weight);

            Assert.assertEquals(firstCargo.getWeight(), weight);
        }
    }

    @Test
    public void getType() {

        firstCargo = new Cargo(1);
        Assert.assertEquals(firstCargo.getType(), CargoType.SOLID);

        firstCargo = new Cargo(CargoType.SOLID, 1);
        Assert.assertEquals(firstCargo.getType(), CargoType.SOLID);

        firstCargo = new Cargo(CargoType.GAS, 1);
        Assert.assertEquals(firstCargo.getType(), CargoType.GAS);

        firstCargo = new Cargo(CargoType.FLUID, 1);
        Assert.assertEquals(firstCargo.getType(), CargoType.FLUID);
    }

    @Test
    public void testEquals() {

        firstCargo = new Cargo(CargoType.SOLID, 1);
        secondCargo = new Cargo(CargoType.SOLID, 1);
        thirdCargo = new Cargo(CargoType.SOLID, 1);

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