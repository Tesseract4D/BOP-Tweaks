package mods.tesseract.bopt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import static net.minecraftforge.common.util.ForgeDirection.UP;

public class BlockBOPTSkydirt extends Block {
    public BlockBOPTSkydirt() {
        super(Material.ground);
        setHardness(0.6F).setStepSound(Block.soundTypeGravel);
    }
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        boolean hasWater;
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
        switch (plantType) {
            case Cave:
                return isSideSolid(world, x, y, z, UP);
            case Plains:
                return true;
            case Beach:
                hasWater = (world.getBlock(x - 1, y, z).getMaterial() == Material.water || world.getBlock(x + 1, y, z).getMaterial() == Material.water || world.getBlock(x, y, z - 1).getMaterial() == Material.water || world.getBlock(x, y, z + 1).getMaterial() == Material.water);
                return hasWater;
        }
        return super.canSustainPlant(world, x, y, z, direction, plantable);
    }
    public void registerBlockIcons(IIconRegister r) {
        this.blockIcon = r.registerIcon("bopt:holydirt");
    }
}
