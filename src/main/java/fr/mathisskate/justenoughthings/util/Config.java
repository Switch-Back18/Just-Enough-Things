package fr.mathisskate.justenoughthings.util;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    //public static final ForgeConfigSpec.ConfigValue<Boolean> fertilizedDirtEnable;

    static {
        BUILDER.push("Config of Just Enough Things");
        //fertilizedDirtEnable = BUILDER.comment("If you want to enable fertilized dirt (default: true)").define("Fertilized Dirt", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
