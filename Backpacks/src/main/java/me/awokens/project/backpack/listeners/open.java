package me.awokens.project.backpack.listeners;

import me.awokens.project.backpack.utils.methods;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class open implements Listener {

    /*
    opens the back preview when right click a shulker box
    - massive improvement, no longer need NBT API
     */

    methods utils = new methods(); // calls the methods class (backpack stuff)


    @EventHandler
    public void Interact(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        if (!(utils.getBlockState(event.getItem()) instanceof ShulkerBox shulker)) return;

        try {
            player.openInventory(utils.makeBP(player, shulker));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        player.playSound(player, Sound.BLOCK_SHULKER_BOX_OPEN, 1, 1);
    }

}
