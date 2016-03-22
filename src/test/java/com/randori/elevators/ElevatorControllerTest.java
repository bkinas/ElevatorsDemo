package com.randori.elevators;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kinas on 20.03.16.
 */
public class ElevatorControllerTest {
    Elevator elevator1 = mock(Elevator.class);
    Elevator elevator2 = mock(Elevator.class);
    Signal signal = mock(CallSignal.class);

    @Test
    public void testControllerSendRequestToElevator1() {
        // given
        Controller controller = new ElevatorController(elevator1, elevator2);
        Integer requestedFloor = new Integer(3);
        when(signal.getFloor()).thenReturn(requestedFloor);
        when(elevator1.getFloor()).thenReturn(2);
        when(elevator2.getFloor()).thenReturn(1);
        // when
        controller.add(signal);

        // then
        verify(elevator1).sendTo(requestedFloor);
    }

    @Test
    public void testControllerSendRequestToElevator2WhenIsCloser() {
        // given
        Controller controller = new ElevatorController(elevator1, elevator2);
        Integer requestedFloor = new Integer(0);
        when(signal.getFloor()).thenReturn(requestedFloor);
        when(elevator1.getFloor()).thenReturn(requestedFloor+2);
        when(elevator2.getFloor()).thenReturn(requestedFloor+1);

        // when
        controller.add(signal);

        // then
        verify(elevator2).sendTo(requestedFloor);
    }
}
