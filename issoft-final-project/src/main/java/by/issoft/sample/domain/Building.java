package by.issoft.sample.domain;

import lombok.*;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ToString
@Getter
@EqualsAndHashCode(callSuper = false)
public class Building {

    private final String number;

    private final int floorsNumber;

    private final Floor[] floors;

    private final PeopleSpawner[] peopleSpawners;

    private final int liftsNumber;

    private final Lift[] lifts;

    private final LiftsController liftsController;

    private final ExecutorService floorsService;

    private final ExecutorService liftsControllerService;

    private final ExecutorService peopleSpawnersService;


    public Building(String number, int floorsNumber, Floor[] floors, PeopleSpawner[] peopleSpawners,
                    int liftsNumber, Lift[] lifts, LiftsController liftsController) {
        this.number = number;
        this.floorsNumber = floorsNumber;
        this.floors = floors;
        this.peopleSpawners = peopleSpawners;
        this.liftsNumber = liftsNumber;
        this.lifts = lifts;
        this.liftsController = liftsController;

        floorsService = Executors.newFixedThreadPool(floorsNumber);
        liftsControllerService = Executors.newSingleThreadExecutor();
        peopleSpawnersService = Executors.newFixedThreadPool(floorsNumber);
    }

    public static Building of(String number, int floorsNumber, Floor[] floors, PeopleSpawner[] peopleSpawners,
                          int liftsNumber, Lift[] lifts, LiftsController liftsController) {
        return new Building(number, floorsNumber, floors, peopleSpawners, liftsNumber, lifts, liftsController);
    }

    public void start() {
        Arrays.stream(floors).forEach(floorsService::submit);

        liftsControllerService.submit(liftsController);

        Arrays.stream(peopleSpawners).forEach(peopleSpawnersService::submit);
    }

    public void finish() {
        peopleSpawnersService.shutdown();
        floorsService.shutdown();
        liftsControllerService.shutdown();
    }

}
