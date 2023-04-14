package dev.acog.bukkit.language.core;

import dev.acog.bukkit.language.core.utils.FileResourcesUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitLanguage {

    private final File file;
    private final String defaultLocate;
    private final ConcurrentHashMap<String, LocateLanguage> languages = new ConcurrentHashMap<>();

    private BukkitLanguage(File file, String locate) {
        this.file = file;
        this.defaultLocate = locate.toLowerCase();
    }

    /**
     * This method creates and returns a BukkitLanguage instance by loading the language
     * file located at the specified path and using the given locate parameter.
     * @param plugin JavaPlugin Class
     * @param locale ISO-3166 + ISO-3166
     * @param file language pack path
     * @return BukkitLanguage.class
     */
    public static BukkitLanguage load(JavaPlugin plugin, String locale, File file) {
        List<Path> resources = FileResourcesUtils.getPathsFromResourceJar(file);
        if (resources != null) {
            for (Path path : resources) {
                plugin.saveResource(path.toString(), false);
            }
        }
        return new BukkitLanguage(file, locale).reload();
    }

    public BukkitLanguage reload() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".yml");
        File[] files = file.listFiles(filter);

        if (files == null || files.length == 0) {
            throw new NoSuchElementException("No language files found in the directory.");
        }
        for (File locateFile : files) {
            String locate = locateFile.getName().replace(".yml", "").toLowerCase();
            languages.put(locate, LocateLanguage.load(locate, locateFile));
        }
        return this;
    }

    /**
     * This function returns a LocateLanguage object that contains language data for the given locate.
     * @param locate ISO-3166 + ISO-3166
     * @throws NullPointerException locate language not fount
     * @return LocateLanguage.class
     */
    public LocateLanguage getLanguage(String locate) {
        LocateLanguage language = languages.getOrDefault(locate.toLowerCase(), getLanguage());
        if (language == null) {
            throw new NullPointerException("Language not found for locate : " + locate);
        }
        return language;
    }

    public LocateLanguage getLanguage() {
        return getLanguage(defaultLocate);
    }

    public LocateLanguage getLanguage(Player player) {
        return getLanguage(player.getLocale());
    }

}
