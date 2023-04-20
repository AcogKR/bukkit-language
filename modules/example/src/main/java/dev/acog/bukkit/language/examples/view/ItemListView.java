package dev.acog.bukkit.language.examples.view;

import io.typecraft.bukkit.view.ChestView;
import io.typecraft.bukkit.view.ClickEvent;
import io.typecraft.bukkit.view.ViewAction;
import io.typecraft.bukkit.view.ViewControl;
import io.typecraft.bukkit.view.page.PageContext;
import io.typecraft.bukkit.view.page.PageViewLayout;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 예제 파일로 typecraft.io 의 bukkit-view 라이브러리 기능
public class ItemListView {

    public static ChestView create(Stream<Material> materials) {
        return createLayout(materials).toView(1);
    }

    public static PageViewLayout createLayout(Stream<Material> materials) {
        List<Function<PageContext, ViewControl>> pagingContents =
                materials
                        .map(material ->
                                // Why `Function<PageContext, ViewControl` not just `ViewControl`?
                                // Because of laziness to avoid lag from immediate calculation the all page items.
                                (Function<PageContext, ViewControl>) ctx ->
                                        ViewControl.of(
                                                createDisplayItem(material),
                                                e -> onClick(e, material)
                                        )
                        )
                        .collect(Collectors.toList());
        return PageViewLayout.ofDefault(
                "materials",
                6,
                Material.STONE_BUTTON,
                pagingContents
        );
    }

    private static ItemStack createDisplayItem(Material material) {
        return new ItemStack(material);
    }

    private static ViewAction onClick(ClickEvent event, Material material) {
        Player clicker = event.getClicker();
        if (clicker.isOp()) {
            clicker.getInventory().addItem(new ItemStack(material));
        }
        return ViewAction.NOTHING;
    }

}
