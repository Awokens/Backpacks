package me.awokens.project.backpack;

import me.awokens.project.backpack.listeners.click;
import me.awokens.project.backpack.listeners.close;
import me.awokens.project.backpack.listeners.open;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Backpack extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            this.getServer().getPluginManager().registerEvents(new open(), this);
            this.getServer().getPluginManager().registerEvents(new click(), this);
            this.getServer().getPluginManager().registerEvents(new close(), this);
        } catch (NullPointerException e) {
            e.printStackTrace();
            this.getPluginLoader().disablePlugin(this);
        }
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN +
                        "Loaded Backpack plugin " + this.getDescription().getVersion(),
                ChatColor.GREEN + "Author: " + this.getDescription().getAuthors()
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}


