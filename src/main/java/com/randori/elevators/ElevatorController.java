package com.randori.elevators;

/**
 * Created by kinas on 21.03.16.
 */
public class ElevatorController implements Controller {
    private final Elevator elevator1;
    private final Elevator elevator2;

    public ElevatorController(Elevator elevator1, Elevator elevator2) {
        this.elevator1 = elevator1;
        this.elevator2 = elevator2;
    }

    @Override
    public void add(Signal signal) {
        Integer floor = signal.getFloor();
        Integer elevator1Floor = elevator1.getFloor();
        Integer elevator2Floor = elevator2.getFloor();
        if (Math.abs(elevator1Floor - floor) < Math.abs(elevator2Floor - floor)) {
            elevator1.sendTo(floor);
        } else {
            elevator2.sendTo(floor);
        }

    }
}
