package fr.mathisskate.justenoughthings.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class TheEnderTier implements IItemTier {

    @Override
    public int getMaxUses() {
        return 1024;
    }

    @Override
    public float getEfficiency() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 5;
    }

    @Override
    public int getHarvestLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.EMPTY;
    }
}
