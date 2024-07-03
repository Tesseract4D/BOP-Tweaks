package mods.tesseract.bopt.fix;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.BlockBOPMushroom;
import biomesoplenty.common.blocks.BlockBOPSapling;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import mods.tesseract.bopt.BOPTBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import net.tclproject.mysteriumlib.asm.annotations.EnumReturnSetting;
import net.tclproject.mysteriumlib.asm.annotations.Fix;

import java.util.Random;

public class FixesBOP {
    public static WorldGenerator holyTree = new WorldGenBOPTaiga2(BOPCBlocks.logs2, BOPCBlocks.leaves2, 0, 2, false, 10, 10, 5, 4);

    @Fix(insertOnExit = true)
    public static void func_149878_d(BlockBOPSapling c, World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMetadata(x, y, z) & 0xF;
        if (meta == 7) {
            world.setBlockToAir(x, y, z);
            if (!holyTree.generate(world, random, x, y, z))
                world.setBlock(x, y, z, c, meta, 2);
        }
    }

    @Fix(returnSetting = EnumReturnSetting.ALWAYS)
    public static boolean isValidPosition(BlockBOPSapling c, World world, int x, int y, int z, int metadata) {
        Block block = world.getBlock(x, y - 1, z);
        return (block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, c));
    }

    @Fix(returnSetting = EnumReturnSetting.ON_TRUE)
    public static boolean isValidPosition(BlockBOPMushroom c, World world, int x, int y, int z, int metadata) {
        Block block = world.getBlock(x, y - 1, z);
        return block == BOPTBlocks.holyGrass || block == BOPTBlocks.holyDirt;
    }
}
