package mods.tesseract.bopt.biomes;

import mods.tesseract.bopt.BOPTBlocks;
import mods.tesseract.bopt.world.WorldGenWaterSpring;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.awt.*;
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

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 7925125;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 7925125;
    }


    @Override
    public void decorate(World world, Random rand, int x, int z) {
        super.decorate(world, rand, x, z);
        worldGeneratorSpring.generate(world, rand, x + rand.nextInt(16), rand.nextInt(60), z  + rand.nextInt(16));
    }

    /**
     * takes temperature, returns color
     */
    @Override
    public int getSkyColorByTemp(float v) {
        v /= 3.0F;

        if (v < -1.0F) {
            v = -1.0F;
        }

        if (v > 1.0F) {
            v = 1.0F;
        }

        return Color.getHSBColor(0.62222224F - v * 0.05F, 0.5F + v * 0.1F, 1.0F).getRGB();
    }
}
