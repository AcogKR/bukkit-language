package dev.acog.bukkit.language.plugin;

import dev.acog.bukkit.language.core.BukkitLanguage;
import dev.acog.bukkit.language.core.LocateLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class LanguagePlugin extends JavaPlugin implements CommandExecutor {

    private final BukkitLanguage language = BukkitLanguage.load("ko_kr", getLangFolder());

    @Override
    public void onEnable() {
        LocateLanguage locateLanguage = LocateLanguage.load("en_us", new File(getDataFolder(), "en_us.yml"));
        getLogger().info(locateLanguage.get("start_message"));
    }

    private File getLangFolder() {
        return new File(getDataFolder() + "/langs/");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        LocateLanguage locateLanguage = language.getLanguage(player);

        if (args[0].equals("reload")) {
            language.reload();
        } else {
            player.sendMessage(locateLanguage.get("help.message"));
        }
        return true;
    }
}
