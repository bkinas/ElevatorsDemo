package com.randori.elevators;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by kinas on 2016-04-20.
 */

class BuildingElevator implements Elevator {
    private SignalComparator comparator = new SignalComparator();

    @Override
    public void run() {
        for (Iterator<Signal> iterator = requests.iterator(); iterator.hasNext();) {
            process(iterator.next());
        }
    }

    private void process(Signal next) {
        requests.remove(next);
    }

    private final TreeSet<Signal> requests = new TreeSet<>(comparator);

    @Override
    public void add(Signal signal) {
        synchronized (requests) {
            requests.add(signal);
            comparator.setDirection(requests.first().getDirection());
        }
    }

    @Override
    public int countRequests() {
        return requests.size();
    }

    @Override
    public int getRequestPosition(Signal signal) {
        int position = 0;
        synchronized (requests) {
            requests.add(signal);
            for (Signal s : requests) {
                if (signal.equals(s)) {
                    break;
                }

                position++;
            }
            requests.remove(signal);
        }
        return position + 1;
    }

    private class SignalComparator implements Comparator<Signal> {
        private Direction reference = null;

        public void setDirection(Direction direction) {
            this.reference = direction;
        }

        @Override
        public int compare(Signal s1, Signal s2) {
            if (reference != null && !s1.getDirection().equals(s2.getDirection())) {
                return s1.getDirection().equals(reference) ? 1 : -1;
            }
            return s1.compareTo(s2);
        }
    }
}
