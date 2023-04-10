package org.red.has.skill.runner;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class StimPackS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 30;
    }

    @Override
    public String code() {
        return "stim_pack";
    }

    @Override
    public String skillMessage() {
        return "스팀팩 사용! 10초간 신속 II가 부여됩니다!";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
        return true;
    }
}
