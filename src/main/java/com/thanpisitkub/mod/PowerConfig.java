package com.thanpisitkub.mod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/** Config files: config/thanpisitkub-common.toml (server rules) and config/thanpisitkub-client.toml (visuals). */
public final class PowerConfig {
    private PowerConfig() {
    }

    // ------------------------------------------------------------------ common (gameplay)
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec.BooleanValue REQUIRE_POWER;
    public static final ForgeConfigSpec.IntValue COOLDOWN_IMPACT_SECONDS;
    public static final ForgeConfigSpec.IntValue COOLDOWN_DIVIDE_SECONDS;
    public static final ForgeConfigSpec.DoubleValue DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.BooleanValue INVULNERABLE_WHILE_CASTING;

    // ------------------------------------------------------------------ client (visuals)
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.DoubleValue EFFECT_SCALE;
    public static final ForgeConfigSpec.DoubleValue PARTICLE_DENSITY;
    public static final ForgeConfigSpec.BooleanValue LOCK_MOVEMENT;
    public static final ForgeConfigSpec.BooleanValue THIRD_PERSON;

    static {
        ForgeConfigSpec.Builder c = new ForgeConfigSpec.Builder();
        c.push("power");
        REQUIRE_POWER = c.comment("If true, only players that own the thanpisitkub power can use the keybinds.",
                "(Use the Thanpisitkub Core item or /thanpisitkub power on <player> to give it.)")
                .define("requirePower", true);
        COOLDOWN_IMPACT_SECONDS = c.comment("Cooldown after Galaxy Impact, in seconds.")
                .defineInRange("cooldownGalaxyImpactSeconds", 60, 0, 3600);
        COOLDOWN_DIVIDE_SECONDS = c.comment("Cooldown after Galaxy Divide, in seconds.")
                .defineInRange("cooldownGalaxyDivideSeconds", 60, 0, 3600);
        DAMAGE_MULTIPLIER = c.comment("Multiplier for all move damage (1.0 = defaults in Moves.java).")
                .defineInRange("damageMultiplier", 1.0, 0.0, 1000.0);
        INVULNERABLE_WHILE_CASTING = c.comment("The caster takes no damage while a move is playing.")
                .define("invulnerableWhileCasting", true);
        c.pop();
        COMMON_SPEC = c.build();

        ForgeConfigSpec.Builder v = new ForgeConfigSpec.Builder();
        v.push("visuals");
        EFFECT_SCALE = v.comment("Scales particle size, spawn radius, speed and acceleration.",
                "1.0 = the numbers exactly as exported in the particle files (huge, about 50 blocks wide).",
                "0.25 (default) gives player-sized effects.")
                .defineInRange("effectScale", 0.25, 0.01, 4.0);
        PARTICLE_DENSITY = v.comment("Multiplier for how many particles are spawned (lower it if the game lags).")
                .defineInRange("particleDensity", 1.0, 0.05, 2.0);
        LOCK_MOVEMENT = v.comment("Freeze your own movement/attack input while your move plays.")
                .define("lockMovementWhileCasting", true);
        THIRD_PERSON = v.comment("Switch to third-person view while your move plays.")
                .define("thirdPersonWhileCasting", true);
        v.pop();
        CLIENT_SPEC = v.build();
    }

    public static void register() {
        ModLoadingContext ctx = ModLoadingContext.get();
        ctx.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        ctx.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
    }
}
