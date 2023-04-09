package org.red.has;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.red.library.util.async.Scheduler;
import org.red.library.world.WorldData;
import org.red.library.world.area.Area;
import org.red.library.world.timer.Timer;

import java.util.*;

public class Game implements Runnable {
    private static Game game;

    public static Game getGame() {
        if (game == null) {
            game = new Game(UUID.randomUUID());
        }

        return game;
    }

    public static void clearGame() {
        game.clear();
    }
    private final UUID gameCode;
    private final List<UUID> joinPlayer = new ArrayList<>();
    private final List<UUID> survivePlayer = new ArrayList<>();
    private final List<UUID> deadPlayer = new ArrayList<>();
    private final List<UUID> specterPlayer = new ArrayList<>();
    private final List<UUID> murderPlayer = new ArrayList<>();
    private boolean isStarted = false;
    private int time = 300;
    private int murderNum = 1;
    private Location spawn;
    private Location start;
    private Location end;
    private GameArea area;
    private Timer timer;

    private Game(UUID uuid) {
        this.gameCode = uuid;
    }

    public List<UUID> getJoinPlayer() {
        return joinPlayer;
    }

    public List<UUID> getSurvivePlayer() {
        return survivePlayer;
    }

    public List<UUID> getDeadPlayer() {
        return deadPlayer;
    }

    public List<UUID> getSpecterPlayer() {
        return specterPlayer;
    }

    public UUID getGameCode() {
        return gameCode;
    }

    public List<UUID> getMurderPlayer() {
        return murderPlayer;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getStart() {
        return start;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public Location getEnd() {
        return end;
    }

    public void setMurderNum(int num) {
        this.murderNum = num;
    }

    public int getMurderNum() {
        return murderNum;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void sendMessage(String message, List<UUID> uuids) {
        uuids.forEach(uuid -> {
            Player player;
            if ((player = Bukkit.getPlayer(uuid)) != null)
                player.sendMessage(message);
        });
    }

    public void sendTitle(String title, String subTitle, List<UUID> uuids) {
        uuids.forEach(uuid -> {
            Player player;
            if ((player = Bukkit.getPlayer(uuid)) != null)
                player.sendTitle(title, subTitle, 10, 70, 20);
        });
    }

    public void sendActionBar(String message, List<UUID> uuids) {
        uuids.forEach(uuid -> {
            Player player;
            if ((player = Bukkit.getPlayer(uuid)) != null)
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
        });
    }

    public void clear() {
        WorldData worldData = WorldData.getWorldData(this.spawn.getWorld());
        worldData.removeArea(area);
        this.joinPlayer.clear();
        this.specterPlayer.clear();;
        this.deadPlayer.clear();
        this.survivePlayer.clear();
        this.isStarted = false;
    }

    public Area getArea() {
        return area;
    }

    public void killPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        if (!this.isStarted()) {
            throw new IllegalArgumentException("Game is not start");
        }

        if (this.deadPlayer.contains(uuid)) {
            throw new IllegalArgumentException(uuid + " is already dead");
        }

        if (this.specterPlayer.contains(uuid)) {
            throw new IllegalArgumentException(uuid + " is specter");
        }

        player.setGameMode(GameMode.SPECTATOR);
        this.survivePlayer.remove(uuid);
        this.deadPlayer.add(uuid);

        if (survivePlayer.size() == 0) {
            //살인마 승리 코드
        }
    }

    public void revivePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        if (!this.isStarted()) {
            throw new IllegalArgumentException("Game is not start");
        }

        if (!this.deadPlayer.contains(uuid)) {
            throw new IllegalArgumentException(uuid + " is not dead");
        }

        if (this.specterPlayer.contains(uuid)) {
            throw new IllegalArgumentException(uuid + " is specter");
        }

        player.setGameMode(GameMode.ADVENTURE);
        this.deadPlayer.remove(uuid);
        this.survivePlayer.add(uuid);
    }

    public void victoryMurder() {
        this.sendTitle("§a§l[ §f승리! §a§l]", "", this.murderPlayer);
        this.sendTitle("§c§l[ §f패배! §c§l]", "", this.deadPlayer);
        this.clear();
    }

    public void victoryRunner() {
        this.sendTitle("§c§l[ §f패배! §c§l]", "", this.murderPlayer);
        this.sendTitle("§a§l[ §f승리! §a§l]", "", this.survivePlayer);
        this.sendTitle("§7§l[ §f승리? §7§l]", "", this.deadPlayer);
        this.clear();
    }

    @Override
    public void run() {
        if (this.isStarted()) {
            throw new IllegalStateException("Game is already started");
        }

        if (this.joinPlayer.size() < 3) {
            sendMessage("§c인원이 부족하여 게임이 취소되었습니다.", joinPlayer);
            return;
        }

        if (this.spawn == null || this.start == null || this.end == null) {
            sendMessage("§c게임 좌표지정 에러 발생. 관리자에게 문의하세요.", joinPlayer);
            return;
        }

        World world = this.spawn.getWorld();
        if (world != this.start.getWorld() || world != this.end.getWorld()) {
            sendMessage("§c게임 좌표지정 에러 발생. 관리자에게 문의하세요.", joinPlayer);
            return;
        }

        BoundingBox box = BoundingBox.of(start, end);
        this.area = new GameArea(gameCode, world, box);
        WorldData worldData = WorldData.getWorldData(world);
        worldData.registerArea(area);

        isStarted = true;
        KeyedBossBar bar = Bukkit.createBossBar(new NamespacedKey(RHAS.getPlugin(), "timer"), "타이머", BarColor.RED, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY);
        this.timer = new Timer("GameTimer", bar, time);

        for (UUID uuid : joinPlayer) {
            Player player = Bukkit.getPlayer(uuid);

            if (player != null)
                continue;

            joinPlayer.remove(uuid);
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            sendMessage("§c" + offlinePlayer.getName() + "플레이어가 접속상태가 아니므로 게임에서 강제 퇴장됩니다.", joinPlayer);
        }

        this.survivePlayer.addAll(this.joinPlayer);

        for (int i = 0; i < this.murderNum; i++) {
            UUID murder = this.survivePlayer.get(new Random().nextInt(this.survivePlayer.size()));
            this.survivePlayer.remove(murder);
            this.murderPlayer.add(murder);
        }

        for (int i = 1; i <= 3; i++) {
            final int finalI = i;
            Scheduler.delayScheduler(new Scheduler.RunnableEx() {
                @Override
                public void function() {
                    sendTitle("§c§l[ §f" + (4 - finalI) + "초 뒤 게임이 시작됩니다! §c§l]", "", joinPlayer);
                }
            }, i * 20);
        }

        this.joinPlayer.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            player.setGameMode(GameMode.SPECTATOR);
            player.getInventory().clear();
            player.setMaxHealth(20);
            player.setHealth(20);
        });

        Scheduler.delayScheduler(new Scheduler.RunnableEx() {
            @Override
            public void function() {
                sendTitle("§c§l[ §f게임 시작! §c§l]", "§f15초뒤 능력과 살인마가 정해집니다!", joinPlayer);
                Game.this.joinPlayer.forEach(uuid -> {
                    Player player = Bukkit.getPlayer(uuid);
                    player.teleport(Game.this.spawn);
                    player.setGameMode(GameMode.ADVENTURE);
                });
            }
        }, 80);
    }

    private static class GameArea implements Area {
        private final World world;
        private final BoundingBox boundingBox;
        private final NamespacedKey key;
        private GameArea(UUID uuid, World world, BoundingBox boundingBox) {
            this.world = world;
            this.boundingBox = boundingBox;
            this.key = new NamespacedKey(RHAS.getPlugin(), uuid.toString());
        }
        @Override
        public BoundingBox getBoundingBox() {
            return boundingBox;
        }

        @Override
        public World getWorld() {
            return world;
        }

        @Override
        public NamespacedKey getKey() {
            return key;
        }

        @Override
        public boolean contain(Location location) {
            return this.boundingBox.contains(location.toVector());
        }

        @Override
        public boolean contain(BoundingBox boundingBox) {
            return this.boundingBox.contains(boundingBox);
        }

        @Override
        public boolean contain(Area area) {
            return this.boundingBox.contains(area.getBoundingBox());
        }

        @Override
        public boolean overlap(Area area) {
            return this.overlap(area.getBoundingBox());
        }

        @Override
        public boolean overlap(BoundingBox boundingBox) {
            return this.boundingBox.overlaps(boundingBox);
        }
    }
}