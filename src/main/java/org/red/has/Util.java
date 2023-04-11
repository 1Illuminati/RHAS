package org.red.has;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static ItemStack getPlayerSkull(Player player) {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (org.bukkit.inventory.meta.SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(player);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public static List<Vector> line(Vector dir, double range, double frequency) {
        List<Vector> result = new ArrayList<>();

        for(double i = 0; i < range; i+=frequency) {
            Vector vec = dir.clone().multiply(i);
            result.add(vec);
        }

        return result;
    }

    public static List<LivingEntity> getTarget(Location loc, double distance) {
        List<Entity> entities = loc.getWorld().getEntities();
        List<LivingEntity> returnValues = new ArrayList<>();
        for(Entity entity : entities) {
            if(entity instanceof LivingEntity livingEntity) {
                double entityDistance = entity.getLocation().distance(loc);
                if(entityDistance <= distance && entityDistance >= 0) {
                    if(!entity.isDead())
                        returnValues.add(livingEntity);
                }
            }
        }
        return returnValues;
    }
}
