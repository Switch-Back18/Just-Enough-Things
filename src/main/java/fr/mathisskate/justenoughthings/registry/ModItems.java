package fr.mathisskate.justenoughthings.registry;

import fr.mathisskate.justenoughthings.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

    /*public static final RegistryObject<Item> ITEM_COLLECTOR_ITEM = ITEMS.register("item_collector",
        () -> new BaseBlockItem(ModBlocks.ITEM_COLLECTOR_BLOCK.get()));

    public static final RegistryObject<Item> SPAWNER_ITEM = ITEMS.register("spawner",
            () -> new BaseBlockItem(ModBlocks.SPAWNER_BLOCK.get()));*/

    public static final RegistryObject<Item> ANGEL_BLOCK_ITEM = ITEMS.register("angel_block", AngelBlockItem::new);


    //Items
    public static final RegistryObject<Item> GLASS_CUTTER_ITEM = ITEMS.register("glass_cutter", GlassCutterItem::new);

    public static final RegistryObject<Item> GLASS_CUTTER_UNBREAKABLE_ITEM = ITEMS.register("glass_cutter_unbreakable", GlassCutterUnbreakableItem::new);

    public static final RegistryObject<Item> DARK_STEEL_INGOT = ITEMS.register("darksteel_ingot", DarkSteelIngot::new);

    public static final RegistryObject<Item> THE_ENDER = ITEMS.register("the_ender", TheEnderItem::new);


    //public static final RegistryObject<Item> FILTER_ITEM = ITEMS.register("filter_item", FilterItem::new);

    public static void registerItems() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
