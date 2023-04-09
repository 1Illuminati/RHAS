package org.red.has;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.red.library.event.TimerEndEvent;
import org.red.library.event.area.player.AreaPlayerMoveEvent;
import org.red.library.world.area.Area;
import org.red.library.world.timer.Timer;

public final class RHAS extends JavaPlugin implements Listener {
    private static RHAS plugin;

    public static RHAS getPlugin() {
        return RHAS.plugin;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
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
            game.victoryRunner();
        }
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Game game = Game.getGame();
        if (!game.isStarted())
            return;
    }
}
