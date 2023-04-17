package dev.acog.bukkit.language.core.utils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResourcesUtils {

    public static List<Path> getResourceFolderFiles(String folder, boolean allFile) {
        try (
                FileSystem fs = FileSystems.newFileSystem(getJarURL(), Collections.emptyMap());
                Stream<Path> paths = allFile
                        ? Files.walk(fs.getPath(folder))
                        : Files.list(fs.getPath(folder))
        ) {
            return paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            return Collections.emptyList();
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

    public static List<File> getLangFiles(File folder) {
        return getFolderFiles(folder)
                .filter(file -> file.isFile() && file.getName().endsWith(".yml"))
                .collect(Collectors.toList());
    }

    public static List<File> getLangAllFiles(File folder) {
        return getFolderFiles(folder)
                .flatMap(FileResourcesUtils::getFolderFiles)
                .filter(file -> file.isFile() && file.getName().endsWith(".yml"))
                .collect(Collectors.toList());
    }

    private static Stream<File> getFolderFiles(File folder) {
        File[] files = folder.listFiles();
        return files != null ? Arrays.stream(files) : Stream.empty();
    }

}

