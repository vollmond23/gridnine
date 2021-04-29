package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVoyageTest {

    List<? extends Voyage> voyages;

    static Segment segment1;

    static {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        segment1 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
    }

    AbstractVoyageTest(List<? extends Voyage> voyages) {
        this.voyages = voyages;
    }

    @Test
    public void getVoyageHours() {
        List<Long> expected = Arrays.asList(2L, 4L, 144L, -6L, 3L, 4L);
        Assert.assertEquals(expected, voyages.stream().map(Voyage::getTotalVoyageHours).collect(Collectors.toList()));
    }

    @Test
    public void getTotalVoyageHours() {
        Assert.assertEquals(151, voyages.stream().mapToLong(Voyage::getTotalVoyageHours).sum());
    }

    @Test
    public void getRestHours() {
        List<Long> expected = Arrays.asList(0L, 1L, 0L, 0L, 3L, 3L);
        Assert.assertEquals(expected, voyages.stream()
                .map(Voyage::getTotalRestHours)
                .collect(Collectors.toList()));
    }

    @Test
    public void getTotalRestHours() {
        Assert.assertEquals(7, voyages.stream().mapToLong(Voyage::getTotalRestHours).sum());
    }

    @Test
    public void getTotalVoyages() {
        Assert.assertEquals(6, voyages.size());
    }
}