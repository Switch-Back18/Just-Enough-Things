package fr.mathisskate.justenoughthings;

import fr.mathisskate.justenoughthings.util.References;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC = BUILDER.build();

    public static ForgeConfigSpec.ConfigValue<Boolean> fertilizedDirtEnable;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        BUILDER.push("Config of Just Enough Things");
        fertilizedDirtEnable = BUILDER.comment("If you want to enable fertilized dirt (default: true)").define("Fertilized Dirt", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
