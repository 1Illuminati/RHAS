package org.red.has;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.red.library.event.TimerEndEvent;
import org.red.library.event.area.player.AreaPlayerMoveEvent;
import org.red.library.item.ItemBuilder;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.timer.Timer;

import java.util.*;

public final class RHAS extends JavaPlugin implements Listener {
    private static RHAS plugin;

    public static RHAS getPlugin() {
        return RHAS.plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("game").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void areaMoveEvent(AreaPlayerMoveEvent event) {
        Game game = Game.getGame();
        if (!game.isStarted())
            return;

        PlayerMoveEvent playerMoveEvent = event.getEvent();
        if (event.getArea().equals(game.getArea()) && event.getAreaMoveAct() == AreaPlayerMoveEvent.AreaMoveAct.EXIT &&
                game.getSetting().containsJoinPlayer(playerMoveEvent.getPlayer().getUniqueId())) {
            playerMoveEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void timerEndEvent(TimerEndEvent event) {
        Timer timer = event.getTimer();
        Game game = Game.getGame();
        if (timer.equals(game.getTimer())) {
            game.victoryRunner("악마가 제한시간동안 모든 인간을 잡지 못했습니다.");
        }
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Game game = Game.getGame();
        if (!game.isStarted())
            return;

        Player runner = event.getEntity();
        Player killer = runner.getKiller();

        if (killer == null)
            return;

        if (game.getSurvivePlayer().contains(runner.getUniqueId()) && game.getMurderPlayer().contains(killer.getUniqueId())) {
            game.killPlayer(runner, killer);
        }

        if (!game.getSetting().isDeathMessage()) {
            event.setDeathMessage("");
        }
    }

    @EventHandler
    public void foodLevelChangeEvent(FoodLevelChangeEvent event) {
        HumanEntity humanEntity = event.getEntity();
        Game game = Game.getGame();

        if (!game.isStarted())
            return;
        GameSetting setting = game.getSetting();

        if (setting.isHunger())
            return;

        if (setting.containsJoinPlayer(humanEntity.getUniqueId())) {
            event.setCancelled(true);
            humanEntity.setFoodLevel(20);
        }
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        Game game = Game.getGame();
        UUID uuid = event.getPlayer().getUniqueId();

        if (!game.isStarted())
            return;

        List<UUID> joinPlayers = game.getSetting().getJoinPlayers();
        if (joinPlayers.contains(uuid)) {
            joinPlayers.remove(uuid);
            game.getDeadPlayer().remove(uuid);
            game.getSurvivePlayer().remove(uuid);
            game.getMurderPlayer().remove(uuid);

            if (game.getSurvivePlayer().size() == 0 || game.getMurderPlayer().size() == 0) {
                game.clear();
            }
        }
    }
    private static final ItemStack LOC_HELPER = new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("§fLocation Set Up Helper").setLore(Collections.singletonList("§f이 아이템은 게임의 플레이를 지원하기 위해 제작되었습니다.")).build();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§4Please input a subcommand");
            return true;
        }

        Game game = Game.getGame();
        Player player = (Player) sender;
        GameSetting gameSetting = game.getSetting();
        UUID uuid = player.getUniqueId();
        switch (args[0]) {
            case "join" -> {
                if (gameSetting.containsJoinPlayer(uuid)) {
                    player.sendMessage("§cYou are already join the game");
                } else {
                    gameSetting.addJoinPlayer(uuid);
                    game.sendMessage("§a" + player.getName() + " join the game", gameSetting.getJoinPlayers());
                }
            }
            case "unjoin" -> {
                if (!gameSetting.containsJoinPlayer(uuid)) {
                    player.sendMessage("§cYou are not join the game");
                } else {
                    gameSetting.removeJoinPlayer(uuid);
                    game.sendMessage("§a" + player.getName() + " unJoin the game", gameSetting.getJoinPlayers());
                }
            }
            case "loc" -> player.getInventory().addItem(GameItems.LOC_HELPER);
            case "start" -> {
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }
                if (game.isStarted()) {
                    player.sendMessage("§cThe game is already started");
                } else {
                    game.run();
                }
            }
            case "timer" -> {
                for (Iterator<KeyedBossBar> it = Bukkit.getBossBars(); it.hasNext(); ) {
                    BossBar bossBar = it.next();
                    bossBar.removeAll();
                }
            }
            case "stop" -> {
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }
                if (!game.isStarted()) {
                    player.sendMessage("§cThe game is not started");
                } else {
                    game.clear();
                }
            }
            case "time" -> gameSetting.setTime(Integer.parseInt(args[1]));
            case "murderNum" -> gameSetting.setMurderNum(Integer.parseInt(args[1]));
            case "rw" -> {
                if (!player.isOp())
                    return false;
                for (RunnerAbility runnerAbility : RunnerAbility.values())
                    player.getInventory().addItem(runnerAbility.itemStack);
            }
            case "mw" -> {
                if (!player.isOp())
                    return false;
                for (MurderAbility murderAbility : MurderAbility.values())
                    player.getInventory().addItem(murderAbility.itemStack);
            }
            case "test" -> {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(0, 127, 255), 1.0F);
                player.spawnParticle(Particle.REDSTONE, player.getLocation(), 50, dustOptions);
            }
            case "list" -> {
                for (UUID list : gameSetting.getJoinPlayers()) {
                    Player joiner = Bukkit.getPlayer(list);
                    if (joiner != null) player.sendMessage(joiner.getName());
                }
            }
            default -> sender.sendMessage("§a존재하지 않는 명령어 입니다.");
        }
        return true;
    }
}
