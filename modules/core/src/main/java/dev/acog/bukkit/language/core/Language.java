package dev.acog.bukkit.language.core;

import lombok.Data;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class Language {

    private final static Pattern pattern = Pattern.compile("\\[color=#(\\d+)\\]");

    private final String lang;

    public static Language create(String lang) {
        String format = from(ChatColor.translateAlternateColorCodes('&', lang));
        return new Language(format);
    }

    public String replace(String[]... pairs) {
        String result = this.lang;
        for (String[] pair : pairs) {
            result = result.replace(pair[0], pair[1]);
        }
        return result;
    }

    public String toString() {
        return this.lang;
    }

    public static String[] pair(String key, String value) {
        return new String[] { key, value };
    }

    private static String from(String value) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            String color = ChatColor.of(matcher.group(1)).toString();
            result.append(value, matcher.start(), matcher.end()).replace(matcher.start(), matcher.end(), color);
        }
        return result.toString();
    }

}

