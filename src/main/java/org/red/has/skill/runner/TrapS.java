package org.red.has.skill.runner;

import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class TrapS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 30;
    }

    @Override
    public String code() {
        return "trap";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 현재 위치에 함정이 설치되었습니다!";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        return true;
    }
}
