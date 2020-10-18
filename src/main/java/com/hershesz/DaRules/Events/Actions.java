package com.hershesz.DaRules.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Actions implements Listener {
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player p = event.getPlayer();
        boolean isSneaking = p.isSneaking();
        if(isSneaking) {
            p.kickPlayer("You broke Da Rules! You have been banned! (This is a test, not really)");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();

        if (b.getType() == Material.OAK_LEAVES || b.getType() == Material.BIRCH_LEAVES) {
            p.kickPlayer("You broke Da Rules! You have been banned! (This is a test, not really)");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK)){
            p.kickPlayer("You broke Da Rules! You have been banned! (This is a test, not really)");
        }
        if (p.getTargetBlock(null, 15).getType().equals(Material.COBBLESTONE_STAIRS)) {
            p.kickPlayer("You broke Da Rules! You have been banned! (This is a test, not really)");
        }
    }
}
