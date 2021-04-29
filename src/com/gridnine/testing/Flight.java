package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 */
class Flight extends AbstractVoyage {

    Flight(final List<Segment> segs) {
        super(segs);
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
