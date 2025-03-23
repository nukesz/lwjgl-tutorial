package com.github.nukesz.examples;

import com.github.nukesz.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ExampleTriangleAnimation extends Example {

    private int programId;
    private Uniform<Vector> translation;
    private Uniform<Vector> baseColor;

    @Override
    public void init(InputHandler inputHandler) {
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
        translation.data.values[0] += 0.0002f;

        if (translation.data.values[0] > 1.2f)
            translation.data.values[0] = -1.2f;

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL20.glUseProgram(programId);

        translation.uploadData();
        baseColor.uploadData();
        GL30.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
    }

    public static void main(String[] args) {
        run(new ExampleTriangleAnimation());
    }
}
