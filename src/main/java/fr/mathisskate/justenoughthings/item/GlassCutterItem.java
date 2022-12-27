package fr.mathisskate.justenoughthings.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static fr.mathisskate.justenoughthings.util.References.jet_Group;

public class GlassCutterItem extends ToolItem {

    public GlassCutterItem() {
        super(0, 0, ItemTier.IRON, null, new Properties().group(jet_Group));
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        BlockState blockState = player.world.getBlockState(pos);
        if (!player.isCreative() && blockState.getMaterial() == Material.GLASS)
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) == 0)
                itemstack.addEnchantment(Enchantments.SILK_TOUCH, 1);
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(itemstack, world, entity, slot, isSelected);
        ListNBT tagList = itemstack.getEnchantmentTagList();
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
        if (state.getMaterial() == Material.GLASS)
            return 1.0F;
        return 1.5F;
    }

    @Override
    public boolean canHarvestBlock(BlockState block) {
        return block.getMaterial() == Material.GLASS;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage();
    }
}
