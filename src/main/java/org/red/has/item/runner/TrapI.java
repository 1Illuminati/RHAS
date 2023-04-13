package org.red.has.item.runner;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.red.has.skill.runner.TrapS;
import org.red.library.entity.player.NewPlayer;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public class TrapI implements EventItem {
    @Override
    public String getCode() {
        return "trap";
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void leftClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new TrapS();
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void leftClickBlock(PlayerInteractEvent event) {
        this.leftClickAir(event);
    }
}
