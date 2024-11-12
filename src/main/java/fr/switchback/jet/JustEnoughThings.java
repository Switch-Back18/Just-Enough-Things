package fr.switchback.jet;

import com.mojang.logging.LogUtils;
import fr.switchback.jet.event.FertilizedDirtEvent;
import fr.switchback.jet.event.MobEvent;
import fr.switchback.jet.registry.ModBlocks;
import fr.switchback.jet.registry.ModDataComponents;
import fr.switchback.jet.registry.ModItems;
import fr.switchback.jet.registry.ModTab;
import fr.switchback.jet.util.References;
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

        ModDataComponents.registerDataComponents(modEventBus);
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
