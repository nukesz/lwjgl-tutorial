package com.github.nukesz.examples;

import com.github.nukesz.GameEngine;

public class Example {

    public static void run(Example example) {
        GameEngine gameEngine = new GameEngine() {

            @Override
            public void initialize() {
                example.initialize();
            }

            @Override
            public void update() {
                example.update();
            }

            @Override
            public void dispose() {
                example.dispose();
            }
        };
        gameEngine.init();
        gameEngine.loop();
        gameEngine.close();
    }

    public void initialize() {
    }

    public void update() {
    }

    public void dispose() {
    }
}
