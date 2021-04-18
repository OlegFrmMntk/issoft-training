package by.issoft.sample.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AgeTest {

    @Mock
    private Age age;

    @Test
    void ofAndIntValue() {
        for (int anyAge = 0; anyAge <= 125; anyAge++) {
            age = Age.of(anyAge);

            Assertions.assertEquals(age.intValue(), anyAge);
        }
    }

}