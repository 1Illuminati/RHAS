package org.red.has.skill.runner;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class InvincibleS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 20;
    }

    @Override
    public String code() {
        return "invincible";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 10초간 무적이 됩니다!";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 4));
        return true;
    }
}
