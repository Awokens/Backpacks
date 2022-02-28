package me.awokens.project.backpack.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

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
        ItemStack item = event.getCurrentItem();
        /*
        added event.isCancelled boolean check so the code doesn't waste time to cancelling the event when it already was.
         */
        BlockStateMeta bmeta = (BlockStateMeta) item.getItemMeta();
        if (!(bmeta.getBlockState() instanceof ShulkerBox shulker)) {
            event.setCancelled(true);
        }
        if (!event.isCancelled()) {
            ItemStack HotBar;
            try {
                HotBar = inv.getItem(event.getHotbarButton());


                bmeta = (BlockStateMeta) HotBar.getItemMeta();

                if ((bmeta.getBlockState() instanceof ShulkerBox)) event.setCancelled(true);
                bmeta = (BlockStateMeta) player.getInventory().getItemInMainHand().getItemMeta();
                if ((bmeta instanceof ShulkerBox)) event.setCancelled(true);


            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        if (!event.isCancelled()) return;
        player.sendMessage(ChatColor.RED + "Illegal backpack click movement!");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 1);
    }


}
