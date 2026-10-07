package org.example;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// JAVA 21 (LTS, septembre 2023)
// @see https://openjdk.org/jeps/444
public class VirtualThreads {

    private final int taskCount;

    public VirtualThreads(int taskCount) {
        this.taskCount = taskCount;
    }

    public void run() {
        long start = System.nanoTime();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, taskCount).forEach(i ->
                    executor.submit(() -> {
                        Thread.sleep(Duration.ofSeconds(1)); // tâche bloquante
                        return i;
                    }));
        } // close() attend la fin de toutes les tâches

        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("%d tâches bloquantes de 1 s terminées en %d ms%n", taskCount, ms);
    }
}