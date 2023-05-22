package org.red.has;

import org.bukkit.Location;
import org.red.library.util.map.DataMap;

import java.util.List;
import java.util.UUID;

public class GameSetting {
    private final Game game;
    private final DataMap setting = new DataMap();
    public GameSetting(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public boolean isFallDamage() {
        return this.setting.getBoolean("fall_damage");
    }

    public void setFallDamage(boolean fallDamage) {
        this.setting.set("fall_damage", fallDamage);
    }

    public boolean isHunger() {
        return this.setting.getBoolean("hunger");
    }

    public void setHunger(boolean hunger) {
        this.setting.set("hunger", hunger);
    }

    public boolean isDeathMessage() {
        return this.setting.getBoolean("death_message", true);
    }

    public void setDeathMessage(boolean deathMessage) {
        this.setting.set("death_message", deathMessage);
    }

    public boolean isNameVisible() {
        return this.setting.getBoolean("name_visible", true);
    }

    public void setNameVisible(boolean nameVisible) {
        this.setting.set("name_visible", nameVisible);
    }

    public boolean isSameSkin() {
        return this.setting.getBoolean("same_skin");
    }

    public void setSameSkin(boolean sameSkin) {
        this.setting.set("same_skin", sameSkin);
    }

    public int getTime() {
        return this.setting.getInt("time", 240);
    }

    public void setTime(int time) {
        this.setting.set("time", time);
    }

    public int getMaxPlayer() {
        return this.setting.getInt("max_player", 10);
    }

    public void setMaxPlayer(int maxPlayer) {
        this.setting.set("max_player", maxPlayer);
    }

    public int getMurderNum() {
        return this.setting.getInt("murder_num", 1);
    }

    public void setMurderNum(int murderNum) {
        this.setting.set("murder_num", murderNum);
    }

    public Location getStartLoc() {
        return this.setting.getLocation("start_loc", null);
    }

    public void setStartLoc(Location start) {
        this.setting.set("start_loc", start);
    }

    public Location getSpawnLoc() {
        return this.setting.getLocation("spawn_loc", null);
    }

    public void setSpawnLoc(Location start) {
        this.setting.set("spawn_loc", start);
    }

    public Location getEndLoc() {
        return this.setting.getLocation("end_loc", null);
    }

    public void setEndLoc(Location start) {
        this.setting.set("end_loc", start);
    }

    public List<UUID> getJoinPlayers() {
        return this.setting.getList("join_players");
    }

    public void setJoinPlayers(List<UUID> joinPlayers) {
        this.setting.set("join_players", joinPlayers);
    }

    public void addJoinPlayer(UUID playerUUID) {
        this.getJoinPlayers().add(playerUUID);
    }

    public void removeJoinPlayer(UUID playerUUID) {
        this.getJoinPlayers().remove(playerUUID);
    }

    public boolean containsJoinPlayer(UUID playerUUID) {
        return this.getJoinPlayers().contains(playerUUID);
    }

    public float getRunnerSpeed() {
        return (float) this.setting.getDouble("runner_speed", 0.2D);
    }

    public void setRunnerSpeed(float speed) {
        this.setting.set("runner_speed", (double) speed);
    }

    public float getMurderSpeed() {
        return (float) this.setting.getDouble("murder_speed", 0.23D);
    }

    public void setMurderSpeed(float speed) {
        this.setting.set("murder_speed", (double) speed);
    }

    public boolean locSettingCheck() {
        return this.getStartLoc() != null || this.getEndLoc() != null || this.getSpawnLoc() != null;
    }
}
