/*
 * Copyright (c) 2020 Dirt Powered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.dirtpowered.betatorelease.data.magicvalues;

import com.github.dirtpowered.betatorelease.data.magicvalues.model.DataHolder;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.data.game.setting.Difficulty;
import com.github.steveice10.mc.protocol.data.game.world.notify.ClientNotification;

public class MagicValues {

    private static DifficultyMap difficultyMap;
    private static GamemodeMap gamemodeMap;
    private static ClientNotificationMap clientNotificationMap;

    static {
        difficultyMap = new DifficultyMap();
        gamemodeMap = new GamemodeMap();
        clientNotificationMap = new ClientNotificationMap();
    }

    public static int getDifficultyId(Difficulty difficulty) {
        return difficultyMap.getFromNamespace(difficulty);
    }

    public static int getGameModeId(GameMode gameMode) {
        return gamemodeMap.getFromNamespace(gameMode);
    }

    public static int getClientNotificationId(ClientNotification clientNotification) {
        return clientNotificationMap.getFromNamespace(clientNotification);
    }

    static class DifficultyMap extends DataHolder<Difficulty> {

        DifficultyMap() {
            add(Difficulty.PEACEFUL, 0);
            add(Difficulty.EASY, 1);
            add(Difficulty.NORMAL, 2);
            add(Difficulty.HARD, 3);
        }
    }

    static class GamemodeMap extends DataHolder<GameMode> {

        GamemodeMap() {
            add(GameMode.SURVIVAL, 0);
            add(GameMode.CREATIVE, 1);
            add(GameMode.ADVENTURE, 0);
            add(GameMode.SPECTATOR, 1);
        }
    }

    static class ClientNotificationMap extends DataHolder<ClientNotification> {

        ClientNotificationMap() {
            add(ClientNotification.INVALID_BED, 0);
            add(ClientNotification.START_RAIN, 1);
            add(ClientNotification.STOP_RAIN, 2);
            add(ClientNotification.CHANGE_GAMEMODE, 3);
            add(ClientNotification.ENTER_CREDITS, 4);
        }
    }
}