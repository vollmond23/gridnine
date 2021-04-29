package com.gridnine.testing;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

abstract class AbstractVoyage implements Voyage {

    final List<Segment> segments;

    public AbstractVoyage(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public List<Long> getVoyageHours() {
        return segments.stream()
                .map(segment -> ChronoUnit.HOURS.between(segment.getDepartureDate(), segment.getArrivalDate()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(segments.size())));
    }

    @Override
    public long getTotalVoyageHours() {
        return getTotalHours(getVoyageHours());
    }

    @Override
    public List<Long> getRestHours() {
        int size = segments.size();
        if (size == 1) {
            return Collections.emptyList();
        }
        List<Long> groundTimes = new ArrayList<>(size - 1);
        for (int i = 0; i < size - 1; i++) {
            groundTimes.add(ChronoUnit.HOURS.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()));
        }
        return groundTimes;
    }

    @Override
    public long getTotalRestHours() {
        return getTotalHours(getRestHours());
    }

    long getTotalHours(List<Long> hours) {
        return hours.stream().mapToLong(value -> value).sum();
    }
}
