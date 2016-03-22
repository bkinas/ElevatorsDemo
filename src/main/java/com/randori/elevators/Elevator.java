package com.randori.elevators;

/**
 * Created by kinas on 21.03.16.
 */
public interface Elevator {
    void sendTo(Integer requestedFloor);

    Integer getFloor();
}
