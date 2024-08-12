package fr.mathisskate.jet.item;

import net.minecraft.world.item.ItemStack;

public class GlassCutterUnbreakableItem extends GlassCutterItem {
    public GlassCutterUnbreakableItem() {
        super();
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return false;
    }
}
