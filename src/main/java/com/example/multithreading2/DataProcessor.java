package com.example.multithreading2;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class DataProcessor {

    private final ExecutorService executorService;
    private final AtomicInteger taskCounter;
    private final Map<String, Future<Integer>> currentTasks;
    private final Logger log;

    public DataProcessor(int threadAmount) {
        this.executorService = Executors.newFixedThreadPool(threadAmount);
        taskCounter = new AtomicInteger(0);
        currentTasks = new HashMap<>();
        log = Logger.getLogger(DataProcessor.class.getName());
    }

    public String calculateSumTask(List<Integer> integerList) {
        String taskName = String.format("task %d", taskCounter.incrementAndGet());
        log.info(String.format("Задача %s принята в работу", taskName));
        Future<Integer> task = executorService.submit(new CalculateSumTask(integerList, taskName));
        synchronized (currentTasks) {
            currentTasks.put(taskName, task);
        }
        return taskName;
    }

    public long getActiveTasks() {
        synchronized (currentTasks) {
            return currentTasks.values()
                    .stream()
                    .filter(task -> !task.isDone())
                    .count();
        }
    }


    public Optional<Integer> getTaskResult(String taskName) throws ExecutionException, InterruptedException {
        synchronized (currentTasks) {
            return Optional.ofNullable(currentTasks.get(taskName).get());
        }
    }

    public void shutdown() throws InterruptedException {
        while (getActiveTasks() > 0) {
            log.info("Ожидание завершения всех задач!");
            Thread.sleep(100);
        }
        executorService.shutdownNow();
        log.info("Все задачи успешно завершены!");
    }
}
