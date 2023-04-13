package org.red.has.item.murder;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.skill.murder.DoubleR;
import org.red.has.skill.murder.DoubleSL;
import org.red.has.skill.murder.DoubleSR;
import org.red.library.entity.player.NewPlayer;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public class DoubleI implements EventItem {

    @Override
    public String getCode() {
        return "double";
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void leftClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new DoubleR().skill(newPlayer);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void leftClickBlock(PlayerInteractEvent event) {
        this.leftClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_LEFT_CLICK_AIR)
    public void shiftLeftClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new DoubleSL().skill(newPlayer);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_LEFT_CLICK_BLOCK)
    public void shiftLeftClickBlock(PlayerInteractEvent event) {
        this.shiftLeftClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_RIGHT_CLICK_AIR)
    public void shiftRightClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new DoubleSR().skill(newPlayer);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_RIGHT_CLICK_BLOCK)
    public void shiftRightClickBlock(PlayerInteractEvent event) {
        this.shiftRightClickAir(event);
    }

}
