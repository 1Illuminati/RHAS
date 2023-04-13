package org.red.has.skill.murder;

import org.bukkit.entity.LivingEntity;
import org.red.has.Util;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class ShadowSL extends AbstractSkill {
    @Override
    public int coolTime() {
        return 40;
    }

    @Override
    public String code() {
        return "shadow_sl";
    }

    @Override
    public String skillMessage() {
        return "분노 쉬프트 좌클릭 사용";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        for (LivingEntity livingEntity : Util.getTarget(player.getLocation(), 3)) {
            if (!livingEntity.equals(player.getPlayer()))
                livingEntity.damage(10, player.getPlayer());
        }

        return true;
    }
}
