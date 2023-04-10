package org.red.has.skill.runner;

import org.bukkit.Location;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.util.async.Scheduler;

public class LocationChange extends AbstractSkill {
    @Override
    public int coolTime() {
        return 50;
    }

    @Override
    public String code() {
        return "location_change";
    }

    @Override
    public String skillMessage() {
        return null;
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        
        return true;
    }
}
