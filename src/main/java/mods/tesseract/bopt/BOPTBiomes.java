package mods.tesseract.bopt;

import mods.tesseract.bopt.biomes.BiomeGenPromisedLandForest;
import mods.tesseract.bopt.biomes.BiomeGenPromisedLandPlains;
import mods.tesseract.bopt.biomes.BiomeGenPromisedLandShrub;
import mods.tesseract.bopt.biomes.BiomeGenPromisedLandSwamp;
import net.minecraft.world.biome.BiomeGenBase;

public class BOPTBiomes {
    public static BiomeGenBase promisedLandForest;
    public static BiomeGenBase promisedLandPlains;
    public static BiomeGenBase promisedLandShrub;
    public static BiomeGenBase promisedLandSwamp;

    public static void init() {
        promisedLandForest = new BiomeGenPromisedLandForest(237).setColor(7925125).setBiomeName("Wonderous Woods").setTemperatureRainfall(2.0F, 2.0F).setHeight(new BiomeGenBase.Height(0.1F, 2.0F));
        promisedLandShrub = new BiomeGenPromisedLandShrub(238).setColor(13433204).setBiomeName("Sublime Shrubland").setTemperatureRainfall(2.0F, 2.0F).setHeight(new BiomeGenBase.Height(0.1F, 2.0F));
        promisedLandPlains = new BiomeGenPromisedLandPlains(239).setColor(13433204).setBiomeName("Majestic Meadow").setTemperatureRainfall(2.0F, 2.0F).setHeight(new BiomeGenBase.Height(0.1F, 2.0F));
        promisedLandSwamp = new BiomeGenPromisedLandSwamp(240).setColor(3447145).setBiomeName("Blessed Bog").setTemperatureRainfall(2.0F, 2.0F).setHeight(new BiomeGenBase.Height(0.1F, 2.0F));
    }
}
