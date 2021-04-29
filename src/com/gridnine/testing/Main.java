package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import static com.gridnine.testing.VoyageFilter.*;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("All flights:");
        flights.forEach(System.out::println);

        System.out.println("\nConditions:");
        Predicate<Segment> segmentPredicate = s -> s.getDepartureDate().isBefore(LocalDateTime.now());
        System.out.println("1. Flights departing in the past: " + filterBySegmentCondition(flights, segmentPredicate));

        segmentPredicate = s -> s.getArrivalDate().isBefore(s.getDepartureDate());
        System.out.println("2. Flights that arrives before then departs: " + filterBySegmentCondition(flights, segmentPredicate));

        Predicate<Voyage> voyagePredicate = f -> f.getTotalRestHours() > 2;
        System.out.println("3. Flights with more than two hours ground time: " + filterByVoyageCondition(flights, voyagePredicate));
    }
}
