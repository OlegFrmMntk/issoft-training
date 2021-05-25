package by.issoft.sample.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName="of")
public class Person {

    @NonNull
    private final String id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private final LocalDate birthDate;

    @NonNull
    private final Gender gender;

    private float weight;

    private final int needFloor;
}
