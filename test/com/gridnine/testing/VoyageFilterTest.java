package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class VoyageFilterTest {

    private static final List<Flight> FLIGHTS = FlightBuilder.createFlights();

    //
    private static final Predicate<Segment> SEGMENT_PREDICATE_1 = s -> s.getDepartureDate().isBefore(LocalDateTime.now());
    private static final Predicate<Segment> SEGMENT_PREDICATE_2 = s -> s.getArrivalDate().isBefore(s.getDepartureDate());

    private static final Predicate<Voyage> VOYAGE_PREDICATE_1 = f -> f.getTotalRestHours() > 2;
    private static final Predicate<Voyage> VOYAGE_PREDICATE_2 = f -> f.getVoyageHours().stream().anyMatch(hours -> hours > 2);

    @Test
    public void filterBySegmentCondition1() {
        List<Flight> expectedVoyages = getExpectedVoyages(FLIGHTS, 2);
        List<Flight> actualVoyages = VoyageFilter.filterBySegmentCondition(FLIGHTS, SEGMENT_PREDICATE_1);
        assertEquals(expectedVoyages, actualVoyages);
    }

    @Test
    public void filterBySegmentCondition2() {
        List<Flight> expectedVoyages = getExpectedVoyages(FLIGHTS, 3);
        List<Flight> actualVoyages = VoyageFilter.filterBySegmentCondition(FLIGHTS, SEGMENT_PREDICATE_2);
        assertEquals(expectedVoyages, actualVoyages);
    }

    @Test
    public void filterByVoyageCondition1() {
        List<Flight> expectedVoyages = getExpectedVoyages(FLIGHTS, 4, 5);
        List<Flight> actualVoyages = VoyageFilter.filterByVoyageCondition(FLIGHTS, VOYAGE_PREDICATE_1);
        assertEquals(expectedVoyages, actualVoyages);
    }

    @Test
    public void filterByVoyageCondition2() {
        List<Flight> expectedVoyages = getExpectedVoyages(FLIGHTS, 2);
        List<Flight> actualVoyages = VoyageFilter.filterByVoyageCondition(FLIGHTS, VOYAGE_PREDICATE_2);
        assertEquals(expectedVoyages, actualVoyages);
    }

    private <T extends Voyage> List<T> getExpectedVoyages(List<T> voyages, int... indexes) {
        if (Arrays.stream(indexes).anyMatch(i -> i < 0)) {
            return Collections.emptyList();
        }
        List<T> expectedVoyages = new ArrayList<>();
        for (int i : indexes) {
            expectedVoyages.add(voyages.get(i));
        }
        return expectedVoyages;
    }
}