package com.example.multithreading1;

public interface SiteVisitCounter {

    void incrementVisitCount() throws InterruptedException;
    int getVisitCount();
}
