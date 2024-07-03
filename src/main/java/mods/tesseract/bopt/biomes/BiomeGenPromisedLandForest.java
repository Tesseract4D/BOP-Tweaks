package mods.tesseract.bopt.biomes;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;
import mods.tesseract.bopt.BOPTBlocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenPromisedLandForest extends BOPTPromisedBiome {
    public WorldGenAbstractTree worldGeneratorTreeMagic = new WorldGenOriginalTree(BOPCBlocks.logs2, BOPCBlocks.leaves1, 1, 2, false, 5, 3, false);
    public WorldGenAbstractTree worldGeneratorTreeHoly = new WorldGenBOPTaiga2(BOPCBlocks.logs2, BOPCBlocks.leaves2, 0, 2, false, 10, 10, 5, 4);

    public BiomeGenPromisedLandForest(int id) {
        super(id);

        var d = (BOPTPromisedBiomeDecorator) theBiomeDecorator;
        d.treesPerChunk = 5;
        d.grassPerChunk = 7;
        d.pinkFlowersPerChunk = 3;
        d.rainbowflowersPerChunk = 2;
        d.blueMilksPerChunk = 1;
        d.waterLakesPerChunk = 1;
        d.generateMossySkystone = true;
    }

    public WorldGenAbstractTree func_150567_a(Random e) {
        return e.nextInt(8) == 0 ? worldGeneratorTreeMagic : worldGeneratorTreeHoly;
    }
}
