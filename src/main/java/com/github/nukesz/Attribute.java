package com.github.nukesz;

import org.lwjgl.opengl.GL20;

public class Attribute {

    private final String dataType;
    private final float[] dataArray;
    private final int bufferId;

    public Attribute(String dataType, float[] dataArray) {
        this.dataType = dataType;
        this.dataArray = dataArray;
        bufferId = GL20.glGenBuffers();
        uploadData();
    }

    private void uploadData() {
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, bufferId);
        GL20.glBufferData(GL20.GL_ARRAY_BUFFER, dataArray, GL20.GL_STATIC_DRAW);
    }

    public void associateVariable(int programId, String variableName) {
        int variableId = GL20.glGetAttribLocation(programId, variableName);
        if (variableId == -1) {
            return;
        }

        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, bufferId);
        switch (dataType) {
            case "int" -> GL20.glVertexAttribPointer(variableId, 1, GL20.GL_INT, false, 0, 0);
            case "float" -> GL20.glVertexAttribPointer(variableId, 1, GL20.GL_FLOAT, false, 0, 0);
            case "vec2" -> GL20.glVertexAttribPointer(variableId, 2, GL20.GL_FLOAT, false, 0, 0);
            case "vec3" -> GL20.glVertexAttribPointer(variableId, 3, GL20.GL_FLOAT, false, 0, 0);
            case "vec4" -> GL20.glVertexAttribPointer(variableId, 4, GL20.GL_FLOAT, false, 0, 0);
            default -> throw new RuntimeException("Attribute " + variableName + " has unknown type " + dataType);
        }

        GL20.glEnableVertexAttribArray(variableId);
    }
}
