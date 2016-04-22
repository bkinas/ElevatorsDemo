package com.randori.elevators;

import org.junit.Assert;
import org.junit.Test;

import static com.randori.elevators.Direction.*;
import static org.mockito.Mockito.mock;

/**
 * Created by kinas on 2016-04-20.
 */
public class BuildingElevatorTest {
    Signal signal = mock(Signal.class);
    Controller controller = mock(Controller.class);

    @Test
    public void shouldAddNewSignalForHandling() {
        // given
        Elevator elevator = new BuildingElevator();

        // when
        elevator.add(signal);

        // then
        Assert.assertEquals(1, elevator.countRequests());
    }

    @Test
    public void shouldAddNewSignalInTheMiddleWhenItCanBeProcessFasterThanTheLastSignal() {
        // given
        Elevator elevator = new BuildingElevator();
        Signal s1 = new ExteriorSignal(controller, 0, UP);
        Signal s2 = new ExteriorSignal(controller, 3, UP);
        Signal s3 = new ExteriorSignal(controller, 2, UP);
        elevator.add(s1);
        elevator.add(s2);
        elevator.add(s3);

        // when
        Signal s4 = new ExteriorSignal(controller, 1, UP);
        int expectedPosition = elevator.getRequestPosition(s4);

        // verify
        Assert.assertEquals(expectedPosition, 2);
    }

    @Test
    public void shouldAddNewSignalInTheMiddleWhenElevatorHasSignalsOfDifferentDirection() {
        // given
        Elevator elevator = new BuildingElevator();
        Signal s1 = new ExteriorSignal(controller, 0, DOWN);
        Signal s2 = new ExteriorSignal(controller, 3, DOWN);
        Signal s3 = new ExteriorSignal(controller, 2, UP);
        elevator.add(s1);
        elevator.add(s2);
        elevator.add(s3);

        // when
        Signal s4 = new ExteriorSignal(controller, 1, UP);
        int expectedPosition = elevator.getRequestPosition(s4);

        // verify
        Assert.assertEquals(expectedPosition, 4);
    }
}
