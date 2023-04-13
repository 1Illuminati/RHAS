package org.red.has.skill.murder;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class DoubleR extends AbstractSkill {
    @Override
    public int coolTime() {
        return 10;
    }

    @Override
    public String code() {
        return "double_r";
    }

    @Override
    public String skillMessage() {
        return "나태 우클릭 사용";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
        return true;
    }
}
