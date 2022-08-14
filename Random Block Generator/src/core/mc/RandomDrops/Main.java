package core.mc.RandomDrops;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("random").setExecutor((CommandExecutor) new RandomDropsMe());
        getServer().getPluginManager().registerEvents((Listener) new RandomDropsMe(), this);
    }


}

