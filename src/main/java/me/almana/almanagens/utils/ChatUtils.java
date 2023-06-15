package me.almana.almanagens.utils;

import me.almana.almanagens.AlmanaGens;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void sendServerMessage(String msg, Player player) {

        msg = AlmanaGens.getServerPrefix() + msg;
        player.sendMessage(AlmanaGens.getMiniMessage().deserialize(msg));
    }
}
