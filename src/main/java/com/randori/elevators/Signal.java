package com.randori.elevators;

/**
 * Created by kinas on 20.03.16.
 */
public interface Signal extends Comparable<Signal> {
    Integer getFloor();

    Direction getDirection();
}
