package dev.acog.bukkit.language.examples;

import dev.acog.bukkit.language.core.BukkitLanguage;
import dev.acog.bukkit.language.examples.view.ItemListView;
import io.typecraft.bukkit.view.BukkitView;
import io.typecraft.bukkit.view.ChestView;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

public class LanguagePlugin extends JavaPlugin  {

    public final BukkitLanguage language = BukkitLanguage.load(this, Locale.KOREA, getLangFolder(), false);

    @Override
    public void onEnable() {
        getLogger().info(KoreaLang.COMMAND_GUIDE.format(this));
    }

    private File getLangFolder() {
        return new File(getDataFolder(), "/langs/");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = sender instanceof Player ? ((Player) sender) : null;
        String head = args.length >= 1 ? args[0] : "";
        switch (head) {
            case "page": {
                if (p != null && p.isOp()) {
                    ChestView view = ItemListView.create(
                            Arrays.stream(Material.values())
                                    .filter(mat -> mat.isItem() && !mat.isAir())
                    );
                    BukkitView.openView(view, p, this);
                }
                break;
            }
            default: {
                sender.sendMessage(KoreaLang.COMMAND_GUIDE.format(this, label));
                break;
            }
        }
        return true;
    }

}
