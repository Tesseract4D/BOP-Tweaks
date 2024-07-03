package mods.tesseract.bopt;

import biomesoplenty.common.core.BOPBlocks;
import mods.tesseract.bopt.blocks.*;
import mods.tesseract.bopt.itemblocks.ItemBlockSkystone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;

public class BOPTBlocks {
    public static Block holyStone;
    public static Block holyGrass;
    public static Block holyDirt;
    public static Block holyTallgrass;
    public static Block rainbowFlower;

    public static void init() {
        holyStone = BOPBlocks.registerBlock((new BlockBOPTSkystone()).setBlockName("holyStone"), ItemBlockSkystone.class);
        holyGrass = BOPBlocks.registerBlock((new BlockBOPTSkygrass()).setBlockName("holyGrass"));
        holyDirt =BOPBlocks.registerBlock(new BlockBOPTSkydirt().setBlockName("holyDirt"));
        holyTallgrass =BOPBlocks.registerBlock(new BlockBOPTHolyTallgrass().setBlockName("holyTallgrass"));
        rainbowFlower =BOPBlocks.registerBlock(new BlockBOPTRainbowFlower().setBlockName("rainbowFlower"));
    }
}
