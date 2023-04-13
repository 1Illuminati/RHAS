package org.red.has.item.murder;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.skill.murder.ShadowR;
import org.red.has.skill.murder.ShadowSL;
import org.red.has.skill.murder.ShadowSR;
import org.red.library.entity.player.NewPlayer;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

import java.util.Random;

public class ShadowI implements EventItem {
    @Override
    public String getCode() {
        return "shadow";
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void leftClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new ShadowR().skill(newPlayer);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void leftClickBlock(PlayerInteractEvent event) {
        this.leftClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_LEFT_CLICK_AIR)
    public void shiftLeftClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new ShadowSL().skill(newPlayer);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_LEFT_CLICK_BLOCK)
    public void shiftLeftClickBlock(PlayerInteractEvent event) {
        this.shiftLeftClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_RIGHT_CLICK_AIR)
    public void shiftRightClickAir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NewPlayer newPlayer = NewPlayer.getNewPlayer(player);
        new ShadowSR().skill(newPlayer);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_RIGHT_CLICK_BLOCK)
    public void shiftRightClickBlock(PlayerInteractEvent event) {
        this.shiftRightClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.HIT)
    public void hit(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof LivingEntity livingEntity) {
            int rand = new Random().nextInt(10);

            if (rand < 4)
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 0));
        }
    }
}
