package org.example;

import java.util.ArrayList;
import java.util.List;

public class Semaphore {

    private final int permits;
    private final int taskCount;

    public Semaphore(int permits, int taskCount) {
        this.permits = permits;
        this.taskCount = taskCount;
    }

    public void run(){
    }
}
