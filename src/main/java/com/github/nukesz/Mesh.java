package com.github.nukesz;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {

    private final int vaoId;
    private final int vertexCount;
    private List<Integer> vboIds = new ArrayList<>();

    public Mesh(float[] positions, int vertexCount) {
        this.vertexCount = vertexCount;

        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        int positionVboId = glGenBuffers();
        vboIds.add(positionVboId);
        FloatBuffer positionsBuffer = MemoryUtil.memCallocFloat(positions.length);
        positionsBuffer.put(0, positions);
        glBindBuffer(GL_ARRAY_BUFFER, positionVboId);
        glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        MemoryUtil.memFree(positionsBuffer);
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void cleanup() {
        vboIds.forEach(GL30::glDeleteBuffers);
        glDeleteVertexArrays(vaoId);
    }
}

