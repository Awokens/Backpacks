package me.awokens.project.backpack.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class open implements Listener {

    /*
    opens the back preview when right click a shulker box
     */

    /*
    make interact event left click only
    and then cancel other events like breaking insta blocks
    goodnight me.
     */


    @EventHandler
    public void Interact(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        if (!player.isSneaking()) return;
        ItemStack item = event.getItem();

        if (item == null || !(item.getType() == Material.SHULKER_BOX)) return;

        NBTItem data = new NBTItem(item);
        Inventory inv = Bukkit.createInventory(null, 27, "Backpack of " + player.getName()); // yes I know it's deprecated, calm down nerds.

        if (!data.hasKey("BlockEntityTag")) {
            player.openInventory(inv);
            return;
        }
        try {
            NBTCompound items = data.getCompound("BlockEntityTag");
            for (NBTListCompound compound : items.getCompoundList("Items")) {
                inv.setItem(compound.getInteger("Slot"), NBTItem.convertNBTtoItem(compound));
            }
            player.openInventory(inv);
            Location location = player.getLocation();
            location.getWorld().playSound(location, Sound.BLOCK_SHULKER_BOX_OPEN, 1, 1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
