package fr.mathisskate.jet.block;

import fr.mathisskate.jet.JustEnoughThings;
import fr.mathisskate.jet.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.util.TriState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class FertilizedDirtBlock extends Block {

    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    private static final VoxelShape SHAPE_TILLED = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    private static final VoxelShape SHAPE_DEFAULT = Block.box(0.0D,0.0D,0.0D,16.0D, 16.0D,16.0D);

    public FertilizedDirtBlock() {
        super(Properties.ofFullCopy(Blocks.DIRT).setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("jet", "fertilized_dirt"))));
        this.registerDefaultState(this.getStateDefinition().any().setValue(TILLED, false));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        BlockState aboveState = level.getBlockState(pos.above());
        for (int i = 0; i < 5; i++) {
            if (Utils.canGrow(aboveState.getBlock())) {
                aboveState.randomTick(level, pos.above(), random);
            }
            else
                break;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(TILLED) ? SHAPE_TILLED : SHAPE_DEFAULT;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TILLED);
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, BlockState plant) {
        Block plantBlock = plant.getBlock();
        ItemStack plantItem = new ItemStack(plantBlock);
        boolean tilled = state.getValue(TILLED);
        if (Utils.isPlantable(plantItem) && tilled) {
            return TriState.TRUE;
        }
        else if (plantBlock instanceof SugarCaneBlock && !tilled)
            return TriState.TRUE;
        else if (plantBlock instanceof SaplingBlock && !tilled)
            return TriState.TRUE;
        else if (plantBlock instanceof MushroomBlock && !tilled)
            return TriState.TRUE;
        return TriState.FALSE;
    }

    @Override
    public boolean onTreeGrow(BlockState state, LevelReader level, BiConsumer<BlockPos, BlockState> placeFunction, RandomSource randomSource, BlockPos pos, TreeConfiguration config) {
        return true;
    }

    @Override
    public boolean isFertile(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Grow plants faster").withColor(65280));
    }
}
