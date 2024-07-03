package mods.tesseract.bopt.biomes;

import mods.tesseract.bopt.BOPTBlocks;

public class BiomeGenPromisedLandPlains extends BOPTPromisedBiome {
    public BiomeGenPromisedLandPlains(int id) {
        super(id);

        var d = (BOPTPromisedBiomeDecorator) theBiomeDecorator;
        d.treesPerChunk = 0;
        d.grassPerChunk = 15;
        d.rainbowflowersPerChunk = 4;
        d.waterLakesPerChunk = 1;
        d.generateMossySkystone = true;
    }
}
