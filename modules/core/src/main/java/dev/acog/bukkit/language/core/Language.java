package dev.acog.bukkit.language.core;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language {

    private final String value;
    private final static Pattern pattern = Pattern.compile("\\[color=#(\\d+)\\]");

    public Language(String value) {
        this.value = from(ChatColor.translateAlternateColorCodes('&', value));
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

    private String from(String value) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            String color = ChatColor.of(matcher.group(1)).toString();
            result.append(value, matcher.start(), matcher.end())
                    .replace(matcher.start(), matcher.end(), color);
        }
        return result.toString();
    }

    public static String[] pair(String key, String value) {
        return new String[] { key, value };
    }

}
