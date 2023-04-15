package dev.acog.bukkit.language.core;

import dev.acog.bukkit.language.core.utils.FileResourcesUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitLanguage {

    private final File path;
    private final String defaultLocale;
    private final ConcurrentHashMap<String, LocaleLanguage> languages = new ConcurrentHashMap<>();

    private BukkitLanguage(File file, String locale) {
        this.path = file;
        this.defaultLocale = locale.toLowerCase();
    }

    /**
     * This method creates and returns a BukkitLanguage instance by loading the language
     * file localed at the specified path and using the given locale parameter.
     * @param plugin JavaPlugin Class
     * @param locale ISO-3166 + ISO-3166
     * @param file language pack path
     * @return BukkitLanguage.class
     */
    public static BukkitLanguage load(JavaPlugin plugin, String locale, File file) {
        List<Path> resources = FileResourcesUtils.getPathsFromResourceJar(file.getName());
        if (resources != null) {
            for (Path path : resources) {
                plugin.saveResource(path.toString(), false);
            }
        }
        return new BukkitLanguage(file, locale).reload();
    }

    public BukkitLanguage reload() {
        File[] files = path.listFiles((dir, name) -> name.endsWith(".yml"));

        if (files == null || files.length == 0) {
            throw new NoSuchElementException("No language files found in the directory.");
        }
        languages.clear();
        for (File localeFile : files) {
            String locale = localeFile.getName().substring(0, localeFile.getName().length() - 4).toLowerCase();
            languages.put(locale, LocaleLanguage.load(localeFile));
        }
        return this;
    }

    /**
     * This function returns a localeLanguage object that contains language data for the given locale.
     * @param locale ISO-3166 + ISO-3166
     * @throws NullPointerException locale language not fount
     * @return localeLanguage.class
     */
    public LocaleLanguage getLanguage(String locale) {
        LocaleLanguage language = languages.getOrDefault(locale.toLowerCase(), getLanguage());
        if (language == null) {
            throw new NullPointerException("Language not found for locale : " + locale);
        }
        return language;
    }

    public LocaleLanguage getLanguage() {
        return getLanguage(defaultLocale);
    }

    public LocaleLanguage getLanguage(Player player) {
        return getLanguage(player.getLocale());
    }

}
