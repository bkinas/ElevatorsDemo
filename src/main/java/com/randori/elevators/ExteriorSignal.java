/**
 * Created by kinas on 20.03.16.
 */

package com.randori.elevators;

public class ExteriorSignal implements Signal {
    private final Controller controller;
    private final Integer floor;
    private final Direction direction;

    public ExteriorSignal(Controller controller, Integer floor, Direction direction) {
        this.controller = controller;
        this.floor = floor;
        this.direction = direction;
    }

    public void send() {
        this.controller.process(this);
    }

    @Override
    public Integer getFloor() {
        return floor;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ExteriorSignal that = (ExteriorSignal) o;

        return floor.equals(that.floor) && direction == that.direction;

    }

    @Override
    public int hashCode() {
        int result = floor.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Signal o) {
        if (this.equals(o)) {
            return 0;
        } else {
            return this.floor > o.getFloor() ? 1 : -1;
        }
    }
}
