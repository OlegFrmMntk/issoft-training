package by.issoft.sample.persistence;

import by.issoft.sample.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class StatisticItem {
    String action;

    Person person;

    int floorNumber;
}
