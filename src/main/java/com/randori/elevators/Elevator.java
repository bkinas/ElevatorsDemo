package com.randori.elevators;

/**
 * Created by kinas on 21.03.16.
 */
public interface Elevator {
    void add(Signal signal);

    int countUnprocessSignals();

    int checkNewSignalProcessingOrder(Signal signal);

    void run();
}
