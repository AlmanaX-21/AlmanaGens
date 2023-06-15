package me.almana.almanagens;

import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.utils.JsonUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
    private ArrayList<AvgGen> avgGens = new ArrayList<>();

    @Override
    public void onEnable() {

        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        serverPrefix = getConfig().getString("PREFIX");

        genFile = new File(getDataFolder().getAbsolutePath() + "/gens.json");

        try {

            avgGens = JsonUtils.readGens(genFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

        try {
            JsonUtils.saveGenJson(avgGens, genFile);
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
