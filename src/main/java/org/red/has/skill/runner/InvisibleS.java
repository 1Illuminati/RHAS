package org.red.has.skill.runner;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class InvisibleS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 50;
    }

    @Override
    public String code() {
        return "invisible";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 20초간 투명해집니다!";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 0));
        return true;
    }
}
