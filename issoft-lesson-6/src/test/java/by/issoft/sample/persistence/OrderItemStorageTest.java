package by.issoft.sample.persistence;

import by.issoft.sample.data.OrderItem;
import org.junit.jupiter.api.Test;

import static by.issoft.sample.data.OrderItemTestSamples.anyValidOrderItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemStorageTest {

    private final OrderItemStorage orderItemStorage = new OrderItemStorage();

    @Test
    public void testPersist() {
        OrderItem orderItem = anyValidOrderItem();

        final String id = orderItemStorage.persist(orderItem);
        assertThat(id, is(not(null)));

        final OrderItem loaded = orderItemStorage.load(id);
        assertThat(loaded, is(equalTo(orderItem)));
    }

    @Test
    public void testPersist_null() {
        assertThrows(NullPointerException.class, () -> orderItemStorage.persist(null));
    }

}