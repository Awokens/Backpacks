package me.awokens.project.backpack.listeners;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class close implements Listener {


    /*
    when player's inventory closes, it checks if the item held was a Shulker box,
    and see if player's inventory name was Backpack of <player>, then it saves
     */
    @EventHandler
    public void closeBackpack(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!(item.getType() == Material.SHULKER_BOX)) return;

        @NotNull Component title = event.getView().title();
        if (!title.toString().contains("Backpack of " + player.getName())) return;
        NBTItem data = new NBTItem(item);

        data.mergeCompound(serializeShulker(event.getView().getTopInventory()));
        player.getInventory().getItemInMainHand().setItemMeta(data.getItem().getItemMeta());

    }

    /*
    In case I ever use it other plugins, I got one here.
     */
    public NBTContainer serializeShulker(Inventory inv) {
        ItemStack item;
        NBTContainer nbt;
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 27; i++) {
            item = inv.getItem(i);
            try {
                nbt = NBTItem.convertItemtoNBT(item);
                nbt.setInteger("Slot", i);
                items.add(nbt.toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return new NBTContainer("{BlockEntityTag:{Items:[" + String.join(",", items) + "]}}");
    }
}
