package org.example;

import java.util.List;

// JAVA 21
//@see https://openjdk.org/jeps/431
public class PatternMatching {
    sealed interface Shape permits Circle, Rect {}
    record Circle(double r) implements Shape {}
    record Rect(double w, double h) implements Shape {}

    private final List<Shape> shapes;

    public PatternMatching(List<Shape> shapes) {
        this.shapes = shapes;
    }

    // Exhaustif grâce à "sealed" : pas de default, le compilateur vérifie tous les cas
    static double area(Shape s) {
        return switch (s) {
            case Circle(double r)         -> Math.PI * r * r;
            case Rect(double w, double h) -> w * h;
        };
    }

    // Guard "when" pour affiner un cas
    static String describe(Shape s) {
        return switch (s) {
            case Circle(double r) when r > 10 -> "grand cercle";
            case Circle c                     -> "cercle";
            case Rect(double w, double h) when w == h -> "carré";
            case Rect r                       -> "rectangle";
        };
    }

    public void run() {
        for (Shape s : shapes) {
            System.out.printf("%-35s -> %-12s aire = %.2f%n", s, describe(s), area(s));
        }
    }

    public static PatternMatching withDefaults() {
        return new PatternMatching(List.of(
                new Circle(2), new Circle(12), new Rect(3, 4), new Rect(5, 5)));
    }
}