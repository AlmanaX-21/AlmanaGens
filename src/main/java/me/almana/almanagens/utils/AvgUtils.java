package me.almana.almanagens.utils;

import me.almana.almanagens.AlmanaGens;
import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.models.AvgPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class AvgUtils {

    public static AvgPlayer findAvgPlayer(Player player, List<AvgPlayer> avgPlayers) {

        String uuid = player.getUniqueId().toString();

        for (AvgPlayer avgPlayer: avgPlayers) {

            if (avgPlayer.getUuid().equals(uuid)) return avgPlayer;
        }
        return null;
    }

    public static AvgGen findAvgGen(ItemStack stack, List<AvgGen> avgGens) {

        String id = stack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(AlmanaGens.getPlugin(), "id"), PersistentDataType.STRING);


        for (AvgGen gen: avgGens) {

            if (gen.getId().equals(id)) return gen;
        }
        return null;
    }
}
