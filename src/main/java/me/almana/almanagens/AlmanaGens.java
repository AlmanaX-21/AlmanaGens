package me.almana.almanagens;

import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.models.AvgItem;
import me.almana.almanagens.utils.AvgItemBuilder;
import me.almana.almanagens.utils.JsonUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AlmanaGens extends JavaPlugin {

    private static Plugin plugin;
    private static String serverPrefix;
    private static MiniMessage miniMessage = MiniMessage.miniMessage();
    private File genFile;
    private ArrayList<AvgGen> avgGens;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        serverPrefix = getConfig().getString("PREFIX");

        genFile = new File(getDataFolder().getAbsolutePath() + "/gens.json");

        try {
            avgGens = JsonUtils.readGens(genFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        test();
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

    private void test() {

        AvgItem drop = AvgItemBuilder.builder(Material.IRON_INGOT)
                .name("bruh")
                .lore(List.of("bruh", "blow"))
                .buildDrop();
        AvgGen gen = AvgItemBuilder.builder(Material.IRON_BLOCK)
                .name("Ahh").id("iron").isEnchanted(true).drop(drop).buildGen();
        avgGens.add(gen);

        getLogger().info(avgGens.toString());
    }
}
