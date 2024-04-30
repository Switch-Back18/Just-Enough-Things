package fr.mathisskate.justenoughthings.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;


public class GlassCutterItem extends DiggerItem {

    public GlassCutterItem() {
        super(0, 0, Tiers.IRON, null, new Properties());
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos pos, LivingEntity entity) {
        if(entity instanceof Player) {
            Player player = (Player) entity;
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockState.getBlock()));
            if (!player.isCreative() && (blockState.is(Tags.Blocks.GLASS) || blockState.is(Tags.Blocks.GLASS_PANES) || blockState.getBlock() instanceof GlassBlock))
                if (!level.isClientSide)
                    level.addFreshEntity(itemEntity);
        }
        return super.mineBlock(itemStack, level, blockState, pos, entity);
    }

    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        if (blockState.is(Tags.Blocks.GLASS) || blockState.is(Tags.Blocks.GLASS_PANES) || blockState.getBlock() instanceof GlassBlock)
            return 1.0F;
        return 1.5F;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockState) {
        return blockState.is(Tags.Blocks.GLASS) || blockState.is(Tags.Blocks.GLASS_PANES) || blockState.getBlock() instanceof GlassBlock;
    }
}
