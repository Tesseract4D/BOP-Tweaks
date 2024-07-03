package mods.tesseract.bopt.world;

import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.common.eventhandler.Event;
import mods.tesseract.bopt.BOPTBlocks;
import mods.tesseract.bopt.world.noise.NoiseOctaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import java.util.List;
import java.util.Random;

public class ChunkProviderPromised implements IChunkProvider {
    private final Random endRNG;
    private final NoiseOctaves field_912_k;
    private final NoiseOctaves field_911_l;
    private final NoiseOctaves field_910_m;
    public NoiseOctaves field_922_a;
    public NoiseOctaves field_921_b;
    private final World endWorld;
    private double[] densities;

    private BiomeGenBase[] biomesForGeneration;
    double[] field_4185_d;
    double[] field_4184_e;
    double[] field_4183_f;
    double[] field_4182_g;
    double[] field_4181_h;

    public ChunkProviderPromised(World world, long seed) {
        endWorld = world;
        endRNG = new Random(seed);
        field_912_k = new NoiseOctaves(endRNG, 16);
        field_911_l = new NoiseOctaves(endRNG, 16);
        field_910_m = new NoiseOctaves(endRNG, 8);
        field_922_a = new NoiseOctaves(endRNG, 10);
        field_921_b = new NoiseOctaves(endRNG, 16);

        //NoiseGeneratorOctaves[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5};
        //noiseGens = TerrainGen.getModdedNoiseGenerators(world, this.endRNG, noiseGens);
        //this.noiseGen1 = noiseGens[0];
        //this.noiseGen2 = noiseGens[1];
        //this.noiseGen3 = noiseGens[2];
        //this.noiseGen4 = noiseGens[3];
        //this.noiseGen5 = noiseGens[4];
    }

    public void generateTerrain(int par1, int par2, Block[] b) {
        byte var5 = 2;
        int var6 = var5 + 1;
        byte var7 = 33;
        int var8 = var5 + 1;
        densities = this.initializeNoiseField(densities, par1 * var5, 0, par2 * var5, var6, var7, var8);

        for (int var9 = 0; var9 < var5; ++var9) {
            for (int var10 = 0; var10 < var5; ++var10) {
                for (int var11 = 0; var11 < 32; ++var11) {
                    double var12 = 0.25D;
                    double var14 = densities[((var9) * var8 + var10) * var7 + var11];
                    int i = ((var9) * var8 + var10 + 1) * var7;
                    double var16 = densities[i + var11];
                    int j = ((var9 + 1) * var8 + var10) * var7;
                    double var18 = densities[j + var11];
                    int k = ((var9 + 1) * var8 + var10 + 1) * var7;
                    double var20 = densities[k + var11];
                    double var22 = (densities[((var9) * var8 + var10) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (densities[i + var11 + 1] - var16) * var12;
                    double var26 = (densities[j + var11 + 1] - var18) * var12;
                    double var28 = (densities[k + var11 + 1] - var20) * var12;

                    for (int var30 = 0; var30 < 4; ++var30) {
                        double var31 = 0.125D;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for (int var41 = 0; var41 < 8; ++var41) {
                            int var42 = var41 + var9 * 8 << 11 | var10 * 8 << 7 | var11 * 4 + var30;
                            short var43 = 128;
                            double var44 = 0.125D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int var50 = 0; var50 < 8; ++var50) {
                                Block var51 = Blocks.air;

                                if (var46 > 0.0D) {
                                    var51 = BOPTBlocks.holyStone;
                                }

                                b[var42] = var51;
                                var42 += var43;
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        var14 += var22;
                        var16 += var24;
                        var18 += var26;
                        var20 += var28;
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int par1, int par2, Block[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        byte var98 = 63;

        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        for (int var5 = 0; var5 < 16; ++var5) {
            for (int var6 = 0; var6 < 16; ++var6) {
                BiomeGenBase var99 = par4ArrayOfBiomeGenBase[var6 + var5 * 16];
                byte var7 = 1;
                int var8 = -1;
                Block var9 = var99.topBlock;
                Block var10 = var99.fillerBlock;

                for (int var11 = 127; var11 >= 0; --var11) {
                    int var12 = (var6 * 16 + var5) * 128 + var11;
                    Block var13 = par3ArrayOfByte[var12];

                    if (var13 == Blocks.air) {
                        var8 = -1;
                    } else if (var13 == BOPTBlocks.holyStone) {
                        if (var8 == -1) {
                            if (var7 <= 0) {
                                var9 = Blocks.air;
                                var10 = BOPTBlocks.holyStone;
                            } else if (var11 >= var98 - 4 && var11 <= var98 + 1) {
                                var9 = var99.topBlock;
                                var10 = var99.fillerBlock;
                            }

                            if (var11 < var98 && var9 == Blocks.air) {
                                var9 = Blocks.water;
                            }

                            var8 = var7;

                            if (var11 >= 0) {
                                par3ArrayOfByte[var12] = var9;
                            } else {
                                par3ArrayOfByte[var12] = var10;
                            }
                        } else if (var8 > 0) {
                            --var8;
                            par3ArrayOfByte[var12] = var10;
                        }
                    }
                }
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    @Override
    public Chunk loadChunk(int par1, int par2) {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    @Override
    public Chunk provideChunk(int par1, int par2) {
        endRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        Block[] var3 = new Block[32768];
        biomesForGeneration = endWorld.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.generateTerrain(par1, par2, var3);
        this.replaceBlocksForBiome(par1, par2, var3, biomesForGeneration);
        Chunk var4 = new Chunk(endWorld, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6) {
            var5[var6] = (byte) biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */

    private double[] initializeNoiseField(double[] ad, int i, int j, int k, int l, int i1, int j1) {
        if (ad == null) {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        field_4182_g = field_922_a.generateNoiseOctaves(field_4182_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        field_4181_h = field_921_b.generateNoiseOctaves(field_4181_h, i, k, l, j1, 200D, 200D, 0.5D);
        d *= 2D;
        field_4185_d = field_910_m.generateNoiseOctaves(field_4185_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_4184_e = field_912_k.generateNoiseOctaves(field_4184_e, i, j, k, l, i1, j1, d, d1, d);
        field_4183_f = field_911_l.generateNoiseOctaves(field_4183_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / l;
        for (int j2 = 0; j2 < l; j2++) {
            int k2 = j2 * i2 + i2 / 2;
            for (int l2 = 0; l2 < j1; l2++) {
                int i3 = l2 * i2 + i2 / 2;
                double d3;
                d3 = 0.5D;
                double d4 = 1.0D - d3;
                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (field_4182_g[l1] + 256D) / 512D;
                d5 *= d4;
                if (d5 > 1.0D) {
                    d5 = 1.0D;
                }
                double d6 = field_4181_h[l1] / 8000D;
                if (d6 < 0.0D) {
                    d6 = -d6 * 0.29999999999999999D;
                }
                d6 = d6 * 3D - 2D;
                if (d6 > 1.0D) {
                    d6 = 1.0D;
                }
                d6 /= 8D;
                d6 = 0.0D;
                if (d5 < 0.0D) {
                    d5 = 0.0D;
                }
                d5 += 0.5D;
                d6 = (d6 * i1) / 16D;
                l1++;
                double d7 = i1 / 2D;
                for (int j3 = 0; j3 < i1; j3++) {
                    double d8 = 0.0D;
                    double d9 = ((j3 - d7) * 8D) / d5;
                    if (d9 < 0.0D) {
                        d9 *= -1D;
                    }
                    double d10 = field_4184_e[k1] / 512D;
                    double d11 = field_4183_f[k1] / 512D;
                    double d12 = (field_4185_d[k1] / 10D + 1.0D) / 2D;
                    if (d12 < 0.0D) {
                        d8 = d10;
                    } else if (d12 > 1.0D) {
                        d8 = d11;
                    } else {
                        d8 = d10 + (d11 - d10) * d12;
                    }
                    d8 -= 8D;
                    int k3 = 32;
                    if (j3 > i1 - k3) {
                        double d13 = (j3 - (i1 - k3)) / (k3 - 1.0F);
                        d8 = d8 * (1.0D - d13) + -30D * d13;
                    }
                    k3 = 8;
                    if (j3 < k3) {
                        double d14 = (k3 - j3) / (k3 - 1.0F);
                        d8 = d8 * (1.0D - d14) + -30D * d14;
                    }
                    ad[k1] = d8;
                    k1++;
                }

            }

        }

        return ad;
    }

	 /*
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.noisefield;

        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double var8 = 684.412D;
        double var10 = 684.412D;
        this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        var8 *= 2.0D;
        this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
        this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < par5; ++var14)
        {
            for (int var15 = 0; var15 < par7; ++var15)
            {
                double var16 = (this.noiseData4[var13] + 256.0D) / 512.0D;

                if (var16 > 1.0D)
                {
                    var16 = 1.0D;
                }

                double var18 = this.noiseData5[var13] / 8000.0D;

                if (var18 < 0.0D)
                {
                    var18 = -var18 * 0.3D;
                }

                var18 = var18 * 3.0D - 2.0D;
                float var20 = (float)(var14 + par2 - 0) / 1.0F;
                float var21 = (float)(var15 + par4 - 0) / 1.0F;
                float var22 = 100.0F - MathHelper.sqrt_float(var20 * var20 + var21 * var21) * 8.0F;

                if (var22 > 80.0F)
                {
                    var22 = 80.0F;
                }

                if (var22 < -100.0F)
                {
                    var22 = -100.0F;
                }

                if (var18 > 1.0D)
                {
                    var18 = 1.0D;
                }

                var18 /= 8.0D;
                var18 = 0.0D;

                if (var16 < 0.0D)
                {
                    var16 = 0.0D;
                }

                var16 += 0.5D;
                var18 = var18 * (double)par6 / 16.0D;
                ++var13;
                double var23 = (double)par6 / 2.0D;

                for (int var25 = 0; var25 < par6; ++var25)
                {
                    double var26 = 0.0D;
                    double var28 = ((double)var25 - var23) * 8.0D / var16;

                    if (var28 < 0.0D)
                    {
                        var28 *= -1.0D;
                    }

                    double var30 = this.noiseData2[var12] / 512.0D;
                    double var32 = this.noiseData3[var12] / 512.0D;
                    double var34 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var34 < 0.0D)
                    {
                        var26 = var30;
                    }
                    else if (var34 > 1.0D)
                    {
                        var26 = var32;
                    }
                    else
                    {
                        var26 = var30 + (var32 - var30) * var34;
                    }

                    var26 -= 8.0D;
                    var26 += (double)var22;
                    byte var36 = 2;
                    double var37;

                    if (var25 > par6 / 2 - var36)
                    {
                        var37 = (double)((float)(var25 - (par6 / 2 - var36)) / 64.0F);

                        if (var37 < 0.0D)
                        {
                            var37 = 0.0D;
                        }

                        if (var37 > 1.0D)
                        {
                            var37 = 1.0D;
                        }

                        var26 = var26 * (1.0D - var37) + -3000.0D * var37;
                    }

                    var36 = 8;

                    if (var25 < var36)
                    {
                        var37 = (double)((float)(var36 - var25) / ((float)var36 - 1.0F));
                        var26 = var26 * (1.0D - var37) + -30.0D * var37;
                    }

                    par1ArrayOfDouble[var12] = var26;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
    }
	  */

    /**
     * Checks to see if a chunk exists at x, y
     */
    @Override
    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        BlockSand.fallInstantly = true;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, endWorld, endWorld.rand, par2, par3, false));

        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = endWorld.getBiomeGenForCoords(var4 + 16, var5 + 16);

        for (int a = 0; a < 25; ++a) {
            int x = var4 + endWorld.rand.nextInt(16);
            int y = endWorld.rand.nextInt(30) + 30;
            int z = var5 + endWorld.rand.nextInt(16);
            Block b = endWorld.getBlock(x, y, z);

            if (b == BOPTBlocks.holyStone) {
                endWorld.setBlock(x, y, z, BOPCBlocks.gemOre, 0, 2);
            }
        }

        var6.decorate(endWorld, endWorld.rand, var4, var5);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, endWorld, endWorld.rand, par2, par3, false));

        BlockSand.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    /**
     * Unloads the 100 oldest chunks from memory, due to a bug with chunkSet.add() never being called it thinks the list
     * is always empty and will not remove any chunks.
     */

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean unload100OldestChunks() {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    @Override
    public boolean canSave() {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    @Override
    public String makeString() {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
        BiomeGenBase var5 = endWorld.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    @Override
    public ChunkPosition func_147416_a(World worldIn, String structureName, int x, int y, int z) //getNearestStructurePos
    {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int par1, int par2) {
    }

    @Override
    public void saveExtraData() {
    }
}
