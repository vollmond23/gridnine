package com.gridnine.testing;

import java.util.List;

/**
 * interface for different types of voyages (flights, sails, trains etc.). For future use.
 */
public interface Voyage {

    // getting list of all segments if voyage
    List<Segment> getSegments();

    // getting list of hours in all segments of voyage. Gonna be useful for special conditions when we need timing of second segment of voyage for example.
    List<Long> getVoyageHours();

    // fast method for getting total hours of voyage (without rest time)
    long getTotalVoyageHours();

    // getting list of rest hours between segments
    List<Long> getRestHours();

    // fast method for getting total rest hours
    long getTotalRestHours();
}
