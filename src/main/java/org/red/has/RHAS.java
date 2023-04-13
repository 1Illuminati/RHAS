package org.red.has;

import org.bukkit.Bukkit;
import org.bukkit.Color;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.red.library.event.TimerEndEvent;
import org.red.library.event.area.player.AreaPlayerMoveEvent;
import org.red.library.world.timer.Timer;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
                game.getJoinPlayer().contains(playerMoveEvent.getPlayer().getUniqueId())) {
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
            runner.spigot().respawn();
        } else if (game.getDeadPlayer().contains(runner.getUniqueId())) {
            game.victoryRunner("악마가 사망하였습니다!");
            runner.spigot().respawn();
        }
    }

    @EventHandler
    public void foodLevelChangeEvent(FoodLevelChangeEvent event) {
        HumanEntity humanEntity = event.getEntity();
        Game game = Game.getGame();

        if (!game.isStarted())
            return;

        if (game.getJoinPlayer().contains(humanEntity.getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§4Please input a subcommand");
            return true;
        }

        Game game = Game.getGame();
        Player player = (Player) sender;
        List<UUID> joinPlayer = game.getJoinPlayer();
        UUID uuid = player.getUniqueId();
        switch (args[0]) {
            case "join":
                if (joinPlayer.contains(uuid)) {
                    player.sendMessage("§cYou are already join the game");
                } else {
                    joinPlayer.add(uuid);
                    game.sendMessage("§a" + player.getName() + " join the game", joinPlayer);
                }
            break;
            case "unjoin":
                if (!joinPlayer.contains(uuid)) {
                    player.sendMessage("§cYou are not join the game");
                } else {
                    joinPlayer.remove(uuid);
                    game.sendMessage("§a" + player.getName() + " unJoin the game", joinPlayer);
                }
            break;
            case "loc":
                player.getInventory().addItem(GameItems.LOC_HELPER);
            break;
            case "start":
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }

                if (game.isStarted()) {
                    player.sendMessage("§cThe game is already started");
                } else {
                    game.run();
                }
            break;
            case "timer":
                for (Iterator<KeyedBossBar> it = Bukkit.getBossBars(); it.hasNext(); ) {
                    BossBar bossBar = it.next();
                    bossBar.removeAll();
                }
            break;
            case "stop":
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }

                if (!game.isStarted()) {
                    player.sendMessage("§cThe game is not started");
                } else {
                    game.clear();
                }
            break;
            case "time":
                game.setTime(Integer.parseInt(args[1]));
            break;
            case "murderNum":
                game.setMurderNum(Integer.parseInt(args[1]));
            break;
            case "rw":
                if (!player.isOp())
                    return false;
                player.getInventory().addItem(GameItems.RUNNER_WEAPON);
            break;
            case "mw":
                if (!player.isOp())
                    return false;
                player.getInventory().addItem(GameItems.MURDER_WEAPON);
            break;
            case "test":
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(0, 127, 255), 1.0F);
                player.spawnParticle(Particle.REDSTONE, player.getLocation(), 50, dustOptions);
            break;
            default:
                sender.sendMessage("§a존재하지 않는 명령어 입니다.");
        }
        return true;
    }
}
