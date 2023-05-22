package org.red.has;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.red.has.item.LocHelper;
import org.red.has.item.murder.DoubleI;
import org.red.has.item.murder.ShadowI;
import org.red.has.item.runner.*;
import org.red.library.item.ItemBuilder;
import org.red.library.item.event.EventItemManager;

import java.util.Arrays;
import java.util.Collections;

public class GameItems {
    public static final ItemStack RED_KILLER;
    public static final ItemStack SHADOW;
    public static final ItemStack GAMBLER;
    public static final ItemStack DOUBLE;

    public static final ItemStack MEDICAL_BAG;
    public static final ItemStack ARTIFICIAL_EYE_OF_GOD;
    public static final ItemStack STIM_PACK;
    public static final ItemStack INVISIBLE;
    public static final ItemStack INVINCIBLE;
    public static final ItemStack NO_ABILITY;
    public static final ItemStack BET;
    public static final ItemStack BLINDFOLD;
    public static final ItemStack LOCATION_CHANGE;
    public static final ItemStack SLEEP_GUN;
    public static final ItemStack LEAP;
    public static final ItemStack HEALTH_POTION;
    public static final ItemStack POCKET_WATCH;
    public static final ItemStack TRAP;

    public static final ItemStack LOC_HELPER;

    public static final ItemStack[] MURDER_WEAPON;
    public static final ItemStack[] RUNNER_WEAPON;

    static {
        RED_KILLER = new ItemBuilder(Material.STONE_SWORD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
                .setCustomModelData(1).setDisplayName("§4§l교만").addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 4, AttributeModifier.Operation.ADD_NUMBER)
                .addAttribute(Attribute.GENERIC_ATTACK_SPEED, -1, AttributeModifier.Operation.ADD_NUMBER).setLore(Arrays.asList(
                        "§f 우클릭, 쿨타임 : 7초",
                        "§7   > 전방으로 데미지 5의 칼을 날립니다.",
                        "",
                        "§f 쉬프트 좌클릭, 쿨타임 : 30초",
                        "§7   > ",
                        "",
                        "§f 쉬프트 우클릭, 쿨타임 : 90초",
                        "§7   > 20초간 우클릭의 쿨타임이 0초가 됩니다. 또한 무적시간을 무시합니다,",
                        "",
                        "§f 패시브",
                        "§7   > 인간에게 우클릭 적중시 20% 확률로 3초간 독을 부여합니다.",
                        "",
                        "§7 하얀 가면속엔 자기 자신에 미쳐버린 살인마가 숨어있었다.")).build();

        SHADOW = new ItemBuilder(Material.STONE_SWORD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
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
                        "§7 그는 그의 모든 것을 잃었고 분노 하였다.")).build();
        EventItemManager.setItemInEvent(SHADOW, new ShadowI());

        GAMBLER = new ItemBuilder(Material.STONE_SWORD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
                .setCustomModelData(3).setDisplayName("§e§l탐욕").addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 15, AttributeModifier.Operation.ADD_NUMBER)
                .addAttribute(Attribute.GENERIC_ATTACK_SPEED, -3, AttributeModifier.Operation.ADD_NUMBER).setLore(Arrays.asList(
                        "§f 우클릭, 쿨타임 : 20초",
                        "§7  > 자기 주변의 인간의 체력을 30% 흡수한다.",
                        "",
                        "§f 쉬프트 좌클릭, 쿨타임 : 20초",
                        "§7  > 최대 체력의 25%를 소모하고 7초간 신속 III를 부여받습니다.",
                        "",
                        "§f 쉬프트 우클릭, 쿨타임 : 90초",
                        "§7  > 최대 체력의 70%를 소모하고 자기 주변의 모든 인간을 흡수한다.",
                        "",
                        "§f 패시브",
                        "§7   > 매 초 체력이 0.5%씩 감소합니다. 인간을 죽일시 체력을 50% 회복합니다.",
                        "§7     만약 최대 체력 이상의 hp를 회복하게 될 경우 그 수치 만큼 추가 체력을 획득합니다.",
                        "",
                        "§7 그의 멈출줄 모르는 탐욕은 결국 자기 자신을 잡아먹었다.")).build();

        DOUBLE = new ItemBuilder(Material.STONE_SWORD).setUnbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
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
                        "§7 그의 나태함은 천천히 자기 자신을 갉아먹었다.")).build();
        EventItemManager.setItemInEvent(DOUBLE, new DoubleI());

        MURDER_WEAPON = new ItemStack[]{SHADOW, DOUBLE};

        MEDICAL_BAG = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(1).setDisplayName("§f구급상자").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 240초",
                "§7   > 죽은 도망자중 한명을 되살립니다.",
                "",
                "§7 죽은자는 말이 많다")).build();
        EventItemManager.setItemInEvent(MEDICAL_BAG, new MedicalBagI());

        ARTIFICIAL_EYE_OF_GOD = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(2).setDisplayName("§f천년눈").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 25초",
                "§7   > 15초간 악마에게 발광을 부여합니다.",
                "",
                "§7 마인드 스캔!")).build();
        EventItemManager.setItemInEvent(ARTIFICIAL_EYE_OF_GOD, new ArtificialEyeOfGodI());

        STIM_PACK = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(3).setDisplayName("§f스팀팩").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 30초",
                "§7   > 10초간 신속 II를 받습니다.",
                "",
                "§7 우리 마린 영양 간식")).build();
        EventItemManager.setItemInEvent(STIM_PACK, new StimPackI());

        INVISIBLE = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(4).setDisplayName("§f투명화").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 50초",
                "§7   > 10초간 투명화를 받습니다.",
                "",
                "§7 내가 사라져볼게 얍!")).build();
        EventItemManager.setItemInEvent(INVISIBLE, new InvisibleI());

        INVINCIBLE = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(5).setDisplayName("§f방패").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 20초",
                "§7   > 10초간 저항 V를 받습니다.",
                "",
                "§7 안 아파~ 하나도 안 아파~")).build();
        EventItemManager.setItemInEvent(INVINCIBLE, new InvincibleI());

        NO_ABILITY = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(6).setDisplayName("§f?").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : ㅋ초",
                "§7   > 놀랍게도 아무 일도 일어나지 않습니다.",
                "",
                "§7 ㅋ")).build();
        EventItemManager.setItemInEvent(NO_ABILITY, new NoAbilityI());

        BET = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(7).setDisplayName("§f방망이").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 20초",
                "§7  > 3초간 아이템에 밀치기 X 인챈트가 부여됩니다.",
                "",
                "§7 홈런~! 볼")).build();
        EventItemManager.setItemInEvent(BET, new BetI());

        BLINDFOLD = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(8).setDisplayName("§f독침").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 25초",
                "§7   > 전방으로 독침을 발사합니다. 적중시 10초간 실명을 부여합니다.",
                "",
                "§7 ^오^")).build();
        EventItemManager.setItemInEvent(BLINDFOLD, new BlindFoldI());

        LOCATION_CHANGE = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(9).setDisplayName("§f위치 바꾸기").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 90초",
                "§7   > 위치를 바꿔보죠?",
                "",
                "§7 체인지!")).build();
        EventItemManager.setItemInEvent(LOCATION_CHANGE, new LocationChangeI());

        SLEEP_GUN = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(10).setDisplayName("§f수면총").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 20초",
                "§7  > 전방으로 총알을 발사합니다.",
                "§7    피격당한 엔티티에게 3초간 구속 5 및 실명을 부여합니다.",
                "",
                "§7 더이상 아이디어가 안떠오른다.")).build();
        EventItemManager.setItemInEvent(SLEEP_GUN, new SleepGunI());

        POCKET_WATCH = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(11).setDisplayName("§f회중시계").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 80초",
                "§7  > 남은 시간이 10% 감소합니다.",
                "",
                "§7 줄어드는 시간")).build();
        EventItemManager.setItemInEvent(POCKET_WATCH, new PocketWatchI());

        LEAP = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(12).setDisplayName("§f도약").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 20초",
                "§7  > 도약합니다.",
                "",
                "§7 도도도도도오오오오오오오오오야야야야야야약")).build();
        EventItemManager.setItemInEvent(LEAP, new LeapI());

        HEALTH_POTION = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(13).setDisplayName("§f회복포션").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 25초",
                "§7  > 체력을 전부 회복합니다.",
                "",
                "§7 은근 잘 버팀")).build();
        EventItemManager.setItemInEvent(HEALTH_POTION, new HealthPotionI());

        TRAP = new ItemBuilder(Material.IRON_INGOT).setCustomModelData(14).setDisplayName("§f함정").setLore(Arrays.asList(
                "§f 우클릭, 쿨타임 : 30초",
                "§7  > 현재 위치에 함정을 설치합니다. (최대 10개 까지 동시 설치)",
                "§7    함정을 밟은 악마는 10초간 구속 5에 걸립니다.",
                "",
                "§7 You just active my trap card")).build();


        RUNNER_WEAPON = new ItemStack[]{MEDICAL_BAG, ARTIFICIAL_EYE_OF_GOD, STIM_PACK, INVISIBLE,
                INVINCIBLE, NO_ABILITY, BET, BLINDFOLD, LOCATION_CHANGE, SLEEP_GUN, POCKET_WATCH, HEALTH_POTION, LEAP};

        LOC_HELPER = new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("§fLocation Set Up Helper").setLore(Collections.singletonList("§f이 아이템은 게임의 플레이를 지원하기 위해 제작되었습니다.")).build();
        EventItemManager.setItemInEvent(LOC_HELPER, new LocHelper());
    }
}
