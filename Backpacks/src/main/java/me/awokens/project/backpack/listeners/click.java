package me.awokens.project.backpack.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class click implements Listener {
    /*
    checks if player has shulker preview open, then cancels the event if they attempt
    to move the shulker box, basic security for preventing duplication.

    - Improved security checks and extended flexibility such as hotbar key swapping.
    */

    @EventHandler
    public void clickBackPack(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        String title = event.getView().title().toString();
        if (!title.contains("Backpack of " + player.getName())) return;

        Inventory inv = player.getInventory();
        ItemStack current = event.getCurrentItem();

        if (current != null && current.getType() == Material.SHULKER_BOX) event.setCancelled(true);
        ItemStack HotBar;
        try {
            HotBar = inv.getItem(event.getHotbarButton());
        } catch (ArrayIndexOutOfBoundsException e) {
            HotBar = null;
        }
        if (HotBar != null && HotBar.getType() == Material.SHULKER_BOX) event.setCancelled(true);
        if (player.getInventory().getItemInMainHand().getType() != Material.SHULKER_BOX) event.setCancelled(true);
        if (event.getView().getTopInventory().contains(Material.SHULKER_BOX)) event.setCancelled(true);
        if (!event.isCancelled()) return;

        player.sendMessage(ChatColor.RED + "Illegal backpack click movement!");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 1);
    }


}
