package org.red.has.skill.runner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

public class LocationChangeS extends AbstractSkill {
    @Override
    public int coolTime() {
        return 50;
    }

    @Override
    public String code() {
        return "location_change";
    }

    @Override
    public String skillMessage() {
        return null;
    }

    @Override
    protected boolean onSkill(NewPlayer player) {
        player.openInventory(new LocationChangeInv(Game.getGame()).getInventory());
        return false;
    }

    private static class LocationChangeInv extends CustomInventory {

        public LocationChangeInv(Game game) {
            super(27, "누구랑 위치를 바꿔볼까");

            int i = 0;
            for (UUID uuid : game.getJoinPlayer()) {

                if (game.getDeadPlayer().contains(uuid))
                    continue;

                Player player = Bukkit.getPlayer(uuid);
                this.setItem(i, new ItemBuilder(Util.getPlayerSkull(player)).setDisplayName(ChatColor.WHITE + player.getName()).build());
                this.setButton(i++, event -> {
                    event.setCancelled(true);
                    HumanEntity humanEntity = event.getWhoClicked();
                    humanEntity.closeInventory();
                    Location temp = humanEntity.getLocation();
                    humanEntity.teleport(player.getLocation());
                    player.teleport(temp);
                    player.sendMessage(ChatColor.RED + "누군가와 위치가 바꼇습니다!");
                    humanEntity.sendMessage(ChatColor.GREEN + "위치 변경 성공!");
                    NewPlayer.getNewPlayer(player).getCoolTime().setCoolTime("location_change", 50, CoolTime.TimeType.SECOND);
                });
            }
        }
    }
}
