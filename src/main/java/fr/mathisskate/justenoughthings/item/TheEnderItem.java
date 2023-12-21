package fr.mathisskate.justenoughthings.item;

import fr.mathisskate.justenoughthings.util.References;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

public class TheEnderItem extends SwordItem {
    public TheEnderItem() {
        super(new TheEnderTier(), 5, 1, new Properties().tab(References.jet_tab));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player attacker, Entity target) {
        if(target instanceof EnderMan) {
            target.teleportTo(attacker.getOnPos().getX(), attacker.getOnPos().getY()+1, attacker.getOnPos().getZ());
        }
        return super.onLeftClickEntity(stack, attacker, target);
    }
}
