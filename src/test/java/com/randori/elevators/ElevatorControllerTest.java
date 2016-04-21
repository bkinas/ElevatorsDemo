package com.randori.elevators;

import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kinas on 20.03.16.
 */
public class ElevatorControllerTest {
    Elevator elevator1 = mock(Elevator.class);
    Elevator elevator2 = mock(Elevator.class);
    Signal signal = mock(ExteriorSignal.class);
    Controller controller = new ElevatorController(elevator1, elevator2);
    final int idleState = 0;
    final int busyState = 1;

    @Test
    public void shouldAddRequestToElevator1WhenBothElevatorAreInIdle() {
        // given
        when(elevator1.countRequests()).thenReturn(idleState);
        when(elevator2.countRequests()).thenReturn(idleState);

        // when
        controller.process(signal);

        // then
        verify(elevator1).add(signal);
        verify(elevator2,never()).add(signal);
    }

    @Test
    public void shouldAddRequestToElevator2WhenElevator1IsBusy() {
        // given
        when(elevator1.countRequests()).thenReturn(busyState);
        when(elevator2.countRequests()).thenReturn(idleState);

        // when
        controller.process(signal);

        // then
        verify(elevator1,never()).add(signal);
        verify(elevator2).add(signal);
    }

    @Test
    public void shouldControllerSendRequestToElevator2WhichIsBusyButIsCloser() {
        // given
        when(elevator1.countRequests()).thenReturn(busyState);
        when(elevator2.countRequests()).thenReturn(busyState);

        when(elevator1.getRequestPosition(signal)).thenReturn(5);
        when(elevator2.getRequestPosition(signal)).thenReturn(4);

        // when
        controller.process(signal);

        // then
        verify(elevator1, never()).add(signal);
        verify(elevator2).add(signal);
    }
}
