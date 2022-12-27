package fr.mathisskate.justenoughthings.block;


import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.EntitySelectionContext;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class MobGlassBlock extends GlassBlock {
    private final boolean COLLIDEMOB;

    public MobGlassBlock() {
        super(Block.Properties.from(Blocks.GLASS).setOpaque(MobGlassBlock::isntOpaque).setBlocksVision(MobGlassBlock::isntOpaque));
        this.COLLIDEMOB = false;
    }

    private static boolean isntOpaque(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return (context instanceof EntitySelectionContext && ((EntitySelectionContext)context).getEntity() instanceof MobEntity) == COLLIDEMOB ? state.getShape(world, pos) : VoxelShapes.empty();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("Mob can pass away the block")).mergeStyle(TextFormatting.RED));
    }
}