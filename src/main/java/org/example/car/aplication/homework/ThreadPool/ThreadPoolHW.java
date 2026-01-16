package org.example.car.aplication.homework.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHW {

    static class NumberPrinter implements Runnable {
        private final String threadName;

        public NumberPrinter(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Поток: " + threadName + " — число: " + i);
                    Thread.sleep(500); // задержка 0.5 сек
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void runWithSingleThreadExecutor() throws InterruptedException {
        System.out.println("Запуск с SingleThreadExecutor");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        long startTime = System.currentTimeMillis();

        // Запуск 3 задач последовательно
        for (int i = 0; i < 3; i++) {
            executor.submit(new NumberPrinter("SingleThreadExecutor"));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (endTime - startTime) + " мс\n");
    }

    public static void runWithFixedThreadPool() throws InterruptedException {
        System.out.println("Запуск с FixedThreadPool (3 потока)");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();

        // Запуск 3 задач
        for (int i = 0; i < 3; i++) {
            executor.submit(new NumberPrinter("FixedThreadPool-" + i));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (endTime - startTime) + " мс\n");
    }

    public static void main(String[] args) throws InterruptedException {
        runWithSingleThreadExecutor();
        runWithFixedThreadPool();
    }

}
