package fr.mathisskate.justenoughthings.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.mathisskate.justenoughthings.util.References.MODID;

public class ModTilesEntities {
    public static DeferredRegister<TileEntityType<?>> TILES_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);

    /*public static final RegistryObject<TileEntityType<ItemCollectorTile>> ITEM_COLLECTOR_TILE = TILES_ENTITIES.register("item_collector", () ->
            TileEntityType.Builder.create(ItemCollectorTile::new, ModBlocks.ITEM_COLLECTOR_BLOCK.get()).build(null)
    );

    public static final RegistryObject<TileEntityType<SpawnerTile>> SPAWNER_TILE = TILES_ENTITIES.register("spawner", () ->
            TileEntityType.Builder.create(SpawnerTile::new, ModBlocks.SPAWNER_BLOCK.get()).build(null)
    );*/
    public static void registerTilesEntities() {
        TILES_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
