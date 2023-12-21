package fr.mathisskate.justenoughthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class FertilizedDirtBlock extends Block {
    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    private static final VoxelShape SHAPE_TILLED = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public FertilizedDirtBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.DIRT).randomTicks());
        this.defaultBlockState().setValue(TILLED, true);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
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

    public static VoxelShape getShapeTilled() {
        return SHAPE_TILLED;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TILLED);
    }

    public boolean allowsMovement(BlockState state, ServerLevel level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType plantType = plantable.getPlantType(world, pos.above());
        boolean tilled = state.getValue(TILLED);


        Block b = plantable.getPlant(world, pos.offset(facing.getNormal())).getBlock();

        if (b instanceof AttachedStemBlock) {
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
}
