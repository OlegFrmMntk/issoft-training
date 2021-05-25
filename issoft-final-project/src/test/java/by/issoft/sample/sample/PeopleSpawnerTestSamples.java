package by.issoft.sample.sample;

import by.issoft.sample.domain.PeopleSpawner;

import static by.issoft.sample.sample.FloorTestSamples.anyFloor;

public class PeopleSpawnerTestSamples {

    public static PeopleSpawner anyValidPeopleSpawner() {
        return PeopleSpawner.of(anyFloor(), 10, 5);
    }

    public static PeopleSpawner validPeopleSpawner(int spawnIntensity) {
        return PeopleSpawner.of(anyFloor(), 10, spawnIntensity);
    }

    public static PeopleSpawner anyPeopleSpawner() {
        return anyValidPeopleSpawner();
    }

}
