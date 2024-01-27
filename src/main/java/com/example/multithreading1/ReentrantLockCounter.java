package com.example.multithreading1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    private final Lock lockCounter;
    private int counter;

    public ReentrantLockCounter() {
        lockCounter = new ReentrantLock();
    }

    @Override
    public void incrementVisitCount() {
        lockCounter.lock();
        try {
            Thread.sleep(100);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lockCounter.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
