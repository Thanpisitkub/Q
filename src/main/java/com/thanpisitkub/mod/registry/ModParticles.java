package com.thanpisitkub.mod.registry;

import com.thanpisitkub.mod.ThanpisitkubMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * One particle type per supplied particle. Textures come from
 * assets/thanpisitkub/particles/NAME.json (sprite list) and the behaviour from
 * assets/thanpisitkub/bedrock_particles/NAME.json (see client.fx).
 */
public final class ModParticles {
    private ModParticles() {
    }

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ThanpisitkubMod.MOD_ID);

    public static final String[] NAMES = {
            "garp_galaxydivide1", "garp_galaxydivide2",
            "garp_rubi2", "garp_rubi2_4",
            "garp_skills4_1",
            "garp_ult1_1", "garp_ult1_1_2", "garp_ult1_2", "garp_ult1_2_2", "garp_ult1_3_3",
            "garp_ult1_4_2", "garp_ult1_6", "garp_ult1_6_2", "garp_ult1_8_2",
            "garp_ult4_2", "garp_ult4_2_3", "garp_ult4_5", "garp_ult4_5_2",
            "garp_ult1_10",
            "garp_ult1_10_2",
            "garp_ult1_10_3",
            "garp_ult1_10_4",
            "garp_ult1_10_5",
            "garp_ult1_10_6",
            "garp_ult1_10_7",
            "garp_ult1_10_8",
            "garp_ult1_11",
            "garp_ult1_11_2",
            "garp_ult1_11_2_2",
            "garp_ult1_11_2_3",
            "garp_ult1_11_2_4",
            "garp_ult1_12",
            "garp_ult1_13",
            "garp_ult1_13_2",
            "garp_ult1_13_3",
            "garp_ult1_3",
            "garp_ult1_3_2",
            "garp_ult1_4",
            "garp_ult1_5",
            "garp_ult1_5_2",
            "garp_ult1_8",
            "garp_ult1_9",
            "garp_ult1_9_2",
            "garp_ult4_1",
            "garp_ult4_1_2",
            "garp_ult4_1_3",
            "garp_ult4_1_4",
            "garp_ult4_2_2",
            "garp_ult4_3",
            "garp_ult4_4",
            "garp_ult4_4_2",
            "garp_ult4_6",
    };

    private static final Map<String, RegistryObject<SimpleParticleType>> BY_NAME = new LinkedHashMap<>();

    static {
        for (String name : NAMES) {
            BY_NAME.put(name, PARTICLES.register(name, () -> new SimpleParticleType(false)));
        }
    }

    public static RegistryObject<SimpleParticleType> get(String name) {
        return BY_NAME.get(name);
    }

    public static Map<String, RegistryObject<SimpleParticleType>> all() {
        return BY_NAME;
    }
}
