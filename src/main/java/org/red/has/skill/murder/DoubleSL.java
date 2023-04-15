package org.red.has.skill.murder;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.red.has.Game;
import org.red.has.Util;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class DoubleSL extends AbstractSkill {
    @Override
    public int coolTime() {
        return 20;
    }

    @Override
    public String code() {
        return "double_sr";
    }

    @Override
    public String skillMessage() {
        return "나태 쉬프트 좌클릭 사용";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Game game = Game.getGame();
        for (LivingEntity livingEntity : Util.getTarget(player.getLocation(), 5)) {
            if (livingEntity instanceof Player pl && game.getSurvivePlayer().contains(pl.getUniqueId())) {
                pl.setFreezeTicks(200);
            }
        }

        return true;
    }
}
