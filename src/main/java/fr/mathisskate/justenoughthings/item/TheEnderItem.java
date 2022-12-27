package fr.mathisskate.justenoughthings.item;

import fr.mathisskate.justenoughthings.util.References;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class TheEnderItem extends SwordItem {
    public TheEnderItem() {
        super(new TheEnderTier(), 5, 1, new Properties().group(References.jet_Group));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker instanceof PlayerEntity) {
            if(target instanceof EndermanEntity) {
                target.attemptTeleport(attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), true);
            }
        }
        return super.hitEntity(stack, target, attacker);
    }
}
