package fr.mathisskate.justenoughthings.util;

import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class Utils {
    public static boolean isGlass(BlockState blockState) {
        return (blockState.is(Tags.Blocks.GLASS) || blockState.is(Tags.Blocks.STAINED_GLASS_PANES) || blockState.is(Tags.Blocks.GLASS_PANES) || blockState.is(Tags.Blocks.STAINED_GLASS) || blockState.getBlock() instanceof AbstractGlassBlock || blockState.getBlock() instanceof StainedGlassPaneBlock);
    }
}
