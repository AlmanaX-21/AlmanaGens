package me.almana.almanagens.models;

import me.almana.almanagens.AlmanaGens;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;

public class AvgItem {

    private Material material;
    private String name;
    private Collection<String> lore;
    private boolean isEnchanted;
    private int customModelData;

    public AvgItem(Material material, String name, Collection<String> lore, boolean isEnchanted, int customModelData) {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.isEnchanted = isEnchanted;
        this.customModelData = customModelData;
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

    @Override
    public String toString() {
        return "AvgItem{" +
                "material=" + material +
                ", name='" + name + '\'' +
                ", lore=" + lore +
                ", isEnchanted=" + isEnchanted +
                ", customModelData=" + customModelData +
                '}';
    }

    public ItemStack convert() {

        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();

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
