package com.github.nukesz.examples;

import com.github.nukesz.ApplicationListener;
import com.github.nukesz.GameEngine;
import com.github.nukesz.InputHandler;

public class Example implements ApplicationListener {

    public static void run(Example example) {
        GameEngine engine = new GameEngine(example);
        engine.run();
    }

    @Override
    public void init(InputHandler inputHandler) {
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void cleanUp() {
    }
}
