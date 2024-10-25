package fr.mathisskate.jet.registry;

import fr.mathisskate.jet.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.mathisskate.jet.util.References.MODID;

public class ModItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Block Items
    public static final DeferredItem<BlockItem> FERTILIZED_DIRT_ITEM = ITEMS.registerSimpleBlockItem("fertilized_dirt", ModBlocks.FERTILIZED_DIRT_BLOCK);

    public static final DeferredItem<BlockItem> PLAYER_GLASS_ITEM = ITEMS.registerSimpleBlockItem("player_glass", ModBlocks.PLAYER_GLASS_BLOCK);

    public static final DeferredItem<BlockItem> MOB_GLASS_ITEM = ITEMS.registerSimpleBlockItem("mob_glass", ModBlocks.MOB_GLASS_BLOCK);

    public static final DeferredItem<BlockItem> ANGEL_BLOCK_ITEM = ITEMS.register("angel_block", AngelBlockItem::new);

    //Items
    public static final DeferredItem<Item> GLASS_CUTTER_ITEM = ITEMS.register("glass_cutter", GlassCutterItem::new);

    public static final DeferredItem<Item> GLASS_CUTTER_UNBREAKABLE_ITEM = ITEMS.register("glass_cutter_unbreakable", GlassCutterUnbreakableItem::new);

    public static final DeferredItem<Item> ENDER_BUCKET_ITEM = ITEMS.register("ender_bucket", EnderBucketItem::new);

    public static final DeferredItem<Item> ROPE_ITEM = ITEMS.register("rope", RopeItem::new);

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
