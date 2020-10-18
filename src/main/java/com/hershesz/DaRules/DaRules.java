package com.hershesz.DaRules;

import com.hershesz.DaRules.Commands.Info;
import com.hershesz.DaRules.Events.Actions;
import org.bukkit.plugin.java.JavaPlugin;

public class DaRules extends JavaPlugin implements org.bukkit.event.Listener {
    @Override
    public void onEnable() {
        this.getCommand("rules").setExecutor(new Info());
        getServer().getPluginManager().registerEvents(new Actions(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Hope you enjoyed!");
    }
}
