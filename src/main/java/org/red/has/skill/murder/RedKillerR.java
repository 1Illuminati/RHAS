package org.red.has.skill.murder;

import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class RedKillerR extends AbstractSkill {
    @Override
    public int coolTime() {
        return 0;
    }

    @Override
    public String code() {
        return null;
    }

    @Override
    public String skillMessage() {
        return null;
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        return false;
    }
}
