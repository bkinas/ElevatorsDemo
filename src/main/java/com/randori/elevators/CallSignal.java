/**
 * Created by kinas on 20.03.16.
 */

package com.randori.elevators;

public class CallSignal implements Signal {
    private final Controller controller;
    private final Integer floor;
    private final Direction direction;

    public CallSignal(Controller controller, Integer floor, Direction direction) {
        this.controller = controller;
        this.floor = floor;
        this.direction = direction;
    }

    public void send() {
        this.controller.add(this);
    }

    @Override
    public Integer getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}
