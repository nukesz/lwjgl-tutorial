package com.github.nukesz;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static String loadResource(String resourcePath) {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(resourcePath).toURI());
            return Files.readString(path);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
