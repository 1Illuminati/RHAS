package org.red.has.skill.runner;

import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class HealthPotionS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 25;
    }

    @Override
    public String code() {
        return "health_potion";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 체력이 전부 회복됩니다.";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.setHealth(player.getMaxHealth());
        return true;
    }
}
