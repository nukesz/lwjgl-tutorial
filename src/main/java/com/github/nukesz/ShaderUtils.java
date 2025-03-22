package com.github.nukesz;

import org.lwjgl.opengl.GL20;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShaderUtils {

    public static int initProgram(String vertexShaderFile, String fragmentShaderFile) {
        String fragmentShader = loadFileToString(fragmentShaderFile);
        String vertexShader = loadFileToString(vertexShaderFile);

        int vertexShaderId = loadShader(vertexShader, GL20.GL_VERTEX_SHADER);
        int fragmentShaderId = loadShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);

        int programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId, vertexShaderId);
        GL20.glAttachShader(programId, fragmentShaderId);

        GL20.glLinkProgram(programId);
        int[] status = new int[1];
        GL20.glGetProgramiv(programId, GL20.GL_LINK_STATUS, status);
        if (status[0] == GL20.GL_FALSE) {
//        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) {
            String errorMessage = GL20.glGetProgramInfoLog(programId);
            GL20.glDeleteProgram(programId);
            throw new RuntimeException("Program linking failed: " + errorMessage);
        }
        return programId;
    }

    public static int loadShader(String code, int type) {
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, code);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            String errorMessage = GL20.glGetShaderInfoLog(shaderID);
            GL20.glDeleteShader(shaderID);
            throw new RuntimeException("Shader compilation failed: " + errorMessage);
        }
        return shaderID;
    }

    public static String loadFileToString(String resourcePath) {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(resourcePath).toURI());
            return Files.readString(path);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
