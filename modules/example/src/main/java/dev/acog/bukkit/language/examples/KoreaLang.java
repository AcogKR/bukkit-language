package dev.acog.bukkit.language.examples;

import java.util.Locale;

// 이런식으로 응용해서 사용이 가능하다.
public enum KoreaLang {
    LOAD_PLUGIN,
    COMMAND_GUIDE
    ;

    public String format(LanguagePlugin plugin, Object... args) {
        String value = plugin.language.getLanguage(Locale.KOREA).get(toString().toLowerCase());
        return String.format(value, args);
    }

}
