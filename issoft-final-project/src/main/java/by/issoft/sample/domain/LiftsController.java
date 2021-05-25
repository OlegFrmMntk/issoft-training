package by.issoft.sample.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class LiftsController implements Runnable {

    private final LiftController[] controllers;

    private final Floor[] floors;

    private boolean isFinished = false;

    @Override
    public void run() {

        setStartLiftsPositions();

        ExecutorService liftsService = Executors.newFixedThreadPool(controllers.length);
        Arrays.stream(controllers).forEach(liftsService::submit);

        while (!isFinished()) {

            Arrays.stream(floors).filter(floor -> floor.getUpButton().isPressed()).forEach(floor -> {
                Optional<LiftController> liftController = getNearestDownLift(floor.getNumber());
                liftController.ifPresent(controller -> controller.getLift().setAimFloorNumber(floor.getNumber()));

            });

            Arrays.stream(floors).filter(floor -> floor.getDownButton().isPressed()).forEach(floor -> {
                Optional<LiftController> liftController = getNearestUpLift(floor.getNumber());
                liftController.ifPresent(controller -> controller.getLift().setAimFloorNumber(floor.getNumber()));
            });
        }

       liftsService.shutdown();
    }


    public void finish() {
        isFinished = true;
    }

    public void setStartLiftsPositions() {
        if (controllers.length == 1) {
            controllers[0].getLift().setStartPosition(1);
        }
        else if (controllers.length == 2) {
            controllers[0].getLift().setStartPosition(1);
            controllers[1].getLift().setStartPosition(floors.length / 2);
        }
        else {
            int period = floors.length / controllers.length;

            int floorNumber = 1;
            for (LiftController controller : controllers) {
                controller.getLift().setStartPosition(floorNumber);
                floorNumber += period;
            }
        }
    }

    public Optional<LiftController> getNearestDownLift(int floorNumber) {
        return Arrays.stream(controllers)
                .filter(controller -> controller.getLift().getFloorNumber() >= floorNumber && controller.isFree())
                .min(Comparator.comparingInt(o -> o.getLift().getFloorNumber()));
    }

    public Optional<LiftController> getNearestUpLift(int floorNumber) {
        return Arrays.stream(controllers)
                .filter(controller -> controller.getLift().getFloorNumber() <= floorNumber  && controller.isFree())
                .max(Comparator.comparingInt(o -> o.getLift().getFloorNumber()));
    }
}
