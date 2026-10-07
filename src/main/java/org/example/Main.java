package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        System.out.println("== Threads virtuels ==");
        new VirtualThreads(10_000).run();

        System.out.println("\n== Pattern matching ==");
        PatternMatching.withDefaults().run();
    }
}
