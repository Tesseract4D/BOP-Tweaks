package mods.tesseract.bopt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockBOPTSkydirt extends Block {
    public BlockBOPTSkydirt() {
        super(Material.ground);
        setHardness(0.6F).setStepSound(Block.soundTypeGravel);
    }

    public void registerBlockIcons(IIconRegister r) {
        this.blockIcon = r.registerIcon("bopt:holydirt");
    }
}
