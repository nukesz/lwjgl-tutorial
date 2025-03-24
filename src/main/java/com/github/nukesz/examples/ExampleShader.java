package com.github.nukesz.examples;

import com.github.nukesz.InputHandler;
import com.github.nukesz.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ExampleShader extends Example{

    private ShaderProgram shaderProgram;

    @Override
    public void init(InputHandler inputHandler) {
        shaderProgram = new ShaderProgram("vertex_shader.vert", "fragment_shader.frag");

        int vaoRef = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoRef);

        GL30.glPointSize(10);
    }

    @Override
    public void update(float deltaTime) {
        shaderProgram.bind();
        GL30.glDrawArrays(GL11.GL_POINTS, 0, 1);
        shaderProgram.unbind();
    }

    @Override
    public void cleanUp() {
        shaderProgram.cleanup();
    }

    public static void main(String[] args) {
        run(new ExampleShader());
    }
}
