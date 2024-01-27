package com.example.multithreading2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class CalculateSumTask implements Callable<Integer> {

    private final List<Integer> integerList;
    private final String taskName;
    private final Logger log;

    public CalculateSumTask(List<Integer> integerList, String taskName) {
        this.integerList = integerList;
        this.taskName = taskName;
        log = Logger.getLogger(CalculateSumTask.class.getName());
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(200);
        log.info(String.format("Поток %s выполняет задачу %s",
                Thread.currentThread().getName(), taskName));
        return integerList
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
