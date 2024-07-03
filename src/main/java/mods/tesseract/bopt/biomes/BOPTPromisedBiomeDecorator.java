package mods.tesseract.bopt.biomes;

import biomesoplenty.api.content.BOPCBlocks;
import mods.tesseract.bopt.BOPTBlocks;
import mods.tesseract.bopt.world.WorldGenMossySkystone;
import mods.tesseract.bopt.world.WorldGenPromisedFlora;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BOPTPromisedBiomeDecorator extends BiomeDecorator {
    public BiomeGenBase biome;
    public static WorldGenerator pink = new WorldGenPromisedFlora(BOPCBlocks.flowers, 6);
    public static WorldGenerator rainbow = new WorldGenPromisedFlora(BOPTBlocks.rainbowFlower, 0);
    public static WorldGenerator blueMilks = new WorldGenPromisedFlora(BOPCBlocks.mushrooms, 2);
    public static WorldGenerator toadstools = new WorldGenPromisedFlora(BOPCBlocks.mushrooms, 0);
    public static WorldGenerator portobellos = new WorldGenPromisedFlora(BOPCBlocks.mushrooms, 1);
    public static WorldGenerator waterLakes = new WorldGenLakes(Blocks.water);
    public static WorldGenerator mossy = new WorldGenMossySkystone(24);
    public int pinkFlowersPerChunk;
    public int rainbowflowersPerChunk;
    public int blueMilksPerChunk;
    public int toadstoolsPerChunk;
    public int portobellosPerChunk;
    public int waterLakesPerChunk;
    public boolean generateMossySkystone;

    public BOPTPromisedBiomeDecorator() {
        super();
    }

    @Override
    public void decorateChunk(World worldIn, Random random, BiomeGenBase biome, int x, int z) {
        if (this.currentWorld != null) {
            System.out.println("Already decorating");
        } else {

            this.currentWorld = worldIn;
            this.randomGenerator = random;
            this.chunk_X = x;
            this.chunk_Z = z;
            this.biome = biome;
            this.genDecorations(biome);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biome) {
        int i, j = 0, k, l;
        int i1;

        for (i = 0; i < waterLakesPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(randomGenerator.nextInt(240) + 8);
            waterLakes.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }
        if (randomGenerator.nextInt(10) == 0)
            j = -1;
        for (; j < treesPerChunk; j++) {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            i1 = this.currentWorld.getHeightValue(k, l);
            WorldGenAbstractTree t = biome.func_150567_a(this.randomGenerator);
            t.setScale(1.0D, 1.0D, 1.0D);

            if (t.generate(this.currentWorld, this.randomGenerator, k, i1, l) && currentWorld.getBlock(k, i1 - 1, l) == BOPTBlocks.holyGrass) {
                this.currentWorld.setBlock(k, i1 - 1, l, BOPTBlocks.holyDirt);
            }
        }

        for (i = 0; i < pinkFlowersPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(currentWorld.getHeightValue(randX, randZ) + 32);
            pink.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }

        for (i = 0; i < rainbowflowersPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(currentWorld.getHeightValue(randX, randZ) + 32);
            rainbow.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }

        for (i = 0; i < blueMilksPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(currentWorld.getHeightValue(randX, randZ) + 32);
            blueMilks.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }

        for (i = 0; i < toadstoolsPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(currentWorld.getHeightValue(randX, randZ) + 32);
            toadstools.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }

        for (i = 0; i < portobellosPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(currentWorld.getHeightValue(randX, randZ) + 32);
            portobellos.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }

        for (i = 0; i < grassPerChunk; i++) {
            int randX = chunk_X + randomGenerator.nextInt(16) + 8;
            int randZ = chunk_Z + randomGenerator.nextInt(16) + 8;
            int randY = randomGenerator.nextInt(currentWorld.getHeightValue(randX, randZ) + 32);
            WorldGenerator g = biome.getRandomWorldGenForGrass(randomGenerator);
            g.generate(currentWorld, randomGenerator, randX, randY, randZ);
        }

        if (generateMossySkystone)
            genStandardOre1(15, mossy, 0, 80);
    }
}
