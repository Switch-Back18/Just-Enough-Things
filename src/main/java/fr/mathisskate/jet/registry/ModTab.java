package fr.mathisskate.jet.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.mathisskate.jet.util.References.MODID;


public class ModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> JET_TAB = CREATIVE_MODE_TABS.register("jet", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.jet"))
            .icon(() -> ModItems.ANGEL_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.ANGEL_BLOCK_ITEM);
                output.accept(ModItems.FERTILIZED_DIRT_ITEM);
                output.accept(ModItems.GLASS_CUTTER_ITEM);
                output.accept(ModItems.GLASS_CUTTER_UNBREAKABLE_ITEM);
                output.accept(ModItems.PLAYER_GLASS_ITEM);
                output.accept(ModItems.MOB_GLASS_ITEM);
                //output.accept(ModItems.BOUNCE_PAD_ITEM);
            }).build());

    public static void registerTab(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
