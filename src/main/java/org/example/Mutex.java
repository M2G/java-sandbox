package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Mutex {

    private final int threadCount;
    private final int incrementsPerThread;

    private final ReentrantLock lock = new ReentrantLock();
    private int safeCounter = 0;
    private int unsafeCounter = 0;

    public Mutex(
            int threadCount,
            int incrementsPerThread
    ){
        this.threadCount = threadCount;
        this.incrementsPerThread = incrementsPerThread;
    }

    private void incrementSafe(){
        lock.lock();
        try {
            safeCounter++;
        } finally {
            lock.unlock();
        }
    }

    private void incrementUnsafe() {
        unsafeCounter++;
    }

    public void run() throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        for (int t = 0; t < threadCount; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < incrementsPerThread; i++) {
                    incrementSafe();
                    incrementUnsafe();
                }
            });
            threads[t].start();
        }
        for (Thread t: threads) t.join();

        int expected = threadCount * incrementsPerThread;
        System.out.println("attendu          = " + expected);
        System.out.println("avec mutex       = " + safeCounter);
        System.out.println("sans mutex       = " + unsafeCounter + " (généralement inférieur)");
    }
}
