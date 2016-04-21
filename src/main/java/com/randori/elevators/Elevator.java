package com.randori.elevators;

/**
 * Created by kinas on 21.03.16.
 */
public interface Elevator {
    void add(Signal signal);

    int countRequests();

    int getRequestPosition(Signal signal);

    void run();
}
