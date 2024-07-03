package mods.tesseract.bopt.blocks;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;

public class BlockBOPTHolyTallgrass extends BlockBush implements IShearable {
    public BlockBOPTHolyTallgrass() {
        super(Material.vine);
        this.setHardness(0.0F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        float w = 0.4F;
        setBlockBounds(0.5F - w, 0.0F, 0.5F - w, 0.5F + w, 0.8F, 0.5F + w);
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

    public void registerBlockIcons(IIconRegister r) {
        this.blockIcon = r.registerIcon("bopt:holytallgrass");
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> a = new ArrayList<>();
        a.add(new ItemStack(this));
        return a;
    }
}
