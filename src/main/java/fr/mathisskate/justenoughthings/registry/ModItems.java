package fr.mathisskate.justenoughthings.registry;

import fr.mathisskate.justenoughthings.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static fr.mathisskate.justenoughthings.util.References.MODID;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Block Items
    public static final RegistryObject<Item> FERTILIZED_DIRT_ITEM = ITEMS.register("fertilized_dirt",
            () -> new BaseBlockItem(ModBlocks.FERTILIZED_DIRT_BLOCK.get()));

    public static final RegistryObject<Item> PLAYER_GLASS_ITEM = ITEMS.register("player_glass",
            () -> new BaseBlockItem(ModBlocks.PLAYER_GLASS_BLOCK.get()));

    public static final RegistryObject<Item> MOB_GLASS_ITEM = ITEMS.register("mob_glass",
            () -> new BaseBlockItem(ModBlocks.MOB_GLASS_BLOCK.get()));

    public static final RegistryObject<Item> ANGEL_BLOCK_ITEM = ITEMS.register("angel_block", AngelBlockItem::new);


    //Items
    public static final RegistryObject<Item> GLASS_CUTTER_ITEM = ITEMS.register("glass_cutter", GlassCutterItem::new);

    public static final RegistryObject<Item> GLASS_CUTTER_UNBREAKABLE_ITEM = ITEMS.register("glass_cutter_unbreakable", GlassCutterUnbreakableItem::new);
    
    public static void registerItems() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
