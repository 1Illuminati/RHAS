package org.red.has.skill;

import org.red.library.entity.player.NewPlayer;
import org.red.library.util.map.CoolTime;

public abstract class AbstractSkill {
    public abstract int coolTime();
    public abstract String code();
    public abstract String skillMessage();

    protected abstract boolean onSkill(NewPlayer player);

    public void skill(NewPlayer player) {
        CoolTime coolTime = player.getCoolTime();

        if (!coolTime.checkCoolTime(code())) {
            player.sendMessage("쿨타임 : " + coolTime.getCoolTime(code()));
            return;
        }

        if (onSkill(player)) {
            if (skillMessage() != null)
                player.sendMessage(skillMessage());
            coolTime.setCoolTime(code(), coolTime(), CoolTime.TimeType.SECOND);
        }

    }
}
