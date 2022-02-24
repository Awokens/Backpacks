package me.awokens.project.backpack.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class open implements Listener {

    /*
    opens the back preview when right click a shulker box
    - massive improvement, no longer need NBT API
     */

    @EventHandler
    public void Interact(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        if (!player.isSneaking()) return;
        ItemStack item = event.getItem();

        if (item == null) return;

        Inventory inv = Bukkit.createInventory(null, 27, "Backpack of " + player.getName()); // yes I know it's deprecated, calm down nerds.

        try {
            BlockStateMeta bmeta = (BlockStateMeta) item.getItemMeta();
            if (!(bmeta.getBlockState() instanceof ShulkerBox shulker)) return;

            ItemStack[] content = shulker.getInventory().getContents();
            if (content.length > 0) inv.setContents(content);

            player.openInventory(inv);
            player.playSound(player, Sound.BLOCK_SHULKER_BOX_OPEN, 1, 1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
