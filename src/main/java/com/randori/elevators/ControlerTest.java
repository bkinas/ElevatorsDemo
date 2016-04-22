package com.randori.elevators;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by wrkj83 on 2016-04-22.
 */

public class ControlerTest {
    Elevator e1 = mock(Elevator.class);
    Elevator e2 = mock(Elevator.class);

    private final Signal signal = mock(Signal.class);
    private final Controller controller = new ElevatorController(e1, e2);

    @Test
    /**
     * Scenario Outline:
     * GIVEN: Elevators are in idle and at the same floor
     * WHEN: controller process new signal
     * THEN: elevator1 got this signal
     */
    public void shouldSendFirstElevatorWhenBothAreSteadyAndHaveTheSameDistance() {
        // Given
        when(e1.countUnprocessSignals()).thenReturn(0);
        when(e2.countUnprocessSignals()).thenReturn(0);
        ElevatorState elevatorState = new ElevatorState(0, null);

        // when -> then
        check(elevatorState, elevatorState);
    }

    private void check(ElevatorState elevatorState1, ElevatorState elevatorState2) {
        when(e1.getState()).thenReturn(elevatorState1);
        when(e2.getState()).thenReturn(elevatorState2);
        // When
        controller.process(signal);

        // Then
        verify(e1).add(signal);
    }

    @Test
    /**
     * Scenario Outline:
     * GIVEN: Elevators are in idle and elevator2 is closer to the requestor
     * WHEN: controller process new signal
     * THEN: elevator2 got this signal
     */
    public void shouldSendClosestWhenBothSteady() {
        // Given
        // ElevatorState es2 = new ElevatorState(1, null);
        // when(e1.countUnprocessSignals()).thenReturn(0);
        // when(e2.countUnprocessSignals()).thenReturn(0);
        // when(e1.getState()).thenReturn(elevatorState);
        // when(e2.getState()).thenReturn(es2);
        //
        // when(signal.getFloor()).thenReturn(1);
        //
        // // When
        // controller.process(signal);
        //
        // // Then
        // check(e2).add(signal);
    }
}
