package me.awokens.project.backpack.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class open implements Listener {


    /*
    opens the back preview when right click a shulker box
     */
    @EventHandler
    public void openBackPack(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        if (!player.isSneaking()) return;
        ItemStack item = event.getItem();
        if (item == null || !(item.getType().name().contains("Shulker"))) return;

        NBTItem data = new NBTItem(item);
        Inventory inv = Bukkit.createInventory(null, 27, "Backpack of " + player.getName());

        if (!data.hasKey("BlockEntityTag")) {
            player.openInventory(inv);
            return;
        }
        NBTCompound items = data.getCompound("BlockEntityTag");
        for (NBTListCompound compound : items.getCompoundList("Items")) {
            inv.setItem(compound.getInteger("Slot"), NBTItem.convertNBTtoItem(compound));
        }
        player.openInventory(inv);

    }

}
