package org.red.has.skill.runner;

import org.red.has.Game;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.world.timer.Timer;

public class PocketWatchS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 80;
    }

    @Override
    public String code() {
        return "pocket_watch";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 시간이 감소합니다.";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Game game = Game.getGame();
        Timer timer = game.getTimer();
        timer.addTime(timer.getMaxTime() * 0.1);
        return true;
    }
}
