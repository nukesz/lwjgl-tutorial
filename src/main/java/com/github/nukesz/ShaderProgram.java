package com.github.nukesz;

import static org.lwjgl.opengl.GL30.*;

public class ShaderProgram {

    private final int programId;

    public ShaderProgram(String vertexShaderFile, String fragmentShaderFile) {
        programId = glCreateProgram();
        initProgram(vertexShaderFile, fragmentShaderFile);
    }

    private void initProgram(String vertexShaderFile, String fragmentShaderFile) {
        String fragmentShader = FileUtils.loadResource(fragmentShaderFile);
        String vertexShader = FileUtils.loadResource(vertexShaderFile);

        int vertexShaderId = loadShader(vertexShader, GL_VERTEX_SHADER);
        int fragmentShaderId = loadShader(fragmentShader, GL_FRAGMENT_SHADER);

        link();

        glDetachShader(programId, vertexShaderId);
        glDetachShader(programId, fragmentShaderId);
        glDeleteShader(vertexShaderId);
        glDeleteShader(fragmentShaderId);
    }

    private void link() {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
            String errorMessage = glGetProgramInfoLog(programId);
            glDeleteProgram(programId);
            throw new RuntimeException("Program linking failed: " + errorMessage);
        }
    }

    private int loadShader(String code, int type) {
        int shaderId = glCreateShader(type);
        if (shaderId == 0) {
            throw new RuntimeException("Error creating shader type: " + type);
        }
        glShaderSource(shaderId, code);
        glCompileShader(shaderId);
        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            String errorMessage = glGetShaderInfoLog(shaderId);
            glDeleteShader(shaderId);
            throw new RuntimeException("Shader compilation failed: " + errorMessage);
        }
        glAttachShader(programId, shaderId);

        return shaderId;
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public int getProgramId() {
        return programId;
    }

    public void cleanup() {
        unbind();
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }
}
