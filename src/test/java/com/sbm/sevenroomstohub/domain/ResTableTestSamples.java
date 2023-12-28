package com.sbm.sevenroomstohub.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ResTableTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ResTable getResTableSample1() {
        return new ResTable().id(1L).tableNumber(1);
    }

    public static ResTable getResTableSample2() {
        return new ResTable().id(2L).tableNumber(2);
    }

    public static ResTable getResTableRandomSampleGenerator() {
        return new ResTable().id(longCount.incrementAndGet()).tableNumber(intCount.incrementAndGet());
    }
}
