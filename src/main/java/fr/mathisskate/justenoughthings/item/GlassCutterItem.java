package fr.mathisskate.justenoughthings.item;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;


public class GlassCutterItem extends DiggerItem {

    public GlassCutterItem() {
        super(0, 0, Tiers.IRON, null, new Properties());
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        BlockState blockState = player.level().getBlockState(pos);
        if (!player.isCreative() && blockState.getBlock() instanceof GlassBlock)
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player) == 0)
                itemstack.enchant(Enchantments.SILK_TOUCH, 1);
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(itemstack, world, entity, slot, isSelected);
        ListTag tagList = itemstack.getEnchantmentTags();
        if (tagList != null)
            for (int i = 0; i < tagList.size(); i++)
                if (tagList.get(i).getId() == 10)
                    tagList.remove(i);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.GLASS))
            return 1.0F;
        return 1.5F;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState block) {
        return block.getBlock() instanceof GlassBlock;
    }

    public boolean hasContainerItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage();
    }
}
