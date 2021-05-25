package by.issoft.sample.sample;

import by.issoft.sample.domain.Gender;
import by.issoft.sample.domain.Person;

import java.time.LocalDate;
import java.util.UUID;

public class PersonTestSamples {

    public static Person anyValidPerson() {
        return Person.of("1", "Oleg", "Man", LocalDate.of(2000, 9, 21),
                Gender.MALE, 12.3F, 7);
    }

    public static Person validPerson(float weight, int needFloor) {
        return Person.of(
                UUID.randomUUID().toString(),
                "Oleg",
                "Mankevich",
                LocalDate.of(2000, 9, 21),
                Gender.MALE,
                weight,
                needFloor
        );
    }

    public static Person anyPerson() {
        return anyValidPerson();
    }

}
