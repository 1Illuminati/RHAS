package org.red.has.skill.murder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.Game;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

import java.util.UUID;

public class DoubleSR extends AbstractSkill {
    @Override
    public int coolTime() {
        return 90;
    }

    @Override
    public String code() {
        return "double_sr";
    }

    @Override
    public String skillMessage() {
        return "나태 쉬프트 우클릭 사용";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Game game = Game.getGame();

        for (UUID uuid : game.getSurvivePlayer()) {
            Player runner = Bukkit.getPlayer(uuid);
            runner.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 4));
        }

        return true;
    }
}
