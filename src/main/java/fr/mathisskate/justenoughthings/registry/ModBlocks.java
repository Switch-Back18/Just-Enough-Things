package fr.mathisskate.justenoughthings.registry;

import fr.mathisskate.justenoughthings.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static fr.mathisskate.justenoughthings.util.References.MODID;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> FERTILIZED_DIRT_BLOCK = BLOCKS.register("fertilized_dirt", FertilizedDirtBlock::new);

    public static final RegistryObject<Block> ANGEL_BLOCK = BLOCKS.register("angel_block", AngelBlock::new);

    public static final RegistryObject<Block> PLAYER_GLASS_BLOCK = BLOCKS.register("player_glass", PlayerGlassBlock::new);

    public static final RegistryObject<Block> MOB_GLASS_BLOCK = BLOCKS.register("mob_glass", MobGlassBlock::new);

    /*public static final RegistryObject<Block> ITEM_COLLECTOR_BLOCK = BLOCKS.register("item_collector", ItemCollectorBlock::new);

    public static final RegistryObject<Block> SPAWNER_BLOCK = BLOCKS.register("spawner", SpawnerBlock::new);*/

    public static void registerBlocks() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
