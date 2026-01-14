package org.example.car.aplication.async;


import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTaskManager implements Closeable {

    private final ExecutorService executorService;

    public AsyncTaskManager(int threadCount) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void doAsync(Runnable asyncTask) {
        executorService.submit(asyncTask);
    }

    @Override
    public void close() {
        executorService.shutdown();
    }
}