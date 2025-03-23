package com.github.nukesz;

public class GameEngine {

    private final Window window;
    private final ApplicationListener applicationListener;
    // number of seconds application has been running
    private float time;
    // seconds since last iteration of run loop
    private float deltaTime;
    // store time data from last iteration of run loop
    private long previousTime;
    private long currentTime;

    public GameEngine(ApplicationListener applicationListener) {
        this.applicationListener = applicationListener;
        this.window = new Window();
    }

    public void run() {
        time = 0;
        deltaTime = 1 / 60f;
        currentTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();

        window.init();
        window.prepare();
        InputHandler inputHandler = new InputHandler(window);
        applicationListener.init(inputHandler);
        while (!window.windowShouldClose()) {
            window.pollEvents();
            inputHandler.update();

            currentTime = System.currentTimeMillis();
            deltaTime = currentTime - previousTime;
            time += deltaTime;
            previousTime = currentTime;

            window.clear();
            applicationListener.update(deltaTime);
            window.update();
        }
        cleanUp();
    }

    private void cleanUp() {
        applicationListener.cleanUp();
        window.close();
    }
}
