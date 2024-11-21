package fr.switchback.jet.block;


import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class MobGlassBlock extends Block {
    private final boolean COLLIDEMOB;

    public MobGlassBlock() {
        super(Properties.ofFullCopy(Blocks.GLASS).noCollission().isSuffocating(MobGlassBlock::isntSolid).isViewBlocking(MobGlassBlock::isntOpaque));
        this.COLLIDEMOB = false;
    }

    private static boolean isntOpaque(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    private static boolean isntSolid(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return (context instanceof EntityCollisionContext && ((EntityCollisionContext)context).getEntity() instanceof Mob) == COLLIDEMOB ? state.getShape(world, pos) : Shapes.empty();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Mob can pass away the block").withColor(16711680));
    }
}