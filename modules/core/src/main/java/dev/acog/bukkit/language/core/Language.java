package dev.acog.bukkit.language.core;

public class Language {

    private final String value;

    public Language(String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }

    public String replace(String[]... pairs) {
        String result = this.value;
        for (String[] pair : pairs) {
            result = result.replace(pair[0], pair[1]);
        }
        return result;
    }

    public static String[] pair(String key, String value) {
        return new String[] { key, value };
    }

}
