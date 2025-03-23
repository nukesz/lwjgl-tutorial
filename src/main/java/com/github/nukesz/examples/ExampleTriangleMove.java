package com.github.nukesz.examples;

import com.github.nukesz.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import static org.lwjgl.glfw.GLFW.*;

public class ExampleTriangleMove extends Example {

    private int programId;
    private Uniform<Vector> translation;
    private Uniform<Vector> baseColor;
    private InputHandler inputHandler;
    private final float speed = 0.001f;

    @Override
    public void init(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        programId = ShaderUtils.initProgram("uniform_position.vert", "uniform_color.frag");
        GL30.glLineWidth(5);

        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        float[] positionData = {
                0.0f, 0.2f, 0.0f,
                0.2f, -0.2f, 0.0f,
                -0.2f, -0.2f, 0.0f};
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programId, "position");

        translation = new Uniform<>("vec3", new Vector(-0.5f, 0.0f, 0.0f));
        translation.locateVariable(programId, "translation");
        baseColor = new Uniform<>("vec3", new Vector(1.0f, 0.0f, 0.0f));
        baseColor.locateVariable(programId, "baseColor");
    }

    @Override
    public void update(float deltaTime) {
        float distance = speed * deltaTime;
        if (inputHandler.isKeyPressed(GLFW_KEY_A)) {
            translation.data.values[0] -= distance;
        }
        if (inputHandler.isKeyPressed(GLFW_KEY_D)) {
            translation.data.values[0] += distance;
        }
        if (inputHandler.isKeyPressed(GLFW_KEY_S)) {
            translation.data.values[1] -= distance;
        }
        if (inputHandler.isKeyPressed(GLFW_KEY_W)) {
            translation.data.values[1] += distance;
        }

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL20.glUseProgram(programId);

        translation.uploadData();
        baseColor.uploadData();
        GL30.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
    }

    public static void main(String[] args) {
        run(new ExampleTriangleMove());
    }
}
