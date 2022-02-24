package me.awokens.project.backpack.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

public class close implements Listener {

    /*
    when player's inventory closes, it checks if the item held was a Shulker box,
    and see if player's inventory name was Backpack of <player>, then it saves
     */

    /*
    cleaned the hell out of this code. Pretty proud.
     */
    @EventHandler
    public void closeBackpack(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        @NotNull Component title = event.getView().title();
        if (!title.toString().contains("Backpack of " + player.getName())) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        Inventory inv = event.getView().getTopInventory();

        BlockStateMeta bmeta = (BlockStateMeta) item.getItemMeta();
        if (!(bmeta.getBlockState() instanceof ShulkerBox shulker)) return;
        shulker.getInventory().setContents(inv.getContents());

        bmeta.setBlockState(shulker);
        item.setItemMeta(bmeta);


    }
}
