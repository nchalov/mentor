package com.example.multithreading1;

public class VolatileCounter implements SiteVisitCounter {

    private volatile int counter;

    @Override
    public void incrementVisitCount() throws InterruptedException {
        Thread.sleep(100);
        counter++;
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
