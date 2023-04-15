package dev.acog.bukkit.language.core.utils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResourcesUtils {

    private FileResourcesUtils() {
    }

    public static List<Path> getPathsFromResourceJar(String folder) {
        try (
            FileSystem fs = FileSystems.newFileSystem(getJarURL(), Collections.emptyMap());
            Stream<Path> paths = Files.walk(fs.getPath(folder))
        ) {
            return paths.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            return null;
        }
    }

    private static URI getJarURL() throws URISyntaxException {
        return URI.create("jar:file:" + FileResourcesUtils.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath()
        );
    }
}

