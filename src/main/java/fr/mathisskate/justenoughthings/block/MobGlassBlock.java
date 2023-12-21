package fr.mathisskate.justenoughthings.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MobGlassBlock extends GlassBlock {
    private final boolean COLLIDEMOB;

    public MobGlassBlock() {
        super(Block.Properties.copy(Blocks.GLASS).isViewBlocking(MobGlassBlock::isntOpaque));
        this.COLLIDEMOB = false;
    }

    private static boolean isntOpaque(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return (context instanceof EntityCollisionContext && ((EntityCollisionContext)context).getEntity() instanceof Mob) == COLLIDEMOB ? state.getShape(world, pos) : Shapes.empty();
    }


    /*@Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable BlockGetter world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, world, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("Mob can pass away the block")).mergeStyle(TextF.RED));
    }*/
}