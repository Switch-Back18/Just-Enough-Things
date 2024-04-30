package fr.mathisskate.justenoughthings.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;


public class GlassCutterItem extends DiggerItem {

    public GlassCutterItem() {
        super(0, 0, Tiers.IRON, null, new Properties());
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        Level level = player.level();
        BlockState blockState = level.getBlockState(pos);
        ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockState.getBlock()));
        if (!player.isCreative() && (blockState.is(Tags.Blocks.GLASS) || blockState.is(Tags.Blocks.GLASS_PANES)))
            if(!level.isClientSide)
                level.addFreshEntity(itemEntity);
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.GLASS))
            return 1.0F;
        return 1.5F;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState block) {
        return block.is(Tags.Blocks.GLASS) || block.is(Tags.Blocks.GLASS_PANES);
    }

    public boolean hasContainerItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage();
    }
}
