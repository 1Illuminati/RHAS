package org.red.has.skill.runner;

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
        Game game = Game.getGame();
        for (UUID uuid : game.getSetting().getJoinPlayers()) {
            Player runner = Bukkit.getPlayer(uuid);
            runner.hidePlayer(RHAS.getPlugin(), player.getPlayer());
        }
        Scheduler.delayScheduler(new Scheduler.RunnableEx() {
            @Override
            public void function() {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.showPlayer(RHAS.getPlugin(), player.getPlayer());
                }
            }
        }, 200);
        return true;
    }
}
