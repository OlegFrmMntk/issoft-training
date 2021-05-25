package by.issoft.sample;

import by.issoft.sample.domain.*;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        int floorsNumber = randomFloorNumber();

        Floor[] floors = makeRandomFloors(floorsNumber);

        PeopleSpawner[] peopleSpawners = makeRandomPeopleSpawners(floors);

        int liftsNumber = randomLiftNumber();

        Lift[] lifts = makeRandomLifts(liftsNumber);

        LiftController[] liftControllers = makeLiftControllers(lifts, floors);

        LiftsController liftsController = LiftsController.of(liftControllers, floors);

        Building building = Building.of("1", floorsNumber, floors, peopleSpawners, liftsNumber, lifts, liftsController);

        building.start();
    }

    private static int randomFloorNumber() {
        return (int) (20);
    }

    private static Floor[] makeRandomFloors(int floorsNumber) {
        return IntStream
                .range(0, floorsNumber)
                .mapToObj(i -> Floor.of(i + 1, LiftButton.of((i + 1) + "UP"), LiftButton.of((i + 1) + "DOWN")))
                .toArray(Floor[]::new);
    }

    private static PeopleSpawner[] makeRandomPeopleSpawners(Floor[] floors) {
        return Arrays
                .stream(floors)
                .map(floor -> PeopleSpawner.of(floor, floors.length, (int) (Math.random() * 5)))
                .toArray(PeopleSpawner[]::new);
    }

    private static int randomLiftNumber() {
        return (int) (Math.random() * 5);
    }

    private static Lift[] makeRandomLifts(int liftsNumber) {
        return IntStream
                .range(0, liftsNumber)
                .mapToObj(i -> Lift.of((i + 1) + "LIFT", (int) (Math.random() * 1000), (int) (Math.random() * 1000), (int) (Math.random() * 500)))
                .toArray(Lift[]::new);
    }

    private static LiftController[] makeLiftControllers(Lift[] lifts, Floor[] floors) {
        return Arrays
                .stream(lifts)
                .map(lift -> LiftController.of(lift, floors))
                .toArray(LiftController[]::new);
    }
}
