package com.example.multithreading1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private final AtomicInteger counter;

    public AtomicIntegerCounter() {
        counter = new AtomicInteger();
    }

    @Override
    public void incrementVisitCount() throws InterruptedException {
        Thread.sleep(100);
        counter.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return counter.get();
    }
}
