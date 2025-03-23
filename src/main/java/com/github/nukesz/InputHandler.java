package com.github.nukesz;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {

    public List<Integer> keyPressQueue = new ArrayList<>();
    public List<Integer> keyReleaseQueue = new ArrayList<>();
    public List<Integer> keyDownList = new ArrayList<>();
    public List<Integer> keyPressedList = new ArrayList<>();
    public List<Integer> keyUpList = new ArrayList<>();

    public InputHandler(Window window) {
        // specify code to run when key is pressed or released
        glfwSetKeyCallback(window.getHandle(), (window_, key, scancode, action, mods) -> {
                    if (action == GLFW_PRESS)
                        keyPressQueue.add(key);
                    if (action == GLFW_RELEASE)
                        keyReleaseQueue.add(key);
                }
        );
    }

    public void update() {
        // reset discrete key states
        keyDownList.clear();
        keyUpList.clear();
        // process queued press/release events;
        // add to or remove from corresponding lists
        for (Integer key : keyPressQueue) {
            keyDownList.add(key);
            keyPressedList.add(key);
        }
        for (Integer key : keyReleaseQueue) {
            keyUpList.add(key);
            keyPressedList.remove(key);
        }
        // finished processing queues; clear contents
        keyPressQueue.clear();
        keyReleaseQueue.clear();

    }

    public boolean isKeyUp(Integer key) {
        return keyUpList.contains(key);
    }

    public boolean isKeyDown(Integer key) {
        return keyDownList.contains(key);
    }

    public boolean isKeyPressed(Integer key) {
        return keyPressedList.contains(key);
    }
}
