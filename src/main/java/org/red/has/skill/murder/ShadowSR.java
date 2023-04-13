package org.red.has.skill.murder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.has.Game;
import org.red.has.RHAS;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.util.async.Scheduler;

import java.util.UUID;

public class ShadowSR extends AbstractSkill {
    @Override
    public int coolTime() {
        return 90;
    }

    @Override
    public String code() {
        return "shadow_sr";
    }

    @Override
    public String skillMessage() {
        return "분노 쉬프트 우클릭 사용";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Game game = Game.getGame();
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
        for (UUID uuid : game.getJoinPlayer()) {
            Player runner = Bukkit.getPlayer(uuid);
            runner.hidePlayer(RHAS.getPlugin(), player.getPlayer());
        }
        Scheduler.delayScheduler(new Scheduler.RunnableEx() {
            @Override
            public void function() {
                for (UUID uuid : game.getJoinPlayer()) {
                    Player runner = Bukkit.getPlayer(uuid);
                    runner.showPlayer(RHAS.getPlugin(), player.getPlayer());
                }
            }
        }, 500);
        return false;
    }
}
