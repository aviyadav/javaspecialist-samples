package com.sample.java.core.issue297;

import com.sample.java.core.bench.DefaultStatsListener;
import com.sample.java.core.bench.ForkJoinPoolBench;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class FactorialByStreamDemo {
public static void main(String... args) {
    ForkJoinPoolBench.test(
        () -> IntStream.rangeClosed(1, 200_000)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply),
        new DefaultStatsListener("sequentialFactorial"));

    ForkJoinPoolBench.test(
        () -> IntStream.rangeClosed(1, 200_000)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply),
        new DefaultStatsListener("parallelFactorial"));
  }
}