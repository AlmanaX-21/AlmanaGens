package me.almana.almanagens;

import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.models.AvgPlayer;
import me.almana.almanagens.utils.JsonUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class AlmanaGens extends JavaPlugin {

    private static Plugin plugin;
    private static String serverPrefix;
    private static MiniMessage miniMessage = MiniMessage.miniMessage();
    private File genFile;
    private File playerFile;
    private ArrayList<AvgGen> avgGens = new ArrayList<>();
    private ArrayList<AvgPlayer> avgPlayers = new ArrayList<AvgPlayer>();

    @Override
    public void onEnable() {

        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        serverPrefix = getConfig().getString("PREFIX");

        genFile = new File(getDataFolder().getAbsolutePath() + "/gens.json");
        playerFile = new File(getDataFolder().getAbsolutePath() + "/players.json");

        getServer().getScheduler().runTaskAsynchronously(this, () -> {
            try {
                avgGens = JsonUtils.readGens(genFile);
                avgPlayers = JsonUtils.readPlayers(playerFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {

            try {
                JsonUtils.savePlayerJson(avgPlayers, playerFile);
                getLogger().info("Saved player data.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 6000L, 500L);
    }

    @Override
    public void onDisable() {

        try {
            JsonUtils.saveGenJson(avgGens, genFile);
            JsonUtils.savePlayerJson(avgPlayers, playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static String getServerPrefix() {
        return serverPrefix;
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
}
