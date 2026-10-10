package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphores {

    private final int permits;
    private final int taskCount;

    public Semaphores(int permits, int taskCount) {
        this.permits = permits;
        this.taskCount = taskCount;
    }

    public void run(){
        Semaphore semaphore = new Semaphore(permits);
        AtomicInteger active = new AtomicInteger();
        AtomicInteger maxActive = new AtomicInteger();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                final int id = 1;
                executorService.submit(() -> {
                    try {
                        semaphore.acquire();
                        try {
                        int now = active.incrementAndGet();
                        maxActive.accumulateAndGet(now, Math::max);
                        System.out.println("tache " + id + "démarre (active : " + now + ")");
                        Thread.sleep(300);
                        active.decrementAndGet();
                        } finally {
                            semaphore.release();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                });

            }

        }
    }
}
