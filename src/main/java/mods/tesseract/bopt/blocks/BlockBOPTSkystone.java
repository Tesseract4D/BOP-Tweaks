package mods.tesseract.bopt.blocks;

import biomesoplenty.BiomesOPlenty;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBOPTSkystone extends Block {
    private static final String[] types = new String[] { "holystone", "holycobble", "holybrick", "holystonemossy" };

    private IIcon[] textures = new IIcon[] { null, null, null };

    public BlockBOPTSkystone() {
        super(Material.rock);
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        setStepSound(Block.soundTypeStone);
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        this.textures = new IIcon[types.length];
        for (int i = 0; i < types.length; i++)
            this.textures[i] = iconRegister.registerIcon("bopt:" + types[i]);
    }

    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= this.textures.length)
            meta = 0;
        return this.textures[meta];
    }

    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    public void getSubBlocks(Item itemIn, CreativeTabs creativeTabs, List<ItemStack> list) {
        for (int i = 0; i < types.length; i++)
            list.add(new ItemStack(itemIn, 1, i));
    }

    public int damageDropped(int meta) {
        if (meta == 0)
            return 1;
        if (meta == 3)
            return 1;
        return meta;
    }

    public float getBlockHardness(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        float hardness = this.blockHardness;
        switch (meta) {
            case 0:
                hardness = 1.0F;
                break;
            case 1:
                hardness = 1.6F;
                break;
            case 2:
                hardness = 1.1F;
                break;
            case 3:
                hardness = 1.0F;
                break;
        }
        return hardness;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int meta = world.getBlockMetadata(x, y, z);
        float resistance = this.blockResistance;
        switch (meta) {
            case 1:
                resistance = 7.5F;
                break;
            case 2:
                resistance = 7.0F;
                break;
        }
        return resistance / 5.0F;
    }
}
