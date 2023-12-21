package fr.mathisskate.justenoughthings.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class References {
    public final static String MODID = "jet";

    public static CreativeModeTab jet_tab = new CreativeModeTab("jet") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.NETHERITE_SWORD);
        }
    };
}
