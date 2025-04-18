package com.github.nukesz.examples;

import com.github.nukesz.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ExampleTrianglesUniform extends Example {

    private ShaderProgram shaderProgram;
    private Uniform<Vector> translation1;
    private Uniform<Vector> translation2;
    private Uniform<Vector> baseColor1;
    private Uniform<Vector> baseColor2;

    @Override
    public void init(InputHandler inputHandler) {
        shaderProgram = new ShaderProgram("uniform_position.vert", "uniform_color.frag");
        int programId = shaderProgram.getProgramId();
        GL30.glLineWidth(5);

        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        float[] positionData = {
                0.0f, 0.2f, 0.0f,
                0.2f, -0.2f, 0.0f,
                -0.2f, -0.2f, 0.0f};
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programId, "position");

        translation1 = new Uniform<>("vec3", new Vector(-0.5f, 0.0f, 0.0f));
        translation1.locateVariable(programId, "translation");
        translation2 = new Uniform<>("vec3", new Vector(0.5f, 0.0f, 0.0f));
        translation2.locateVariable(programId, "translation");
        baseColor1 = new Uniform<>("vec3", new Vector(1.0f, 0.0f, 0.0f));
        baseColor1.locateVariable(programId, "baseColor");
        baseColor2 = new Uniform<>("vec3", new Vector(0.0f, 0.0f, 1.0f));
        baseColor2.locateVariable(programId, "baseColor");
    }

    @Override
    public void update(float deltaTime) {
        shaderProgram.bind();

        translation1.uploadData();
        baseColor1.uploadData();
        GL30.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);

        translation2.uploadData();
        baseColor2.uploadData();
        GL30.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);

        shaderProgram.unbind();
    }

    @Override
    public void cleanUp() {
        shaderProgram.cleanup();
    }

    public static void main(String[] args) {
        run(new ExampleTrianglesUniform());
    }
}
