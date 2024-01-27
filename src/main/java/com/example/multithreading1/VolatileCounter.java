package com.example.multithreading1;

public class VolatileCounter implements SiteVisitCounter {

    private volatile int counter;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter++;
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
