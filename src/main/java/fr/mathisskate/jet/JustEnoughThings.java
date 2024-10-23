package fr.mathisskate.jet;

import com.mojang.logging.LogUtils;
import fr.mathisskate.jet.event.FertilizedDirtEvent;
import fr.mathisskate.jet.event.MobEvent;
import fr.mathisskate.jet.registry.ModBlocks;
import fr.mathisskate.jet.registry.ModItems;
import fr.mathisskate.jet.registry.ModTab;
import fr.mathisskate.jet.util.References;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(References.MODID)
public class JustEnoughThings {

    public static final Logger LOGGER = LogUtils.getLogger();

    public JustEnoughThings(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModBlocks.registerBlocks(modEventBus);
        ModItems.registerItems(modEventBus);
        ModTab.registerTab(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.register(FertilizedDirtEvent.class);
        NeoForge.EVENT_BUS.register(MobEvent.class);
    }

    @EventBusSubscriber(modid = References.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOB_GLASS_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PLAYER_GLASS_BLOCK.get(), RenderType.translucent());
        }
    }
}
