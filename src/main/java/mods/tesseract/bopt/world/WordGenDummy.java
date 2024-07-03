package mods.tesseract.bopt.world;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WordGenDummy extends WorldGenerator {
    @Override
    public boolean generate(World w, Random r, int x, int y, int z) {
        setBlockAndNotifyAdequately(w, x, y, z, Blocks.bedrock, 0);
        return true;
    }
}
