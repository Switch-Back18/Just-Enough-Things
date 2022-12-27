package fr.mathisskate.justenoughthings;

import fr.mathisskate.justenoughthings.event.FertilizedDirtEvent;
import fr.mathisskate.justenoughthings.event.MobEvent;
import fr.mathisskate.justenoughthings.registry.ModBlocks;
import fr.mathisskate.justenoughthings.registry.ModItems;
import fr.mathisskate.justenoughthings.util.Config;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("justenoughthings")
public class JustEnoughThingsMain {
    private static final Logger LOGGER = LogManager.getLogger();

    public JustEnoughThingsMain() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "just_enough_things_common.toml");

        ModBlocks.registerBlocks();
        ModItems.registerItems();
        //ModTilesEntities.registerTilesEntities();
        //ModContainers.registerContainers();
    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new FertilizedDirtEvent());
        MinecraftForge.EVENT_BUS.register(new MobEvent());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ModBlocks.BLOCKS.getEntries().forEach(b -> RenderTypeLookup.setRenderLayer(b.get(), RenderType.getCutout()));
        //ScreenManager.registerFactory(ModContainers.ITEM_COLLECTOR_CONTAINER.get(), ItemCollectorScreen::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }
}
