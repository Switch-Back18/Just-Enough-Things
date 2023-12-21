package fr.mathisskate.justenoughthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FertilizedDirtBlock extends Block {
    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    private static final VoxelShape SHAPE_TILLED = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    private static final VoxelShape SHAPE_DEFAULT = Block.box(0.0D,0.0D,0.0D,16.0D, 16.0D,16.0D);
    public FertilizedDirtBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.DIRT).randomTicks());
        this.registerDefaultState(this.getStateDefinition().any().setValue(TILLED, false));
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel level, BlockPos pos, Random random) {
        super.randomTick(state, level, pos, random);

        for (int i = 0; i < 3; i++) {
            BlockState aboveState = level.getBlockState(pos.above());
            Block aboveBlock = aboveState.getBlock();

            if (aboveBlock instanceof IPlantable && aboveState.isRandomlyTicking()) {
                aboveState.randomTick(level, pos.above(), random);
            } else {
                break;
            }
        }
    }

    public boolean tickRandomly(BlockState state) {
        return state.getValue(TILLED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(TILLED) ? SHAPE_TILLED : SHAPE_DEFAULT;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TILLED);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType plantType = plantable.getPlantType(world, pos.above());
        boolean tilled = state.getValue(TILLED);


        Block block = plantable.getPlant(world, pos.offset(facing.getNormal())).getBlock();

        if (block instanceof AttachedStemBlock) {
            return true;
        }

        switch (plantType.getName()) {
            case "desert":
                return !tilled;
            case "nether":
                return !tilled;
            case "crop":
                return tilled;
            case "cave":
                return !tilled;
            case "plains":
                return !tilled || tilled && world.getBlockState(pos.above()).getBlock() == Blocks.BEETROOTS;
            case "water":
                return false;
            case "beach":
                return !tilled;
        }

        return false;
    }

    @Override
    public boolean isFertile(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    /*@OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add("");
    }*/
}
