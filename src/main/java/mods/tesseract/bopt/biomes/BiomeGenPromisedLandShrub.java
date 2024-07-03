package mods.tesseract.bopt.biomes;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeGenPromisedLandShrub extends BOPTPromisedBiome {
    public WorldGenAbstractTree worldGeneratorShrub = new WorldGenShrub(3, 0);

    public BiomeGenPromisedLandShrub(int id) {
        super(id);

        var d = (BOPTPromisedBiomeDecorator) theBiomeDecorator;
        d.treesPerChunk = 20;
        d.grassPerChunk = 5;
        d.rainbowflowersPerChunk = 2;
        d.generateMossySkystone = true;
    }

    public WorldGenAbstractTree func_150567_a(Random e) {
        return worldGeneratorShrub;
    }
}
