package com.example.multithreading1;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private Integer counter;

    public SynchronizedBlockCounter() {
        counter = 0;
    }

    @Override
    public void incrementVisitCount() {
        synchronized (this) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
