package mods.tesseract.bopt.world;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCachePromised {
    private final WorldChunkManagerPromised chunkManager;

    private long lastCleanupTime = 0L;

    private LongHashMap cacheMap = new LongHashMap();

    private List<BiomeCacheBlockPromised> cache = new ArrayList<>();

    public BiomeCachePromised(WorldChunkManagerPromised par1WorldChunkManager) {
        this.chunkManager = par1WorldChunkManager;
    }

    public BiomeCacheBlockPromised getBiomeCacheBlock(int par1, int par2) {
        par1 >>= 4;
        par2 >>= 4;
        long var3 = par1 & 0xFFFFFFFFL | (par2 & 0xFFFFFFFFL) << 32L;
        BiomeCacheBlockPromised var5 = (BiomeCacheBlockPromised)this.cacheMap.getValueByKey(var3);
        if (var5 == null) {
            var5 = new BiomeCacheBlockPromised(this, par1, par2);
            this.cacheMap.add(var3, var5);
            this.cache.add(var5);
        }
        var5.lastAccessTime = System.currentTimeMillis();
        return var5;
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2) {
        return getBiomeCacheBlock(par1, par2).getBiomeGenAt(par1, par2);
    }

    public void cleanupCache() {
        long var1 = System.currentTimeMillis();
        long var3 = var1 - this.lastCleanupTime;
        if (var3 > 7500L || var3 < 0L) {
            this.lastCleanupTime = var1;
            for (int var5 = 0; var5 < this.cache.size(); var5++) {
                BiomeCacheBlockPromised var6 = this.cache.get(var5);
                long var7 = var1 - var6.lastAccessTime;
                if (var7 > 30000L || var7 < 0L) {
                    this.cache.remove(var5--);
                    long var9 = var6.xPosition & 0xFFFFFFFFL | (var6.zPosition & 0xFFFFFFFFL) << 32L;
                    this.cacheMap.remove(var9);
                }
            }
        }
    }

    public BiomeGenBase[] getCachedBiomes(int par1, int par2) {
        return (getBiomeCacheBlock(par1, par2)).biomes;
    }

    static WorldChunkManagerPromised getChunkManager(BiomeCachePromised par0BiomeCache) {
        return par0BiomeCache.chunkManager;
    }
}
