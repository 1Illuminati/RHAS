package org.red.has;

public class GameSetting {
    private final Game game;
    private boolean isFallDamage = false;
    private boolean isHunger = false;
    private boolean isDeathMessage = true;
    private boolean isNameVisible = true;
    private boolean isAllSameSkin = false;
    private int time = 240;
    private int maxPlayer = 10;
    private int murderNum = 1;
    public GameSetting(Game game) {
        this.game = game;
    }

    public boolean isFallDamage() {
        return isFallDamage;
    }

    public void setFallDamage(boolean fallDamage) {
        isFallDamage = fallDamage;
    }

    public boolean isHunger() {
        return isHunger;
    }

    public void setHunger(boolean hunger) {
        isHunger = hunger;
    }

    public boolean isDeathMessage() {
        return isDeathMessage;
    }

    public void setDeathMessage(boolean deathMessage) {
        isDeathMessage = deathMessage;
    }

    public boolean isNameVisible() {
        return isNameVisible;
    }

    public void setNameVisible(boolean nameVisible) {
        isNameVisible = nameVisible;
    }

    public boolean isAllSameSkin() {
        return isAllSameSkin;
    }

    public void setAllSameSkin(boolean allSameSkin) {
        isAllSameSkin = allSameSkin;
    }
}
