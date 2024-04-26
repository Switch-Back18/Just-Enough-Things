package fr.mathisskate.justenoughthings;

import com.mojang.logging.LogUtils;
import fr.mathisskate.justenoughthings.event.FertilizedDirtEvent;
import fr.mathisskate.justenoughthings.event.MobEvent;
import fr.mathisskate.justenoughthings.registry.ModBlocks;
import fr.mathisskate.justenoughthings.registry.ModItems;
import fr.mathisskate.justenoughthings.registry.ModTab;
import fr.mathisskate.justenoughthings.util.References;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(References.MODID)
public class JustEnoughThings {


    private static final Logger LOGGER = LogUtils.getLogger();

    public JustEnoughThings() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "just_enough_things_common.toml");

        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModTab.registerTab();

        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new FertilizedDirtEvent());
        MinecraftForge.EVENT_BUS.register(new MobEvent());
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == ModTab.JET_TAB.getKey() ) {
            event.accept(ModItems.FERTILIZED_DIRT_ITEM.get());
            event.accept(ModItems.ANGEL_BLOCK_ITEM.get());
            event.accept(ModItems.GLASS_CUTTER_ITEM.get());
            event.accept(ModItems.GLASS_CUTTER_UNBREAKABLE_ITEM.get());
            event.accept(ModItems.PLAYER_GLASS_ITEM.get());
            event.accept(ModItems.MOB_GLASS_ITEM.get());
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOB_GLASS_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PLAYER_GLASS_BLOCK.get(), RenderType.translucent());
        }
    }
}
