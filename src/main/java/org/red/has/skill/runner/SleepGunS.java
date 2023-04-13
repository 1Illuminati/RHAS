package org.red.has.skill.runner;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.red.has.Util;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.util.async.Scheduler;

import java.util.List;

public class SleepGunS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 20;
    }

    @Override
    public String code() {
        return "sleep_gun";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 수면총알 발사!";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Location loc = player.getLocation().clone();
        List<Vector> line = Util.line(loc.getDirection(), 15, 1);

        Scheduler.repeatDelayScheduler(new Scheduler.RunnableEx() {
            @Override
            public void function() {
                for (LivingEntity livingEntity : Util.getTarget(loc.clone().add(line.get(this.getCount())), 1)) {
                    if (livingEntity.equals(player.getPlayer()))
                        continue;

                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 4));
                    stop();
                }
            }
        }, 1, line.size() - 1);
        return true;
    }
}
