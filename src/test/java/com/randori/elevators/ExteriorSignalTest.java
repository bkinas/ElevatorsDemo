/**
 * Created by kinas on 20.03.16.
 */

package com.randori.elevators;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExteriorSignalTest {
    @Test
    public void shouldAddNewCallToElevatorSystem() {
        // given
        Controller controller = mock(Controller.class);
        ExteriorSignal exteriorSignal = new ExteriorSignal(controller, 2, Direction.UP);

        // when
        exteriorSignal.send();

        // then
        verify(controller).process(exteriorSignal);
    }
}
