package org.example.car.aplication.homework.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceHW  {
    public void main(){
        ExecutorService executor = Executors.newFixedThreadPool(3);


        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Задача " + taskNumber + " выполняется в потоке: " + threadName);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }


        executor.shutdown();


        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            System.out.println("Все задачи выполнены.");
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
