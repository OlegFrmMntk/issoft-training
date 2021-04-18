package by.issoft.sample.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class UserTest {

    @Mock
    User firstUser;

    @Mock
    User secondUser;

    @Before
    public void createData() {

        firstUser = new User("Alex", "Kondrashov", Age.of(40));

        secondUser = User.of("Alex", "Kondrashov", Age.of(40));;
    }

    @Test
    public void of() {
        assertEquals(firstUser.getFirstName(), secondUser.getFirstName());
        assertEquals(firstUser.getLastName(), secondUser.getLastName());

        assertEquals(firstUser.getAge(), secondUser.getAge());

        assertNotSame(firstUser, secondUser);
    }
}