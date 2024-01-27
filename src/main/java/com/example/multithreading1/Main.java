package com.example.multithreading1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SiteVisitCounter siteVisitCounter = new UnsynchronizedCounter();
        MultithreadingSiteVisitor multithreadingSiteVisitor = new MultithreadingSiteVisitor(siteVisitCounter);

        multithreadingSiteVisitor.visitMultithread(100000);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.println(multithreadingSiteVisitor.getTotalTimeOfHandling() + " секунд");


    }
}
