package fr.switchback.jet.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.switchback.jet.util.References.MODID;

public class ModDataComponents {

    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = DATA_COMPONENT_TYPES.registerComponentType("coordinates", builder -> builder.persistent(BlockPos.CODEC));

    public static void registerDataComponents(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }

}
