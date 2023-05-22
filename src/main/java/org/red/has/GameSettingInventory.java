package org.red.has;

import org.red.library.invetory.CustomInventory;

public class GameSettingInventory extends CustomInventory {
    private final GameSetting gameSetting;
    public GameSettingInventory(GameSetting gameSetting) {
        super(27, "게임 설정");
        this.gameSetting = gameSetting;
    }
}
