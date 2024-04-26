package fr.mathisskate.justenoughthings.item;

import net.minecraft.world.item.ItemStack;

public class GlassCutterUnbreakableItem extends GlassCutterItem {
    public GlassCutterUnbreakableItem() {
        super();
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }
}
