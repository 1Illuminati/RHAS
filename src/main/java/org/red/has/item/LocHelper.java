package org.red.has.item;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.has.Game;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public class LocHelper implements EventItem {
    @Override
    public String getCode() {
        return "locHelper";
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.LEFT_CLICK_BLOCK)
    public void leftClickBlock(PlayerInteractEvent event) {
        event.setCancelled(true);
        Game game = Game.getGame();
        Location location = event.getClickedBlock().getLocation().getBlock().getLocation();
        game.getSetting().setStartLoc(location);
        event.getPlayer().sendMessage(String.format("좌표 1 지정 완료 %d %d %d", location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void rightClickBlock(PlayerInteractEvent event) {
        event.setCancelled(true);
        Game game = Game.getGame();
        Location location = event.getClickedBlock().getLocation().getBlock().getLocation();
        game.getSetting().setEndLoc(location);
        event.getPlayer().sendMessage(String.format("좌표 2 지정 완료 %d %d %d", location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SWAP_HAND)
    public void swapHandItem(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        Game game = Game.getGame();
        Location location = event.getPlayer().getLocation().getBlock().getLocation();
        game.getSetting().setSpawnLoc(location);
        event.getPlayer().sendMessage(String.format("스폰 지정 완료 %d %d %d", location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }
}
