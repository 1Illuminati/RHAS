package org.red.has.skill;

import org.bukkit.ChatColor;
import org.red.has.Game;
import org.red.library.entity.player.NewPlayer;
import org.red.library.util.map.CoolTime;

public abstract class AbstractSkill {
    public abstract int coolTime();
    public abstract String code();
    public abstract String skillMessage();

    protected abstract boolean onSkill(NewPlayer player);

    public void skill(NewPlayer player) {
        CoolTime coolTime = player.getCoolTime();
        Game game = Game.getGame();

        if (!game.isStarted()) {
            player.sendMessage(ChatColor.RED + "게임이 시작되지 않았습니다.");
            return;
        }

        if (game.getDeadPlayer().contains(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "죽은 사람은 스킬을 사용할 수 없습니다.");
            return;
        }

        if (!coolTime.checkCoolTime(code())) {
            player.sendMessage("쿨타임 : " + coolTime.getLessCoolTime(code()));
            return;
        }

        if (onSkill(player)) {
            if (skillMessage() != null)
                player.sendMessage(skillMessage());
            coolTime.setCoolTime(code(), coolTime(), CoolTime.TimeType.SECOND);
        }

    }
}
