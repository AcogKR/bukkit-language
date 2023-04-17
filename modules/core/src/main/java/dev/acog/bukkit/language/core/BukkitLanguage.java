package dev.acog.bukkit.language.core;

import dev.acog.bukkit.language.core.utils.FileResourcesUtils;
import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class BukkitLanguage {

    private final Locale defaultLocale;
    private final ConcurrentHashMap<Locale, LocaleLanguage> languages;

    /**
     * This function creates and returns a BukkitLanguage object that stores a multilingual message
     * map based on the folder path containing language files and the provided locale information.
     * @param plugin JavaPlugin.class
     * @param locale Default locale
     * @param folder Language folder path
     * @param allFile Whether to apply all files in the path
     * @return BukkitLanguage.class
     */
    public static BukkitLanguage load(
            JavaPlugin plugin,
            Locale locale,
            File folder,
            boolean allFile
    ) {
        String folderName = "/" + folder.getName() + "/";
        FileResourcesUtils.getResourceFolderFiles(folderName, allFile).stream()
                .filter(path -> !(new File(plugin.getDataFolder(), path.toString()).exists()))
                .forEach(path -> plugin.saveResource(path.toString().substring(1), false));

        ConcurrentHashMap<Locale, LocaleLanguage> languages = new ConcurrentHashMap<>();
        List<File> files = allFile ? FileResourcesUtils.getLangAllFiles(folder) : FileResourcesUtils.getLangFiles(folder);

        if (files.isEmpty()) {
            throw new NoSuchElementException("No language files found in the directory.");
        }

        for (File file : files) {
            String fileName = file.getName();
            Locale fileLocale = convent(fileName.substring(0, fileName.length() - 4));
            languages.put(fileLocale, LocaleLanguage.load(fileLocale, file));
        }

        return new BukkitLanguage(locale, languages);
    }

    public LocaleLanguage getLanguage(Locale locale) {
        LocaleLanguage language = languages.getOrDefault(locale, getLanguage());
        if (language == null) {
            throw new NullPointerException("Language not found for locale : " + locale);
        }
        return language;
    }

    public LocaleLanguage getLanguage(String locale) {
        return getLanguage(convent(locale));
    }

    public LocaleLanguage getLanguage() {
        return getLanguage(defaultLocale);
    }

    private static Locale convent(String value) {
        String[] split = value.split("_");
        return new Locale(split[0], split[1]);
    }

}


