package org.red.has.item;

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
        game.setStart(event.getClickedBlock().getLocation());
        event.getPlayer().sendMessage("Start set to " + event.getClickedBlock().getLocation());
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void rightClickBlock(PlayerInteractEvent event) {
        event.setCancelled(true);
        Game game = Game.getGame();
        game.setEnd(event.getClickedBlock().getLocation());
        event.getPlayer().sendMessage("End set to " + event.getClickedBlock().getLocation());
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SWAP_HAND)
    public void dropItem(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        Game game = Game.getGame();
        game.setSpawn(event.getPlayer().getLocation());
        event.getPlayer().sendMessage("Spawn set to " + event.getPlayer().getLocation());
    }
}
