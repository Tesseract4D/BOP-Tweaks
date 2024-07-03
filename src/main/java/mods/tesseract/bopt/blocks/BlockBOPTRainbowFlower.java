package mods.tesseract.bopt.blocks;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockBOPTRainbowFlower extends BlockBush {
    public BlockBOPTRainbowFlower() {
        this.setHardness(0.0F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        float w = 0.4F;
        setBlockBounds(0.5F - w, 0.0F, 0.5F - w, 0.5F + w, 0.8F, 0.5F + w);
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);;
    }

    public void registerBlockIcons(IIconRegister r) {
        this.blockIcon = r.registerIcon("bopt:rainbowflower");
    }

}
