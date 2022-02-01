package com.sample.java.core.issue297;

import com.sample.java.core.bench.DefaultStatsListener;
import com.sample.java.core.bench.ForkJoinPoolBench;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArraySortBenchTest {

    public static void main(String... args) {
        int[] array = ThreadLocalRandom.current().ints(100_000_000).toArray();

        for (int i = 0; i < 3; i++) {
            int[] sequentialToSort = array.clone();

            ForkJoinPoolBench.test(() -> Arrays.sort(sequentialToSort), new DefaultStatsListener("sequentialSort" + 1));
        }

        for (int i = 0; i < 3; i++) {
            int[] parallelToSort = array.clone();
            ForkJoinPoolBench.test( () -> Arrays.parallelSort(parallelToSort), new DefaultStatsListener("parallelSort" + i));
        }
    }
}
