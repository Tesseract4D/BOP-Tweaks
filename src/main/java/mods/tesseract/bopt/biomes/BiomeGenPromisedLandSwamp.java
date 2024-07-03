package mods.tesseract.bopt.biomes;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import mods.tesseract.bopt.BOPTBlocks;
import mods.tesseract.bopt.world.WorldGenPromisedWillowTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenPromisedLandSwamp extends BOPTPromisedBiome {
    public WorldGenAbstractTree worldGeneratorTreeSwamp = new WorldGenPromisedWillowTree(Blocks.log, Blocks.leaves, 0, 4, 6, 8, Blocks.leaves, 4);

    public BiomeGenPromisedLandSwamp(int id) {
        super(id);

        var d = (BOPTPromisedBiomeDecorator) theBiomeDecorator;
        d.treesPerChunk = 2;
        d.grassPerChunk = 7;
        d.pinkFlowersPerChunk = 1;
        d.rainbowflowersPerChunk = 1;
        d.blueMilksPerChunk = 3;
        d.toadstoolsPerChunk = 2;
        d.portobellosPerChunk = 1;
        d.waterLakesPerChunk = 10;
        d.generateMossySkystone = true;
    }

    public WorldGenAbstractTree func_150567_a(Random e) {
        return worldGeneratorTreeSwamp;
    }
}
