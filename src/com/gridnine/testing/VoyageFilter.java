package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * utility class for filter voyages by specific predicate
 */
public class VoyageFilter {

    // method for filter by exact data of segment (start/end, duration of any segment, etc). Use it if you want to specify your filter and avoid long condition.
    public static <T extends Voyage> List<T> filterBySegmentCondition(List<T> voyages, Predicate<Segment> segmentPredicate) {
        return voyages.stream()
                .filter(voyage -> voyage.getSegments().stream()
                        .anyMatch(segmentPredicate))
                .collect(Collectors.toList());
    }

    // method for filter by exact data of voyage (total count of segments, hours of voyage/rest, etc).
    public static <T extends Voyage> List<T> filterByVoyageCondition(List<T> voyages, Predicate<? super Voyage> voyagePredicate) {
        return voyages.stream()
                .filter(voyagePredicate)
                .collect(Collectors.toList());
    }
}
