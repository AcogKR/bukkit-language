package dev.acog.bukkit.language.examples;

import dev.acog.bukkit.language.core.BukkitLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Locale;

public class LanguagePlugin extends JavaPlugin implements CommandExecutor {

    private final BukkitLanguage language = BukkitLanguage.load(this, Locale.KOREA, getLangFolder(), false);

    @Override
    public void onEnable() {
    }

    private File getLangFolder() {
        return new File(getDataFolder(), "/langs/");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return true;
    }
}
