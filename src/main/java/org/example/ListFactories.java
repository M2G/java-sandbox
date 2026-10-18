package org.example;

import java.util.List;

public class ListFactories {
    public void run() {
        // List.of immuable (sans null)
        List<String> immuable = List.of("java", "python", "c++", "c#", "javascript");
        System.out.println("List.of (immuable) : " + immuable);

        tenter("List.of.add", () -> immuable.add("zig"));
        tenter("List.of.set", () -> immuable.set(0, "kotlin"));
        tenter("List.of(null)", () -> List.of("a", null));
        // ...
        // Array.asList (taille fix, vue table)
        // ...
        // Stream (les 2 use cases)
    }

    private static void tenter(String libelle, Runnable action) {
        try {
            action.run();
            System.out.println(libelle + " -> OK");
        } catch (RuntimeException e) {
            System.out.println(libelle + " -> " + e.getClass().getSimpleName());
        }
    }
}
