package com.github.nukesz.examples;

import com.github.nukesz.Attribute;
import com.github.nukesz.InputHandler;
import com.github.nukesz.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ExampleTwoShapes extends Example {

    private int programId;
    private int vaoTriangleId;
    private int vaoSquareId;

    @Override
    public void init(InputHandler inputHandler) {
        programId = ShaderUtils.initProgram("position.vert", "color.frag");
        GL30.glLineWidth(5);

        vaoTriangleId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoTriangleId);
        float[] positionDataTriangle = {
                -0.5f, 0.8f, 0.0f,
                -0.2f, 0.2f, 0.0f,
                -0.8f, 0.2f, 0.0f};
        Attribute positionAttributeTriangle = new Attribute("vec3", positionDataTriangle);
        positionAttributeTriangle.associateVariable(programId, "position");

        vaoSquareId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoSquareId);
        float[] positionDataSquare = {
                0.8f, 0.8f, 0.0f,
                0.8f, 0.2f, 0.0f,
                0.2f, 0.2f, 0.0f,
                0.2f, 0.8f, 0.0f};
        Attribute positionAttributeSquare = new Attribute("vec3", positionDataSquare);
        positionAttributeSquare.associateVariable(programId, "position");
    }

    @Override
    public void update(float deltaTime) {
        GL30.glUseProgram(programId);

        GL30.glBindVertexArray(vaoTriangleId);
        GL30.glDrawArrays(GL11.GL_LINE_LOOP, 0, 3);

        GL30.glBindVertexArray(vaoSquareId);
        GL30.glDrawArrays(GL11.GL_LINE_LOOP, 0, 4);
    }

    public static void main(String[] args) {
        run(new ExampleTwoShapes());
    }
}
