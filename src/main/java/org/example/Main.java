package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws InterruptedException {
        System.out.println("== Threads virtuels ==");
        new VirtualThreads(10_000).run();

        System.out.println("\n== Semaphore ==");
        new Semaphores(3, 8).run();

        System.out.println("\n== Mutex (ReentrantLock) ==");
        new Mutex(8, 100_000).run();
    }
}
