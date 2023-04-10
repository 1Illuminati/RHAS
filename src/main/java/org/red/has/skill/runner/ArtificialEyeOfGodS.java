package org.red.has.skill.runner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.Game;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

import java.util.UUID;

public class ArtificialEyeOfGodS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 25;
    }

    @Override
    public String code() {
        return "artificial_eye_of_god";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 악마가 발광 상태가 됩니다!";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Game game = Game.getGame();

        for (UUID uuid : game.getMurderPlayer()) {
            Player murder = Bukkit.getPlayer(uuid);
            murder.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 0));
        }

        return true;
    }
}
