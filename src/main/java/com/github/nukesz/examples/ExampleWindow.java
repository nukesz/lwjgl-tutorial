package com.github.nukesz.examples;

import com.github.nukesz.InputHandler;

public class ExampleWindow extends Example {

    @Override
    public void init(InputHandler inputHandler) {
        System.out.println("ExampleWindow.init");
    }

    @Override
    public void update(float deltaTime) {
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
