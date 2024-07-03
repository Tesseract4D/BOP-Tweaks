package mods.tesseract.bopt.biomes;

import mods.tesseract.bopt.BOPTBlocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class BOPTPromisedBiome extends BiomeGenBase {
    public WorldGenerator worldGeneratorGrassHoly = new WorldGenTallGrass(BOPTBlocks.holyTallgrass, 0);

    public BOPTPromisedBiome(int id) {
        super(id);
        this.topBlock = BOPTBlocks.holyGrass;
        this.fillerBlock = BOPTBlocks.holyDirt;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    }

    public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_) {
        return worldGeneratorGrassHoly;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BOPTPromisedBiomeDecorator();
    }
}
