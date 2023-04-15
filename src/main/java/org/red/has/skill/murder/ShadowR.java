package org.red.has.skill.murder;

import org.bukkit.Location;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class ShadowR extends AbstractSkill {
    @Override
    public int coolTime() {
        return 20;
    }

    @Override
    public String code() {
        return "shadow_r";
    }

    @Override
    public String skillMessage() {
        return "분노 우클릭 사용";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Location location = player.getLocation();
        player.setVelocity(location.getDirection().multiply(4));
        return true;
    }
}
