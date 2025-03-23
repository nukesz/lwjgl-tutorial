package com.github.nukesz.examples;

import com.github.nukesz.ApplicationListener;
import com.github.nukesz.GameEngine;

public class Example implements ApplicationListener {

    public static void run(Example example) {
        GameEngine engine = new GameEngine(example);
        engine.run();
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
    }

    @Override
    public void cleanUp() {
    }
}
