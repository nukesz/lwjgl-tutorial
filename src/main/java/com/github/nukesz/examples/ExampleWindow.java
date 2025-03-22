package com.github.nukesz.examples;

public class ExampleWindow extends Example {

    @Override
    public void initialize() {
        System.out.println("ExampleWindow.init");
    }

    @Override
    public void update() {
        System.out.println("ExampleWindow.update");
    }

    @Override
    public void dispose() {
        System.out.println("ExampleWindow.dispose");
    }

    public static void main(String[] args) {
        run(new ExampleWindow());
    }
}
