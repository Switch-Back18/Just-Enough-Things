package fr.mathisskate.justenoughthings.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class References {
    public final static String MODID = "jet";
    public static ItemGroup jet_Group = new ItemGroup("jet") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.NETHERITE_SWORD);
        }
    };
}
