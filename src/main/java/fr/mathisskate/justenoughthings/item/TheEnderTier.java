package fr.mathisskate.justenoughthings.item;


import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class TheEnderTier implements Tier {

    @Override
    public int getUses() {
        return 1024;
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 12;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
