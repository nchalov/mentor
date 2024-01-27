package com.example.multithreading1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<SiteVisitCounter> implList = Arrays.asList(
                new AtomicIntegerCounter(),
                new ReentrantLockCounter(),
                new SynchronizedBlockCounter(),
                new UnsynchronizedCounter(),
                new VolatileCounter()
        );

        for (SiteVisitCounter counter : implList) {
            MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter);
            visitor.visitMultithread(10);
            visitor.waitUntilAllVisited();
            System.out.printf("\nВремя работы: %d сек\nСчетчик: %d\n", visitor.getTotalTimeOfHandling(),
                    counter.getVisitCount());
            System.out.println("-".repeat(150));
        }
    }
}
