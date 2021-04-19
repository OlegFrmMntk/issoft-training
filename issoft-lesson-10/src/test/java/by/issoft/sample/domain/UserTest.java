package by.issoft.sample.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class UserTest {

    @Test
    public void of() {

        User firstUser = new User("Alex", "Kondrashov", Age.of(40));

        User secondUser = User.of("Alex", "Kondrashov", Age.of(40));

        assertEquals(firstUser.getFirstName(), secondUser.getFirstName());
        assertEquals(firstUser.getLastName(), secondUser.getLastName());

        assertEquals(firstUser.getAge(), secondUser.getAge());

        assertNotSame(firstUser, secondUser);
    }

}