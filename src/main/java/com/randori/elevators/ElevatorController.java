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
    public void process(Signal signal) {
        calculateCloserElevator(signal).add(signal);
    }

    private Elevator calculateCloserElevator(Signal signal) {
        Elevator closerElevator = calculateFirstUnusedElevator();

        if (closerElevator == null) {
            closerElevator = calculateQuickestElevator(signal);
        }
        return closerElevator;
    }

    private Elevator calculateQuickestElevator(Signal signal) {
        int requestPosition1 = elevator1.getRequestPosition(signal);
        int requestPosition2 = elevator2.getRequestPosition(signal);
        return requestPosition1 <= requestPosition2 ? elevator1 : elevator2;
    }

    private Elevator calculateFirstUnusedElevator() {
        if (elevator1.countRequests() == 0) {
            return elevator1;
        } else if (elevator2.countRequests() == 0) {
            return elevator2;
        }
        return null;
    }
}
