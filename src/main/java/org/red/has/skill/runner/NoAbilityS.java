package org.red.has.skill.runner;

import org.red.has.Game;
import org.red.has.GameItems;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class NoAbilityS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 9999;
    }

    @Override
    public String code() {
        return "noAbility";
    }

    @Override
    public String skillMessage() {
        return null;
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        Game game = Game.getGame();

        if (game.getJoinPlayer().size() == 1 && game.getJoinPlayer().contains(player.getUniqueId())) {
            game.sendTitle("§e§l[ §fㅋㅋㅋㅋ §e§l]", "§7ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", game.getJoinPlayer());
            player.getInventory().addItem(GameItems.RUNNER_WEAPON);
            return true;
        } else {
            player.sendMessage("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            return false;
        }
    }
}
