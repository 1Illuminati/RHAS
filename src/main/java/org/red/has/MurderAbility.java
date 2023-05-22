package org.red.has;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.red.has.item.murder.DoubleI;
import org.red.has.item.murder.ShadowI;
import org.red.library.item.ItemBuilder;

import java.util.Arrays;

public enum MurderAbility {
    DOUBLE(new ItemBuilder(Material.STONE_SWORD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
            .setCustomModelData(4).setDisplayName("§a§l나태").addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 6, AttributeModifier.Operation.ADD_NUMBER)
            .addAttribute(Attribute.GENERIC_ATTACK_SPEED, -1.5, AttributeModifier.Operation.ADD_NUMBER).setLore(Arrays.asList(
                    "§f 우클릭, 쿨타임 : 10초",
                    "§7   > 10초간 구속 I및 힘 II를 부여받습니다.",
                    "",
                    "§f 쉬프트 좌클릭, 쿨타임 : 20초",
                    "§7   > 주변의 모든 인간을 잠시동안 얼어붙게 만듭니다.",
                    "",
                    "§f 쉬프트 우클릭, 쿨타임 : 90초",
                    "§7   > 모든 인간은 15초간 구속 V가 됩니다.",
                    "",
                    "§f 패시브",
                    "§7   > 인간을 죽일시 감소하는 시간 감소량이 2배가 됩니다.",
                    "",
                    "§7 그의 나태함은 천천히 자기 자신을 갉아먹었다.")).setEventItem(new DoubleI()).build()),
    SHADOW(new ItemBuilder(Material.STONE_SWORD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
            .setCustomModelData(2).setDisplayName("§5§l분노").addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 8, AttributeModifier.Operation.ADD_NUMBER)
            .addAttribute(Attribute.GENERIC_ATTACK_SPEED, -2, AttributeModifier.Operation.ADD_NUMBER).setLore(Arrays.asList(
                    "§f 우클릭, 쿨타임 : 20초",
                    "§7   > 전방으로 도약한다.",
                    "",
                    "§f 쉬프트 좌클릭, 쿨타임 : 40초",
                    "§7   > 자기 주변 모든 인간에게 10의 데미지를 준다.",
                    "",
                    "§f 쉬프트 우클릭, 쿨타임 : 90초",
                    "§7   > 25초간 신속 II및 투명화를 얻는다.",
                    "",
                    "§f 패시브",
                    "§7   > 인간을 공격시 40% 확률로 5초간 어둠을 부여합니다.",
                    "",
                    "§7 그는 그의 모든 것을 잃었고 분노 하였다.")).setEventItem(new ShadowI()).build());
    public final ItemStack itemStack;
    MurderAbility(org.bukkit.inventory.ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
