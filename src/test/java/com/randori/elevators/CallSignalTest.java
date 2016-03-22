/**
 * Created by kinas on 20.03.16.
 */

package com.randori.elevators;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CallSignalTest {
    @Test
    public void shouldAddNewCallToElevatorSystem() {
        // given
        Controller controller = mock(Controller.class);
        CallSignal callSignal = new CallSignal(controller, 2, Direction.UP);

        // when
        callSignal.send();

        // then
        verify(controller).add(callSignal);
    }
}
