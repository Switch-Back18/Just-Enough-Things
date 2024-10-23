package fr.mathisskate.jet.util;

import fr.mathisskate.jet.JustEnoughThings;
import fr.mathisskate.jet.registry.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;


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

    public static boolean canGrow(Block block) {
        ItemStack item = new ItemStack(block);
        return item.is(Tags.Items.CROPS) || item.is(Tags.Items.SEEDS) || item.is(Tags.Items.MUSHROOMS) || block instanceof SugarCaneBlock || block instanceof SaplingBlock;
    }

    public static boolean isPlantable(ItemStack item) {
        if(item.is(Items.SUGAR_CANE))
            return false;
        return item.is(Tags.Items.CROPS) || item.is(Tags.Items.SEEDS);
    }
}
