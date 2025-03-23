package com.github.nukesz;

public interface ApplicationListener {

    void init();

    void update(float deltaTime);

    void cleanUp();
}
