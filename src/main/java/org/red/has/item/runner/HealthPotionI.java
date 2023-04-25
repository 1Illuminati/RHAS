package org.red.has.item.runner;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public class HealthPotionI implements EventItem {
    @Override
    public String getCode() {
        return "health_potion";
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void leftClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void leftClickBlock(PlayerInteractEvent event) {
        this.leftClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_RIGHT_CLICK_BLOCK)
    public void shiftLeftClickBlock(PlayerInteractEvent event) {
        this.leftClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void shiftLeftClickAir(PlayerInteractEvent event) {
        this.leftClickAir(event);
    }
}
