package com.example.multithreading1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private final AtomicInteger counter;

    public AtomicIntegerCounter() {
        counter = new AtomicInteger();
    }

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return 0;
    }
}
