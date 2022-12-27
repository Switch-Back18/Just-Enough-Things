package fr.mathisskate.justenoughthings.registry;

//import fr.mathisskate.justenoughthings.container.ItemCollectorContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.mathisskate.justenoughthings.util.References.MODID;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    /*public static RegistryObject<ContainerType<ItemCollectorContainer>> ITEM_COLLECTOR_CONTAINER = CONTAINERS.register("item_collector_container",
            () -> IForgeContainerType.create((((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.world;
                return new ItemCollectorContainer(windowId, world, pos, inv, inv.player);
            })))
    );*/

    public static void registerContainers() {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
