package com.github.nukesz;

public class GameEngine {

    private final Window window;
    private final ApplicationListener applicationListener;

    public GameEngine(ApplicationListener applicationListener) {
        this.applicationListener = applicationListener;
        this.window = new Window();
    }

    public void run() {
        window.init();
        window.prepare();
        applicationListener.init();
        while (!window.windowShouldClose()) {
            window.clear();
            applicationListener.update();
            window.update();
            window.pollEvents();
        }
        cleanUp();
    }

    private void cleanUp() {
        applicationListener.cleanUp();
        window.close();
    }
}
