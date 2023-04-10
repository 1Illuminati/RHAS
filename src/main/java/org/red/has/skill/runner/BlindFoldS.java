package org.red.has.skill.runner;

import org.bukkit.Location;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.util.async.Scheduler;

public class BlindFoldS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 30;
    }

    @Override
    public String code() {
        return "blind_fold";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 독침 발사";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Location loc = player.getLocation().clone();

        Scheduler.repeatDelayScheduler(new Scheduler.RunnableEx() {
            @Override
            public void function() {

            }
        }, 1, 20);
        return true;
    }
}
