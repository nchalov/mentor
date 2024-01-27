package com.example.multithreading2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Main {

    private final static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        DataProcessor dataProcessor =
                new DataProcessor(10);

        List<String> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String taskName = dataProcessor.calculateSumTask(generateListInteger(500));
            taskList.add(taskName);
        }

        do {
            log.info(String.format("Количество активных задач равняется %d",
                    dataProcessor.getActiveTasks()));
            threadSleep(500L);
        } while (dataProcessor.getActiveTasks() != 0);

        taskList.forEach((task) -> print(dataProcessor, task));

        shutdownExecutor(dataProcessor);
    }

    private static List<Integer> generateListInteger(int size) {
        List<Integer> listRandom = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size - 1; i++) {
            listRandom.add(random.nextInt(1000));
        }
        return listRandom;
    }

    private static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(DataProcessor dataProcessor, String task) {
        try {
            System.out.println("Результат: " + dataProcessor.getTaskResult(task).get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void shutdownExecutor(DataProcessor dataProcessor) {
        try {
            dataProcessor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


