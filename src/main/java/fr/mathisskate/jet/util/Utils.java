package fr.mathisskate.jet.util;

import fr.mathisskate.jet.registry.ModBlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class Utils {
    public static boolean isGlass(BlockState blockState) {
        return (blockState.is(Tags.Blocks.GLASS_BLOCKS) ||
                blockState.is(Tags.Blocks.GLASS_BLOCKS_TINTED) ||
                blockState.is(Tags.Blocks.GLASS_PANES) ||
                blockState.is(Tags.Blocks.GLASS_BLOCKS_CHEAP) ||
                blockState.is(Tags.Blocks.GLASS_BLOCKS_COLORLESS) ||
                blockState.is(Tags.Blocks.GLASS_PANES_COLORLESS) ||
                blockState.is(ModBlocks.MOB_GLASS_BLOCK.get()) ||
                blockState.is(ModBlocks.PLAYER_GLASS_BLOCK.get())
        );
    }

    public static boolean isCrop(BlockState blockState) {
        List<TagKey<Block>> tags =  blockState.getTags().toList();
        for(TagKey<Block> tag : tags) {
            if (tag.toString().contains("minecraft:crops"))
                return true;
        }
        return false;
    }
    public static boolean isPlant (BlockState blockState) {
        Block block = blockState.getBlock();
        if (Utils.isCrop(blockState)) {
            return true;
        } else if (block instanceof SugarCaneBlock) {
            return true;
        } else if (block instanceof SaplingBlock) {
            return true;
        } else if (block instanceof MushroomBlock) {
            return true;
        } else if (block instanceof NetherWartBlock) {
            return true;
        }
        return false;
    }
}
