package me.almana.almanagens.listeners;

import me.almana.almanagens.AlmanaGens;
import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.models.AvgPlayer;
import me.almana.almanagens.utils.AvgUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.A;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public class GenPlaceListener implements Listener {

    private ArrayList<AvgPlayer> avgPlayers;
    private ArrayList<AvgGen> avgGens;

    public GenPlaceListener(ArrayList<AvgPlayer> avgPlayers, ArrayList<AvgGen> avgGens) {
        this.avgPlayers = avgPlayers;
        this.avgGens = avgGens;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();
        ItemStack stack = event.getItemInHand().asQuantity(1); // Grab singular item

        if (stack == null || stack.getType() == Material.AIR) return;

        PersistentDataContainer stackContainer = stack.getItemMeta().getPersistentDataContainer();
        NamespacedKey id = new NamespacedKey(AlmanaGens.getPlugin(), "id");
        NamespacedKey genList = new NamespacedKey(AlmanaGens.getPlugin(), "list");

        if (!stackContainer.has(id, PersistentDataType.STRING)) return;

        AvgPlayer avgPlayer = AvgUtils.findAvgPlayer(player, avgPlayers);
        if (avgPlayer == null) return;

        avgPlayer.getGenIdList().add(AvgUtils.findAvgGen(stack, avgGens).getId());
        player.sendMessage("AvgGens: " + avgPlayer.getGenIdList());
        player.sendMessage("Gen Placed.");
    }
}
