package me.almana.almanagens.utils;

import com.google.gson.Gson;
import me.almana.almanagens.AlmanaGens;
import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.models.AvgPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static void saveGenJson(ArrayList<AvgGen> toSave, File file) throws IOException {

        file.createNewFile();
        file.getParentFile().mkdirs();
        Writer writer = new FileWriter(file, false);
        gson.toJson(toSave, writer);
        writer.flush();
        writer.close();
    }

    public static ArrayList<AvgGen> readGens(File file) throws IOException {

        file.createNewFile();
        file.getParentFile().mkdirs();
        if (!file.exists()) {

            throw new NullPointerException("No file found to read...");
        }
        Reader reader = new FileReader(file);
        AvgGen[] genArray = gson.fromJson(reader, AvgGen[].class);
        AlmanaGens.getPlugin().getLogger().info("Gens loaded: " + genArray.length);
        return new ArrayList<AvgGen>(List.of(genArray));
    }

    public static ArrayList<AvgPlayer> readPlayers(File file) throws IOException {

        file.createNewFile();
        file.getParentFile().mkdirs();
        if (!file.exists()) {

            throw new NullPointerException("No file found to read...");
        }
        Reader reader = new FileReader(file);
        AvgPlayer[] playerArray = gson.fromJson(reader, AvgPlayer[].class);
        AlmanaGens.getPlugin().getLogger().info("Gens loaded: " + playerArray.length);
        return new ArrayList<AvgPlayer>(List.of(playerArray));
    }

    public static void savePlayerJson(ArrayList<AvgPlayer> toSave, File file) throws IOException {

        file.createNewFile();
        file.getParentFile().mkdirs();
        Writer writer = new FileWriter(file, false);
        gson.toJson(toSave, writer);
        writer.flush();
        writer.close();
    }
}
