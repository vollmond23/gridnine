package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Bean that represents a flight segment.
 */
class Segment {
    private final LocalDateTime departureDate;

    private final LocalDateTime arrivalDate;

    Segment(final LocalDateTime dep, final LocalDateTime arr) {
        departureDate = Objects.requireNonNull(dep);
        arrivalDate = Objects.requireNonNull(arr);
    }

    LocalDateTime getDepartureDate() {
        return departureDate;
    }

    LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Segment segment = (Segment) o;

        if (!departureDate.equals(segment.departureDate)) return false;
        return arrivalDate.equals(segment.arrivalDate);
    }

    @Override
    public int hashCode() {
        int result = departureDate.hashCode();
        result = 31 * result + arrivalDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}