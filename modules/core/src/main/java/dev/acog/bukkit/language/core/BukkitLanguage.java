package dev.acog.bukkit.language.core;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.FilenameFilter;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitLanguage {

    private final File path;
    private final String defaultLocate;
    private final ConcurrentHashMap<String, LocateLanguage> languages = new ConcurrentHashMap<>();

    private BukkitLanguage(File path, String locate) {
        this.path = path;
        this.defaultLocate = locate.toLowerCase();
    }

    public BukkitLanguage reload() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".yml");
        File[] files = this.path.listFiles(filter);
        if (files == null || files.length == 0) {
            throw new NoSuchElementException("No language files found in the directory.");
        }
        for (File locatePath : files) {
            String locate = locatePath.getName().replace(".yml", "").toLowerCase();
            languages.put(locate, LocateLanguage.load(locate, locatePath));
        }
        return this;
    }

    /**
     * This function returns a LocateLanguage object that contains language data for the given locate.
     * @throws NumberFormatException locate language not fount
     * @return LocateLanguage.class
     */
    public LocateLanguage getLanguage(String locate) {
        LocateLanguage language = languages.getOrDefault(
                locate.toLowerCase(),
                languages.get(this.defaultLocate)
        );
        if (language == null) {
            throw new NullPointerException("Language not found for locate : " + locate);
        }
        return language;
    }

    public LocateLanguage getLanguage(Player player) {
        return getLanguage(player.getLocale());
    }

    /**
     * This method creates and returns a BukkitLanguage instance by loading the language
     * file located at the specified path and using the given locate parameter.
     * @return BukkitLanguage.class
     */
    public static BukkitLanguage load(String locale, File path) {
        return new BukkitLanguage(path, locale).reload();
    }

}
