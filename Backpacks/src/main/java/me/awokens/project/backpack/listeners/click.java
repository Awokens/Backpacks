package me.awokens.project.backpack.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class click implements Listener {


    /*
    checks if player has shulker preview open, then cancels the event if they attempt
    to move the shulker box, basic security for preventing duplication.
     */
    @EventHandler
    public void clickBackPack(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getView().title().toString().contains("Backpack of " + player.getName())) return;
        if (event.getCurrentItem() != null && event.getCurrentItem().getType().name().contains("Shulker")) {
            event.setCancelled(true);
        }
        if (event.getAction() == InventoryAction.PLACE_ALL || event.getAction() == InventoryAction.PICKUP_ALL) {
            event.setCancelled(true);
        }

        if (event.isCancelled()) {
            player.sendMessage(ChatColor.RED + "Illegal backpack click movement!");
        }
    }

}
