package com.example.multithreading1;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private Integer counter;

    public SynchronizedBlockCounter() {
        counter = 0;
    }

    @Override
    public void incrementVisitCount() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(100);
            counter++;
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
