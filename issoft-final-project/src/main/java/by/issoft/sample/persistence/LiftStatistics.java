package by.issoft.sample.persistence;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LiftStatistics {

    private final List<StatisticItem> statistic = new ArrayList<>();

    private int passengerNumber = 0;

    public LiftStatistics() {
        this.passengerNumber = 0;
    }

    public static LiftStatistics of() {
        return new LiftStatistics();
    }

    public void addStatistics(StatisticItem statisticItem) {
        statistic.add(statisticItem);
        if (statisticItem.getAction().equals("add")) {
            passengerNumber++;
        }
    }

    @Override
    public String toString() {
        String stat = statistic.
                stream()
                .map(statisticItem -> String.format("\t%s\n", statisticItem))
                .collect(Collectors.joining("", "LiftStatistics:\n", ""));

        return stat + String.format("Number of passengers: %d\n", passengerNumber);
    }

    @SneakyThrows
    public void saveStatisticFile(String path) {
        FileOutputStream fileOutputStream = new FileOutputStream(path);

        fileOutputStream.write(toString().getBytes());

        fileOutputStream.close();
    }
}
