package org.red.has.skill.runner;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.red.has.RHAS;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;

public class BetS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 20;
    }

    @Override
    public String code() {
        return "bet";
    }

    @Override
    public String skillMessage() {
        return "스킬 사용! 3초간 아이템에 밀치기 X가 부여됩니다.";
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        ItemStack itemStack = player.getItemInHand();
        itemStack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);

        Bukkit.getScheduler().runTaskLater(RHAS.getPlugin(), () -> {
            itemStack.removeEnchantment(Enchantment.KNOCKBACK);
        }, 60);
        return true;
    }
}
