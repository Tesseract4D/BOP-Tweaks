package mods.tesseract.bopt.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSkystone extends ItemBlock {
    private static final String[] types = new String[] { "holystone", "holycobble", "holybrick", "holystonemossy" };

    public ItemBlockSkystone(Block par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int meta) {
        return meta & 0xF;
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        if (meta < 0 || meta >= types.length)
            meta = 0;
        return getUnlocalizedName() + "." + types[meta];
    }
}
