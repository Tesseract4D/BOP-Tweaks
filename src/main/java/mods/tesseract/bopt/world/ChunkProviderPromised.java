package mods.tesseract.bopt.world;

import java.util.List;
import java.util.Random;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.BOPBlock;
import biomesoplenty.common.core.BOPBlocks;
import cpw.mods.fml.common.eventhandler.Event;
import mods.tesseract.bopt.BOPTBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkProviderPromised implements IChunkProvider {
    private Random endRNG;

    private NoiseGeneratorOctaves field_912_k;

    private NoiseGeneratorOctaves field_911_l;

    private NoiseGeneratorOctaves field_910_m;

    public NoiseGeneratorOctaves field_922_a;

    public NoiseGeneratorOctaves field_921_b;

    private World endWorld;

    private double[] densities;

    private BiomeGenBase[] biomesForGeneration;

    double[] field_4185_d;

    double[] field_4184_e;

    double[] field_4183_f;

    double[] field_4182_g;

    double[] field_4181_h;

    int[][] field_73203_h = new int[32][32];

    public ChunkProviderPromised(World par1World, long par2) {
        this.endWorld = par1World;
        this.endRNG = new Random(par2);
        this.field_912_k = new NoiseGeneratorOctaves(this.endRNG, 16);
        this.field_911_l = new NoiseGeneratorOctaves(this.endRNG, 16);
        this.field_910_m = new NoiseGeneratorOctaves(this.endRNG, 8);
        this.field_922_a = new NoiseGeneratorOctaves(this.endRNG, 10);
        this.field_921_b = new NoiseGeneratorOctaves(this.endRNG, 16);
    }

    public void generateTerrain(int par1, int par2, Block[] par3, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        byte var5 = 2;
        int var6 = var5 + 1;
        byte var7 = 33;
        int var8 = var5 + 1;
        this.densities = initializeNoiseField(this.densities, par1 * var5, 0, par2 * var5, var6, var7, var8);
        for (int var9 = 0; var9 < var5; var9++) {
            for (int var10 = 0; var10 < var5; var10++) {
                for (int var11 = 0; var11 < 32; var11++) {
                    double var12 = 0.25D;
                    double var14 = this.densities[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var16 = this.densities[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var18 = this.densities[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var20 = this.densities[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var22 = (this.densities[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (this.densities[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (this.densities[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (this.densities[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;
                    for (int var30 = 0; var30 < 4; var30++) {
                        double var31 = 0.125D;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;
                        for (int var41 = 0; var41 < 8; var41++) {
                            int var42 = var41 + var9 * 8 << 11 | 0 + var10 * 8 << 7 | var11 * 4 + var30;
                            short var43 = 128;
                            double var44 = 0.125D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;
                            for (int var50 = 0; var50 < 8; var50++) {
                                Block var51=Blocks.air;
                                if (var46 > 0.0D)
                                    var51 = BOPTBlocks.holyStone;
                                par3[var42] = var51;
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

    public void replaceBlocksForBiome(int par1, int par2, Block[] par3, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        byte var98 = 63;
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3, par4ArrayOfBiomeGenBase);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY)
            return;
        for (int var5 = 0; var5 < 16; var5++) {
            for (int var6 = 0; var6 < 16; var6++) {
                BiomeGenBase var99 = par4ArrayOfBiomeGenBase[var6 + var5 * 16];
                byte var7 = 1;
                int var8 = -1;
                Block var9 = var99.topBlock;
                Block var10 = var99.fillerBlock;
                for (int var11 = 127; var11 >= 0; var11--) {
                    int var12 = (var6 * 16 + var5) * 128 + var11;
                    Block var13 = par3[var12];
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
                            if (var11 < var98 && var9 == Blocks.air)
                                var9 = Blocks.water;
                            var8 = var7;
                            if (var11 >= 0) {
                                par3[var12] = var9;
                            } else {
                                par3[var12] = var10;
                            }
                        } else if (var8 > 0) {
                            var8--;
                            par3[var12] = var10;
                        }
                    }
                }
            }
        }
    }

    public Chunk loadChunk(int par1, int par2) {
        return provideChunk(par1, par2);
    }

    public Chunk provideChunk(int par1, int par2) {
        this.endRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        Block[] var3 = new Block[32768];
        this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        generateTerrain(par1, par2, var3, this.biomesForGeneration);
        replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        Chunk var4 = new Chunk(this.endWorld, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();
        for (int var6 = 0; var6 < var5.length; var6++)
            var5[var6] = (byte)(this.biomesForGeneration[var6]).biomeID;
        var4.generateSkylightMap();
        return var4;
    }

    private double[] initializeNoiseField(double[] ad, int i, int j, int k, int l, int i1, int j1) {
        if (ad == null)
            ad = new double[l * i1 * j1];
        double d = 684.412D;
        double d1 = 684.412D;
        this.field_4182_g = this.field_922_a.generateNoiseOctaves(this.field_4182_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        this.field_4181_h = this.field_921_b.generateNoiseOctaves(this.field_4181_h, i, k, l, j1, 200.0D, 200.0D, 0.5D);
        d *= 2.0D;
        this.field_4185_d = this.field_910_m.generateNoiseOctaves(this.field_4185_d, i, j, k, l, i1, j1, d / 80.0D, d1 / 160.0D, d / 80.0D);
        this.field_4184_e = this.field_912_k.generateNoiseOctaves(this.field_4184_e, i, j, k, l, i1, j1, d, d1, d);
        this.field_4183_f = this.field_911_l.generateNoiseOctaves(this.field_4183_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / l;
        for (int j2 = 0; j2 < l; j2++) {
            int k2 = j2 * i2 + i2 / 2;
            for (int l2 = 0; l2 < j1; l2++) {
                int i3 = l2 * i2 + i2 / 2;
                double d3 = 0.5D;
                double d4 = 1.0D - d3;
                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (this.field_4182_g[l1] + 256.0D) / 512.0D;
                d5 *= d4;
                if (d5 > 1.0D)
                    d5 = 1.0D;
                double d6 = this.field_4181_h[l1] / 8000.0D;
                if (d6 < 0.0D)
                    d6 = -d6 * 0.3D;
                d6 = d6 * 3.0D - 2.0D;
                if (d6 > 1.0D)
                    d6 = 1.0D;
                d6 /= 8.0D;
                d6 = 0.0D;
                if (d5 < 0.0D)
                    d5 = 0.0D;
                d5 += 0.5D;
                d6 = d6 * i1 / 16.0D;
                l1++;
                double d7 = i1 / 2.0D;
                for (int j3 = 0; j3 < i1; j3++) {
                    double d8 = 0.0D;
                    double d9 = (j3 - d7) * 8.0D / d5;
                    if (d9 < 0.0D)
                        d9 *= -1.0D;
                    double d10 = this.field_4184_e[k1] / 512.0D;
                    double d11 = this.field_4183_f[k1] / 512.0D;
                    double d12 = (this.field_4185_d[k1] / 10.0D + 1.0D) / 2.0D;
                    if (d12 < 0.0D) {
                        d8 = d10;
                    } else if (d12 > 1.0D) {
                        d8 = d11;
                    } else {
                        d8 = d10 + (d11 - d10) * d12;
                    }
                    d8 -= 8.0D;
                    int k3 = 32;
                    if (j3 > i1 - k3) {
                        double d13 = ((j3 - i1 - k3) / (k3 - 1.0F));
                        d8 = d8 * (1.0D - d13) + -30.0D * d13;
                    }
                    k3 = 8;
                    if (j3 < k3) {
                        double d14 = ((k3 - j3) / (k3 - 1.0F));
                        d8 = d8 * (1.0D - d14) + -30.0D * d14;
                    }
                    ad[k1] = d8;
                    k1++;
                }
            }
        }
        return ad;
    }

    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        BlockSand.fallInstantly = true;
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(par1IChunkProvider, this.endWorld, this.endWorld.rand, par2, par3, false));
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.endWorld.getBiomeGenForCoords(var4 + 16, var5 + 16);
        for (int a = 0; a < 25; a++) {
            int x = var4 + this.endWorld.rand.nextInt(16);
            int y = this.endWorld.rand.nextInt(30) + 30;
            int z = var5 + this.endWorld.rand.nextInt(16);
            Block b = this.endWorld.getBlock(x, y, z);
            if (b == BOPTBlocks.holyStone)
                this.endWorld.setBlock(x, y, z, BOPCBlocks.gemOre, 0, 2);
        }
        var6.decorate(this.endWorld, this.endWorld.rand, var4, var5);
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(par1IChunkProvider, this.endWorld, this.endWorld.rand, par2, par3, false));
        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean unload100OldestChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
        BiomeGenBase var5 = this.endWorld.getBiomeGenForCoords(par2, par4);
        return (var5 == null) ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {}

    public void saveExtraData() {}
}
