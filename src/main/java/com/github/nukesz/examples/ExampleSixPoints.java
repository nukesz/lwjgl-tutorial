package com.github.nukesz.examples;

import com.github.nukesz.Attribute;
import com.github.nukesz.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ExampleSixPoints extends Example {

    private int programId;

    @Override
    public void initialize() {
        programId = ShaderUtils.initProgram("position.vert", "color.frag");
        GL30.glLineWidth(5);

        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        float[] positionData = {
                0.8f, 0.0f, 0.0f,
                0.4f, 0.6f, 0.0f,
                -0.4f, 0.6f, 0.0f,
                -0.8f, 0.0f, 0.0f,
                -0.4f, -0.6f, 0.0f,
                0.4f, -0.6f, 0.0f};
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programId, "position");
    }

    @Override
    public void update() {
        GL30.glUseProgram(programId);
        GL30.glDrawArrays(GL11.GL_TRIANGLE_FAN, 0, 6);
    }

    public static void main(String[] args) {
        run(new ExampleSixPoints());
    }
}
