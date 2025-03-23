package com.github.nukesz;

import org.lwjgl.opengl.GL20;

public class Uniform<T> {
    private String dataType;
    public T data;
    private int[] resultArray = new int[1];
    private int variableId;

    public Uniform(String dataType, T data) {
        this.dataType = dataType;
        this.data = data;
    }

    public void locateVariable(int programId, String variableName) {
        variableId = GL20.glGetUniformLocation(programId, variableName);
    }

    public void uploadData() {
        if (variableId == -1) {
            throw new RuntimeException("No variable to upload data");
        }

        switch (dataType) {
            case "int", "bool" -> GL20.glUniform1i(variableId, (Integer) data);
            case "float" -> GL20.glUniform1f(variableId, (Float) data);
            case "vec2" -> {
                Vector v = (Vector) data;
                GL20.glUniform2f(variableId,
                        (float) v.values[0],
                        (float) v.values[1]);
            }
            case "vec3" -> {
                Vector v = (Vector) data;
                GL20.glUniform3f(variableId,
                        (float) v.values[0],
                        (float) v.values[1],
                        (float) v.values[2]);
            }
            case "vec4" -> {
                Vector v = (Vector) data;
                GL20.glUniform4f(variableId, (float) v.values[0],
                        (float) v.values[1],
                        (float) v.values[2],
                        (float) v.values[3]);
            }
        }
    }
}
