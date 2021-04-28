package malekire.devilrycraft.common;

import malekire.devilrycraft.Devilrycraft;
import malekire.devilrycraft.generation.crystal_generation.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

public class DevilryOreGeneration {
    public static final int SPAWN_RATE = 5;
    public static final OreFeatureConfig ORE_FEATURE_CONFIG = new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Blocks.WHITE_WOOL.getDefaultState(), 9);// number of veins per chunk
    public static final RangeDecoratorConfig RANGE_DECORATOR_CONFIG = new RangeDecoratorConfig(0, 0, 64);

    public static ArrayList<FeatureGroup> features = new ArrayList<>();


    public static final Feature<OreFeatureConfig> VIS_CRYSTAL = new VisCrystalGenerationFeature(OreFeatureConfig.CODEC);
    public static final Feature<OreFeatureConfig> TAINT_CRYSTAL = new TaintCrystalGenerationFeature(OreFeatureConfig.CODEC);
    public static final Feature<OreFeatureConfig> EARTH_CRYSTAL = new EarthCrystalGenerationFeature(OreFeatureConfig.CODEC);
    public static final Feature<OreFeatureConfig> AIR_CRYSTAL = new AirCrystalGenerationFeature(OreFeatureConfig.CODEC);
    public static final Feature<OreFeatureConfig> FIRE_CRYSTAL = new FireCrystalGenerationFeature(OreFeatureConfig.CODEC);
    public static final Feature<OreFeatureConfig> WATER_CRYSTAL = new WaterCrystalGenerationFeature(OreFeatureConfig.CODEC);




    public static final ConfiguredFeature<?, ?> VIS_CRYSTAL_CONFIGURED = VIS_CRYSTAL.configure(ORE_FEATURE_CONFIG).decorate(Decorator.RANGE.configure(RANGE_DECORATOR_CONFIG).repeat(SPAWN_RATE));
    public static final ConfiguredFeature<?, ?> TAINT_CRYSTAL_CONFIGURED = TAINT_CRYSTAL.configure(ORE_FEATURE_CONFIG).decorate(Decorator.RANGE.configure(RANGE_DECORATOR_CONFIG).repeat(SPAWN_RATE));
    public static final ConfiguredFeature<?, ?> EARTH_CRYSTAL_CONFIGURED = EARTH_CRYSTAL.configure(ORE_FEATURE_CONFIG).decorate(Decorator.RANGE.configure(RANGE_DECORATOR_CONFIG).repeat(SPAWN_RATE));
    public static final ConfiguredFeature<?, ?> AIR_CRYSTAL_CONFIGURED = AIR_CRYSTAL.configure(ORE_FEATURE_CONFIG).decorate(Decorator.RANGE.configure(RANGE_DECORATOR_CONFIG).repeat(SPAWN_RATE));
    public static final ConfiguredFeature<?, ?> FIRE_CRYSTAL_CONFIGURED = FIRE_CRYSTAL.configure(ORE_FEATURE_CONFIG).decorate(Decorator.RANGE.configure(RANGE_DECORATOR_CONFIG).repeat(SPAWN_RATE));
    public static final ConfiguredFeature<?, ?> WATER_CRYSTAL_CONFIGURED = WATER_CRYSTAL.configure(ORE_FEATURE_CONFIG).decorate(Decorator.RANGE.configure(RANGE_DECORATOR_CONFIG).repeat(SPAWN_RATE));
    static {
        features.add(new FeatureGroup(VIS_CRYSTAL_CONFIGURED, VIS_CRYSTAL, new Identifier(Devilrycraft.MOD_ID, "vis_crystal")));
        features.add(new FeatureGroup(TAINT_CRYSTAL_CONFIGURED, TAINT_CRYSTAL, new Identifier(Devilrycraft.MOD_ID, "taint_crystal")));
        features.add(new FeatureGroup(EARTH_CRYSTAL_CONFIGURED, EARTH_CRYSTAL, new Identifier(Devilrycraft.MOD_ID, "earth_crystal")));
        features.add(new FeatureGroup(AIR_CRYSTAL_CONFIGURED, AIR_CRYSTAL, new Identifier(Devilrycraft.MOD_ID, "air_crystal")));
        features.add(new FeatureGroup(FIRE_CRYSTAL_CONFIGURED, FIRE_CRYSTAL, new Identifier(Devilrycraft.MOD_ID, "fire_crystal")));
        features.add(new FeatureGroup(WATER_CRYSTAL_CONFIGURED, WATER_CRYSTAL, new Identifier(Devilrycraft.MOD_ID, "water_crystal")));

    }


    public static void RegisterFeatures() {
        for(FeatureGroup featureGroup : features)
        {
            RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                    featureGroup.name);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, configuredFeatureRegistryKey.getValue(), featureGroup.configuredFeature);
            Registry.register(Registry.FEATURE, featureGroup.name, featureGroup.feature);
            BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.RAW_GENERATION, configuredFeatureRegistryKey);
        }
    }



}
