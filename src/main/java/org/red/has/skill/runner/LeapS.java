package org.red.has.skill.runner;

import org.bukkit.Location;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class LeapS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 20;
    }

    @Override
    public String code() {
        return "leap";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 앞으로 도약합니다.";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Location location = player.getLocation();
        player.setVelocity(location.getDirection().multiply(3));
        return true;
    }
}
