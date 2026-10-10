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
    //
    public void run(){
        // ...
    }
}
