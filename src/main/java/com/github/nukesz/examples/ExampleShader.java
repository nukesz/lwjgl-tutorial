package com.github.nukesz.examples;

import com.github.nukesz.InputHandler;
import com.github.nukesz.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ExampleShader extends Example{

    private int programId;

    @Override
    public void init(InputHandler inputHandler) {
        programId = ShaderUtils.initProgram("vertex_shader.vert", "fragment_shader.frag");

        int vaoRef = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoRef);

        GL30.glPointSize(10);
    }

    @Override
    public void update(float deltaTime) {
        GL30.glUseProgram(programId);
        GL30.glDrawArrays(GL11.GL_POINTS, 0, 1);
    }

    public static void main(String[] args) {
        run(new ExampleShader());
    }
}
