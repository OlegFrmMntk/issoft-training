package by.issoft.sample.persistence;

import by.issoft.sample.data.Order;
import org.junit.jupiter.api.Test;

import static by.issoft.sample.data.OrderTestSamples.anyValidOrder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderStorageTest {

    private final OrderStorage orderStorage = new OrderStorage();

    @Test
    public void testPersist() {
        Order order = anyValidOrder();

        final String id = orderStorage.persist(order);
        assertThat(id, is(not(null)));

        final Order loaded = orderStorage.load(id);
        assertThat(loaded, is(equalTo(order)));
    }

    @Test
    public void testPersist_null() {
        assertThrows(NullPointerException.class, () -> orderStorage.persist(null));
    }

}