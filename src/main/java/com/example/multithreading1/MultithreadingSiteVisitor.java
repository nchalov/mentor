package com.example.multithreading1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultithreadingSiteVisitor {

    private final SiteVisitCounter siteVisitCounter;
    private final List<Thread> threadList;
    private long startTime;
    private long stopTime;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
        threadList = new ArrayList<>();
    }

    public void visitMultithread(int numOfThreads) {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numOfThreads; i++) {
           Thread thread = createThread();
           threadList.add(thread);
           thread.start();
        }
    }

    public void waitUntilAllVisited() throws InterruptedException {
        for (Thread thread : threadList) {
            thread.join();
        }
        stopTime = System.currentTimeMillis();
    }

    public long getTotalTimeOfHandling() {
        return (stopTime - startTime) / 1000;
    }

    private Thread createThread() {
        return new Thread(() -> {
            try {
                siteVisitCounter.incrementVisitCount();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
