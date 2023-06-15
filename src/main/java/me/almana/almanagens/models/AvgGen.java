package me.almana.almanagens.models;

import me.almana.almanagens.AlmanaGens;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;

public class AvgGen {

    private String id;
    private Material material;
    private String name;
    private Collection<String> lore = new ArrayList<String>();
    private boolean isEnchanted;
    private int customModelData;
    private AvgItem drop;

    public AvgGen(String id, Material material, String name, Collection<String> lore, boolean isEnchanted, int customModelData, AvgItem drop) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.isEnchanted = isEnchanted;
        this.customModelData = customModelData;
        this.drop = drop;
    }

    @Override
    public String toString() {
        return "AvgGen{" +
                "id='" + id + '\'' +
                ", material=" + material +
                ", name='" + name + '\'' +
                ", lore=" + lore +
                ", isEnchanted=" + isEnchanted +
                ", customModelData=" + customModelData +
                ", drop=" + drop.toString() +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getLore() {
        return lore;
    }

    public void setLore(Collection<String> lore) {
        this.lore = lore;
    }

    public boolean isEnchanted() {
        return isEnchanted;
    }

    public void setEnchanted(boolean enchanted) {
        isEnchanted = enchanted;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public AvgItem getDrop() {
        return drop;
    }

    public void setDrop(AvgItem drop) {
        this.drop = drop;
    }

    public ItemStack convert() {

        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(new NamespacedKey(AlmanaGens.getPlugin(), "id"), PersistentDataType.STRING, id);

        MiniMessage miniMessage = AlmanaGens.getMiniMessage();
        meta.displayName(miniMessage.deserialize(name));
        ArrayList<Component> loreList = new ArrayList<>();

        for (String lore: lore) {

            loreList.add(miniMessage.deserialize(lore));
        }
        meta.lore(loreList);

        if (isEnchanted) {

            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setCustomModelData(customModelData);
        stack.setItemMeta(meta);

        return stack;
    }
}
