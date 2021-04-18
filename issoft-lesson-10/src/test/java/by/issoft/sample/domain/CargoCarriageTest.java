package by.issoft.sample.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;

public class CargoCarriageTest {

    @Mock
    private CargoCarriageImpl firstCargoCarriage;

    @Mock
    private CargoCarriageImpl secondCargoCarriage;

    @Mock
    private CargoCarriageImpl thirdCargoCarriage;

    @Mock
    Cargo firstCargo;

    @Mock
    Cargo secondCargo;

    @Before
    public void createData() {
        firstCargoCarriage = new CargoCarriageImpl(50,
                List.of(new Cargo(21), new Cargo(CargoType.GAS, 2)));

        secondCargoCarriage = new CargoCarriageImpl(80,
                List.of(new Cargo(CargoType.FLUID, 5), new Cargo(15)));

        thirdCargoCarriage = new CargoCarriageImpl(70);

        firstCargoCarriage.setNextCarriage(secondCargoCarriage);
        secondCargoCarriage.setNextCarriage(thirdCargoCarriage);

        firstCargo = new Cargo(17);
        secondCargo = new Cargo(CargoType.FLUID, 2);
    }

    @Test
    public void of() {
        firstCargoCarriage = CargoCarriageImpl.of(40, List.of(firstCargo, secondCargo));

        assertEquals(firstCargoCarriage.getCapacity(), 40);
        assertEquals(firstCargoCarriage.getFullness(), 19);

        assertNull(firstCargoCarriage.getNextCarriage());

        assertEquals(firstCargoCarriage.getCargos(), List.of(firstCargo, secondCargo));
    }

    @Test
    public void testAddCargo() {

        firstCargoCarriage.loadCargo(firstCargo);
        Assert.assertTrue(firstCargoCarriage.contains(firstCargo));
    }

    @Test
    public void testAddCargos() {

        secondCargoCarriage.loadCargos(List.of(firstCargo, secondCargo));

        Assert.assertTrue(secondCargoCarriage.contains(firstCargo));
        Assert.assertTrue(secondCargoCarriage.contains(secondCargo));
    }

    @Test
    public void testPickUpCargo() {
        firstCargoCarriage.loadCargo(firstCargo);
        Assert.assertTrue(firstCargoCarriage.contains(firstCargo));

        firstCargoCarriage.uploadCargo(firstCargo);
        Assert.assertFalse(firstCargoCarriage.contains(firstCargo));
    }

    @Test
    public void testPickUpCargos() {
        secondCargoCarriage.loadCargos(List.of(firstCargo, secondCargo));
        Assert.assertTrue(firstCargoCarriage.contains(firstCargo));
        Assert.assertTrue(firstCargoCarriage.contains(secondCargo));


        secondCargoCarriage.uploadCargos(List.of(firstCargo, secondCargo));

        Assert.assertFalse(firstCargoCarriage.contains(firstCargo));
        Assert.assertFalse(firstCargoCarriage.contains(secondCargo));
    }

    @Test
    public void testContainsCargo() {
        Assert.assertFalse(thirdCargoCarriage.contains(firstCargo));
        Assert.assertFalse(thirdCargoCarriage.contains(secondCargo));

        thirdCargoCarriage.loadCargo(firstCargo);
        Assert.assertTrue(thirdCargoCarriage.contains(firstCargo));

        thirdCargoCarriage.loadCargo(secondCargo);
        Assert.assertTrue(thirdCargoCarriage.contains(secondCargo));
    }

    @Test
    public void testGetFullness() {
        Assert.assertEquals(firstCargoCarriage.getFullness(), 23);
        Assert.assertEquals(secondCargoCarriage.getFullness(), 20);
        Assert.assertEquals(thirdCargoCarriage.getFullness(), 0);

        firstCargoCarriage.loadCargo(firstCargo);
        Assert.assertEquals(firstCargoCarriage.getFullness(), 40);

        firstCargoCarriage.loadCargo(secondCargo);
        Assert.assertEquals(firstCargoCarriage.getFullness(), 42);

        firstCargoCarriage.loadCargo(Cargo.of(8));
        Assert.assertEquals(firstCargoCarriage.getFullness(), 50);
    }

    @Test
    public void testLink() {
        assertEquals(firstCargoCarriage.getNextCarriage(), secondCargoCarriage);
        assertEquals(secondCargoCarriage.getNextCarriage(), thirdCargoCarriage);
        assertNull(thirdCargoCarriage.getNextCarriage());
    }
}