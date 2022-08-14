package core.mc.RandomDrops;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomDropsMe implements CommandExecutor, Listener {

    public static List<Material> blocklist = new ArrayList<Material>();
    public static List<ItemStack> itemlist = new ArrayList<ItemStack>();
    public static HashMap<Material, ItemStack> randomList = new HashMap<Material, ItemStack>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Material mat : Material.values()) {
            {
                if (mat.isBlock()) {
                    blocklist.add(mat);
                    ItemStack item = new ItemStack(mat);
                    itemlist.add(item);
                }
            }
        }
        while (blocklist.size() > 0) {
            Random ran = new Random();
            int ranBlock = ran.nextInt(blocklist.size());
            int ranitem = ran.nextInt(itemlist.size());
            randomList.put(blocklist.get(ranBlock), itemlist.get(ranitem));
            blocklist.remove(ranBlock);
            itemlist.remove(ranitem);
        }
         sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[☁ϟ☂Core༉◠⋆]" + (ChatColor.GREEN + ": all blocks have been randomized!"));
            return true;

    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Random ran = new Random();
        int ranInt = ran.nextInt(50) + 1;
        for(int i = ranInt; i > 0; i--) {
            e.setCancelled(true);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), randomList.get(e.getBlock().getType()));
        }
        e.getBlock().setType(Material.AIR);

        }
    }
