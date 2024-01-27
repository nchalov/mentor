package com.example.multithreading1;

public class UnsynchronizedCounter implements SiteVisitCounter {

    private int counter;

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
