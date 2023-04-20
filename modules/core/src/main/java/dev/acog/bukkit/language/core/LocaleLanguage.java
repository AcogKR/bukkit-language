package dev.acog.bukkit.language.core;

import lombok.Data;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class LocaleLanguage {

    private final Locale locale;
    private final Map<String, Language> languages;

    /**
     * This function takes a locale and file path as parameters and creates and returns a LocaleLanguage object.
     * This object includes a message map for multilingual support, and loads and maps YAML configuration from the specified file.
     * @param locale language pack locale
     * @param file language pack file path
     * @return LocaleLanguage.class
     */
    public static LocaleLanguage load(
            Locale locale,
            File file
    ) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Map<String, Language> languages = config.getValues(false).entrySet().stream()
                .filter(entry -> entry.getValue() instanceof String)
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey().toLowerCase(), Language.create((String) entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new LocaleLanguage(locale, languages);
    }

    public Language getLang(String key) {
        if (!languages.containsKey(key)) {
            throw new NoSuchElementException("Language data not found for key : " + key + "locale : " + locale.toString());
        }
        return languages.get(key);
    }

    public String get(String key) {
        return getLang(key).toString();
    }

    public Map<String, Language> getLanguages() {
        return languages;
    }

}
