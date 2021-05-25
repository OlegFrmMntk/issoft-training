package by.issoft.sample.persistence;

import org.junit.Test;

import static by.issoft.sample.sample.FloorTestSamples.anyFloor;
import static by.issoft.sample.sample.PersonTestSamples.anyPerson;
import static org.junit.Assert.*;

public class LiftStatisticsTest {

    @Test
    public void addStatistics() {
        LiftStatistics liftStatistics = LiftStatistics.of();

        StatisticItem statisticItem = StatisticItem.of("add", anyPerson(), anyFloor().getNumber());

        assertEquals(liftStatistics.getPassengerNumber(), 0);
        assertTrue(liftStatistics.getStatistic().isEmpty());

        liftStatistics.addStatistics(statisticItem);

        assertEquals(liftStatistics.getPassengerNumber(), 1);
        assertEquals(liftStatistics.getStatistic().size(), 1);
    }
}