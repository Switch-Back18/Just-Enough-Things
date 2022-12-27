package fr.mathisskate.justenoughthings.block;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class FertilizedDirtBlock extends Block {
    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    private static final VoxelShape SHAPE_TILLED = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public FertilizedDirtBlock() {
        super(Block.Properties.from(Blocks.DIRT).tickRandomly());

        this.setDefaultState(this.stateContainer.getBaseState().with(TILLED, false));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);

        for (int i = 0; i < 3; i++) {
            BlockState aboveState = worldIn.getBlockState(pos.up());
            Block aboveBlock = aboveState.getBlock();

            if (aboveBlock instanceof IPlantable && aboveState.ticksRandomly()) {
                aboveState.randomTick(worldIn, pos.up(), random);
            } else {
                break;
            }
        }

    }

    @Override
    public boolean isToolEffective(BlockState state, ToolType tool) {
        return tool == ToolType.SHOVEL;
    }

    public boolean tickRandomly(BlockState state) {
        return state.get(TILLED);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(TILLED) ? SHAPE_TILLED : VoxelShapes.fullCube();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TILLED);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType plantType = plantable.getPlantType(world, pos.up());
        boolean tilled = state.get(TILLED);

        Block b = plantable.getPlant(world, pos.offset(facing)).getBlock();

        if (b.getBlock() instanceof AttachedStemBlock) {
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
                return !tilled || tilled && world.getBlockState(pos.up()).getBlock() == Blocks.BEETROOTS;
            case "water":
                return false;
            case "beach":
                return !tilled;
        }

        return false;
    }

    @Override
    public boolean isFertile(BlockState state, IBlockReader world, BlockPos pos) {
        return true;
    }
}
