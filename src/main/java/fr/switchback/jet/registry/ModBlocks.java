package fr.switchback.jet.registry;

import fr.switchback.jet.block.AngelBlock;
import fr.switchback.jet.block.FertilizedDirtBlock;
import fr.switchback.jet.block.MobGlassBlock;
import fr.switchback.jet.block.PlayerGlassBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.switchback.jet.util.References.MODID;

public class ModBlocks {
    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> FERTILIZED_DIRT_BLOCK = BLOCKS.register("fertilized_dirt", FertilizedDirtBlock::new);

    public static final DeferredBlock<Block> ANGEL_BLOCK = BLOCKS.register("angel_block", AngelBlock::new);

    public static final DeferredBlock<Block> PLAYER_GLASS_BLOCK = BLOCKS.register("player_glass", PlayerGlassBlock::new);

    public static final DeferredBlock<Block> MOB_GLASS_BLOCK = BLOCKS.register("mob_glass", MobGlassBlock::new);

    public static void registerBlocks(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
