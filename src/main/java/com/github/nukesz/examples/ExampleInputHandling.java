package com.github.nukesz.examples;

import com.github.nukesz.InputHandler;
import static org.lwjgl.glfw.GLFW.*;

public class ExampleInputHandling extends Example {

    private InputHandler inputHandler;

    @Override
    public void init(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void update(float deltaTime) {
        if (inputHandler.isKeyDown(GLFW_KEY_SPACE)) {
            System.out.println("space key is ");
        }
        if (inputHandler.isKeyPressed(GLFW_KEY_RIGHT)) {
            System.out.println("right arrow key");
        }
        if (inputHandler.isKeyUp(GLFW_KEY_A)) {
            System.out.println("’A’ key");
        }
    }

    public static void main(String[] args) {
        run(new ExampleInputHandling());
    }
}
