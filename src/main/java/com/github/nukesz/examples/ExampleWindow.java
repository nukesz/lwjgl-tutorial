package com.github.nukesz.examples;

public class ExampleWindow extends Example {

    @Override
    public void init() {
        System.out.println("ExampleWindow.init");
    }

    @Override
    public void update() {
        System.out.println("ExampleWindow.update");
    }

    @Override
    public void cleanUp() {
        System.out.println("ExampleWindow.cleanUp");
    }

    public static void main(String[] args) {
        run(new ExampleWindow());
    }
}
