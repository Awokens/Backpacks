package me.awokens.project.backpack.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class methods {

    public String getName(Player player) {
        return player.getOpenInventory().title().toString();
    }

    public String BPName(Player player) {
        return ChatColor.GRAY + "Backpack of " + player.getName();
    }

    public Inventory makeBP(Player player, ShulkerBox shulker) {

        Inventory backpack = Bukkit.createInventory(null, 9 * 3, BPName(player));
        ItemStack[] content = shulker.getInventory().getContents();
        backpack.setContents(content);

        return backpack; // TODO: UPDATE TO NEW METHOD FOR CREATING AN INVENTORY
    }

    public Boolean isPlayersBP(Player player) {
        return getName(player).equals(BPName(player));
    }

    public BlockStateMeta getBlockState(ItemStack item) {
        if (item == null) return null;
        return (BlockStateMeta) item.getItemMeta();
    }
}
