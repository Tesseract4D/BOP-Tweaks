package mods.tesseract.bopt;

import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.BlockBOPLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mods.tesseract.bopt.world.WorldProviderPromised;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.DimensionManager;
import net.tclproject.mysteriumlib.asm.common.CustomLoadingPlugin;
import net.tclproject.mysteriumlib.asm.common.FirstClassTransformer;

import java.lang.reflect.Field;

@Mod(modid = "bopt", dependencies = "required-after:BiomesOPlenty", acceptedMinecraftVersions = "[1.7.10]")
public class BOPTweaks extends CustomLoadingPlugin {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        BOPTBlocks.init();
        BOPTBiomes.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        BOPCBiomes.lushDesert.fillerBlock = Blocks.dirt;
        DimensionManager.registerProviderType(20, WorldProviderPromised.class, false);
        DimensionManager.registerDimension(20, 20);
    }
    public String[] getASMTransformerClass() {
        return new String[]{FirstClassTransformer.class.getName()};
    }

    public void registerFixes() {
        registerClassWithFixes("mods.tesseract.bopt.fix.FixesBOP");
    }
}
