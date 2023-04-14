package dev.acog.bukkit.language.core;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LocateLanguage {

    private final String locate;
    private final Map<String, Language> languages = new HashMap<>();

    private LocateLanguage(String locate) {
        this.locate = locate;
    }

    public static LocateLanguage load(String locate, File path) {
        return new LocateLanguage(locate).reload(path);
    }

    public LocateLanguage reload(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        for (Map.Entry<String, Object> entry : config.getValues(false).entrySet()) {
            if (!(entry.getValue() instanceof String)) {
                continue;
            }
            languages.put(entry.getKey(), new Language((String) entry.getValue()));
        }
        return this;
    }

    public String get(String key) {
        return getLang(key).get();
    }

    public Language getLang(String key) {
        if (!languages.containsKey(key)) {
            throw new NoSuchElementException("Language data not found for key : " + key + " locate : " + this.locate);
        }
        return languages.get(key);
    }

    public Map<String, Language> getLanguages() {
        return languages;
    }

}
