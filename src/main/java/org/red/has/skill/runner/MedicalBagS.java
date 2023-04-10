package org.red.has.skill.runner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.red.has.Game;
import org.red.has.skill.AbstractSkill;
import org.red.library.entity.player.NewPlayer;
import org.red.library.invetory.CustomInventory;
import org.red.library.item.ItemBuilder;

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
        Game game = Game.getGame();

        if (!game.isStarted()) {
            player.sendMessage(ChatColor.RED + "게임이 시작되지 않았습니다.");
            return false;
        }

        player.openInventory(new MedicalBagInv(game));
        return true;
    }

    private static class MedicalBagInv extends CustomInventory {

        public MedicalBagInv(Game game) {
            super("살리고 싶은 사람을 누르세요!", 27);

            int i = 0;
            for (UUID uuid : game.getDeadPlayer()) {
                Player player = Bukkit.getPlayer(uuid);
                ItemStack itemStack = new ItemBuilder(Material.PLAYER_HEAD).setDisplayName(ChatColor.WHITE + player.getName()).build();
                SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
                skullMeta.setOwningPlayer(player);
                itemStack.setItemMeta(skullMeta);
                this.setItem(i, itemStack);
                this.setButton(i++, event -> {
                    game.revivePlayer(player);
                    event.getWhoClicked().closeInventory();
                });
            }
        }
    }
}
