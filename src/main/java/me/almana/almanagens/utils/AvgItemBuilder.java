package me.almana.almanagens.utils;

import me.almana.almanagens.models.AvgGen;
import me.almana.almanagens.models.AvgItem;
import org.bukkit.Material;

import java.util.Collection;

public class AvgItemBuilder {

    private String id;
    private Material material;
    private String name;
    private Collection<String> lore;
    private boolean isEnchanted = false;
    private int customModelData = 0;
    private AvgItem drop;

    public AvgItemBuilder(Material material) {

        this.material = material;
    }

    public static AvgItemBuilder builder(Material material) {

        return new AvgItemBuilder(material);
    }

    public AvgItemBuilder id(String id) {

        this.id = id;
        return this;
    }

    public AvgItemBuilder material(Material material) {

        this.material = material;
        return this;
    }

    public AvgItemBuilder name(String name) {

        this.name = name;
        return this;
    }

    public AvgItemBuilder lore(Collection<String> lore) {

        this.lore = lore;
        return this;
    }

    public AvgItemBuilder isEnchanted(boolean isEnchanted) {

        this.isEnchanted = isEnchanted;
        return this;
    }

    public AvgItemBuilder customModelData(int customModelData) {

        this.customModelData = customModelData;
        return this;
    }

    public AvgItemBuilder drop(AvgItem drop) {

        this.drop = drop;
        return this;
    }

    public AvgGen buildGen() {

        return new AvgGen(id, material, name, lore, isEnchanted, customModelData, drop);
    }

    public AvgItem buildDrop() {

        return new AvgItem(material, name, lore, isEnchanted, customModelData);
    }
}
