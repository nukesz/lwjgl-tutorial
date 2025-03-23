package com.github.nukesz;

public interface ApplicationListener {

    void init(InputHandler inputHandler);

    void update(float deltaTime);

    void cleanUp();
}
