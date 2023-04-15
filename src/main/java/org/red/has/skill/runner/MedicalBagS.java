package org.red.has.skill.runner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.red.has.Game;
import org.red.has.Util;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.invetory.CustomInventory;
import org.red.library.item.ItemBuilder;
import org.red.library.util.map.CoolTime;

import java.util.UUID;

public class MedicalBagS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 240;
    }

    @Override
    public String code() {
        return "medical_bag";
    }

    @Override
    public String skillMessage() {
        return null;
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.openInventory(new MedicalBagInv(Game.getGame()).getInventory());
        return false;
    }

    private static class MedicalBagInv extends CustomInventory {

        public MedicalBagInv(Game game) {
            super(27, "살리고 싶은 사람을 누르세요!");

            int i = 0;
            for (UUID uuid : game.getDeadPlayer()) {
                Player player = Bukkit.getPlayer(uuid);
                this.setItem(i, new ItemBuilder(Util.getPlayerSkull(player)).setDisplayName(ChatColor.WHITE + player.getName()).build());
                this.setButton(i++, event -> {
                    event.setCancelled(true);
                    HumanEntity humanEntity = event.getWhoClicked();
                    game.revivePlayer(player);
                    humanEntity.closeInventory();
                    NewPlayer.getNewPlayer((Player) humanEntity).getCoolTime().setCoolTime("medical_bag", 240, CoolTime.TimeType.SECOND);
                    humanEntity.sendMessage(ChatColor.GREEN + "성공적으로 살려냈습니다!");
                    player.teleport(humanEntity.getLocation());
                    player.sendMessage(ChatColor.GREEN + "누군가가 당신을 치료에 성공하였습니다!");
                });
            }
        }
    }
}
