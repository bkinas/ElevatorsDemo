package com.randori.elevators;

/**
 * Created by wrkj83 on 2016-04-22.
 */
public class ElevatorController implements Controller {

    private Elevator e1;
    private Elevator e2;

    public ElevatorController(Elevator e1, Elevator e2) {

        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void process(Signal signal) {
        if(e1.countUnprocessSignals() == 0) {
            e1.add(signal);
        } else if (e2.countUnprocessSignals() == 0){
            e2.add(signal);
        }
    }
}

