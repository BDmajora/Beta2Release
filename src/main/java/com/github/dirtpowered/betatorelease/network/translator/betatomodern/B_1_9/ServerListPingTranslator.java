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

package com.github.dirtpowered.betatorelease.network.translator.betatomodern.B_1_9;

import com.github.dirtpowered.betaprotocollib.packet.Version_B1_7.data.KickDisconnectPacketData;
import com.github.dirtpowered.betaprotocollib.packet.Version_B1_8.data.ServerListPingPacketData;
import com.github.dirtpowered.betatorelease.BetaToRelease;
import com.github.dirtpowered.betatorelease.network.client.ModernClient;
import com.github.dirtpowered.betatorelease.network.session.ServerSession;
import com.github.dirtpowered.betatorelease.network.translator.model.BetaToModern;
import com.github.dirtpowered.betatorelease.utils.chat.ChatUtils;

public class ServerListPingTranslator implements BetaToModern<ServerListPingPacketData> {

    @Override
    public void translate(BetaToRelease main, ServerListPingPacketData packet, ServerSession session, ModernClient modernClient) {
        //TODO: Translate it on server thread, not client
        modernClient.getStatus(result -> {
            String strippedMotd = ChatUtils.stripColor(result.getMotd());
            String legacyMotd = strippedMotd.substring(0, Math.min(50, strippedMotd.length()));

            String legacyPingMessage = legacyMotd + ChatUtils.COLOR_CHAR + result.getOnline() + ChatUtils.COLOR_CHAR + result.getMaxOnline();

            session.sendPacket(new KickDisconnectPacketData(legacyPingMessage));
            main.getLogger().info("[" + session.getAddress() + "] has pinged");
        });
    }
}