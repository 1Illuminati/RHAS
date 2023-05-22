package org.red.has;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.red.has.item.runner.*;
import org.red.library.item.ItemBuilder;

import java.util.Arrays;

public enum RunnerAbility {
    HEALTH_POTION(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(13).setDisplayName("§f회복포션").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 25초",
            "§7  > 체력을 전부 회복합니다.",
            "",
            "§7 은근 잘 버팀")).setEventItem(new HealthPotionI()).build()),
    LEAP(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(12).setDisplayName("§f도약").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 20초",
            "§7  > 도약합니다.",
            "",
            "§7 도도도도도오오오오오오오오오야야야야야야약")).setEventItem(new LeapI()).build()),
    POCKET_WATCH(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(11).setDisplayName("§f회중시계").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 80초",
            "§7  > 남은 시간이 10% 감소합니다.",
            "",
            "§7 줄어드는 시간")).setEventItem(new PocketWatchI()).build()),
    SLEEP_GUN(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(11).setDisplayName("§f회중시계").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 80초",
            "§7  > 남은 시간이 10% 감소합니다.",
            "",
            "§7 줄어드는 시간")).setEventItem(new SleepGunI()).build()),
    LOCATION_CHANGE(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(9).setDisplayName("§f위치 바꾸기").setLore(Arrays.asList(
             "§f 우클릭, 쿨타임 : 90초",
             "§7   > 위치를 바꿔보죠?",
             "",
             "§7 체인지!")).setEventItem(new LocationChangeI()).build()),

    BLINDFOLD(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(8).setDisplayName("§f독침").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 25초",
            "§7   > 전방으로 독침을 발사합니다. 적중시 10초간 실명을 부여합니다.",
            "",
            "§7 ^오^")).setEventItem(new BlindFoldI()).build()),

    BET(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(7).setDisplayName("§f방망이").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 20초",
            "§7  > 3초간 아이템에 밀치기 X 인챈트가 부여됩니다.",
            "",
            "§7 홈런~! 볼")).setEventItem(new BetI()).build()),
    NO_ABILITY(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(6).setDisplayName("§f?").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : ㅋ초",
            "§7   > 놀랍게도 아무 일도 일어나지 않습니다.",
            "",
            "§7 ㅋ")).setEventItem(new NoAbilityI()).build()),
    INVINCIBLE(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(5).setDisplayName("§f방패").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 20초",
            "§7   > 10초간 저항 V를 받습니다.",
            "",
            "§7 안 아파~ 하나도 안 아파~")).setEventItem(new InvincibleI()).build()),
    INVISIBLE(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(4).setDisplayName("§f투명화").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 50초",
            "§7   > 10초간 투명화를 받습니다.",
            "",
            "§7 내가 사라져볼게 얍!")).setEventItem(new InvisibleI()).build()),
    STIM_PACK(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(3).setDisplayName("§f스팀팩").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 30초",
            "§7   > 10초간 신속 II를 받습니다.",
            "",
            "§7 우리 마린 영양 간식")).setEventItem(new StimPackI()).build()),
    ARTIFICIAL_EYE_OF_GOD(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(2).setDisplayName("§f천년눈").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 25초",
            "§7   > 15초간 악마에게 발광을 부여합니다.",
            "",
            "§7 마인드 스캔!")).setEventItem(new ArtificialEyeOfGodI()).build()),
    MEDICAL_BAG(new ItemBuilder(Material.IRON_INGOT).setCustomModelData(1).setDisplayName("§f구급상자").setLore(Arrays.asList(
            "§f 우클릭, 쿨타임 : 240초",
            "§7   > 죽은 도망자중 한명을 되살립니다.",
            "",
            "§7 죽은자는 말이 많다")).setEventItem(new MedicalBagI()).build());
    public final ItemStack itemStack;
    RunnerAbility(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
