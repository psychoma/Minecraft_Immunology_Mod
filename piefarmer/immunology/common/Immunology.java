package piefarmer.immunology.common;

import java.io.File;

import piefarmer.immunology.block.BlockCustomFlower;
import piefarmer.immunology.block.BlockMedicalResearchTable;
import piefarmer.immunology.client.ClientTickHandler;
import piefarmer.immunology.disease.Disease;
import piefarmer.immunology.events.EventHook;
import piefarmer.immunology.item.ItemDisease;
import piefarmer.immunology.network.packet.PacketHandler;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Immunology", name = "Immunology", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"ImmunDisease", "RemoveDisease", "ImmunParticle", "ImmunTileMedRes"}, packetHandler = PacketHandler.class)


public class Immunology {
	
	@Instance ("Immunology")
	public static Immunology instance;
	
	@SidedProxy (clientSide = "piefarmer.immunology.client.ClientProxy", serverSide = "piefarmer.immunology.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static int MedResTbl = 192;
	public static final BlockCustomFlower plantBlueBell = (BlockCustomFlower) new BlockCustomFlower(504, 0).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("bluebell");
	public static final Item disease = new ItemDisease(20000).setItemName("disease");
	public static final Block blockMedResearchTable = (new BlockMedicalResearchTable(MedResTbl)).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("MedicalResearchBlock");
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt) {
		proxy.registerRenderThings();
	}

	@Init
	public void load(FMLInitializationEvent evt) {
		
		
		Minecraft mc = ModLoader.getMinecraftInstance();
		sounds(mc);
		GameRegistry.registerWorldGenerator(new ImmunologyWorldGenerator());
		MinecraftForge.EVENT_BUS.register(new EventHook());
		NetworkRegistry.instance().registerGuiHandler(this, this.proxy);
		
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		GameRegistry.registerTileEntity(TileEntityMedicalResearchTable.class, "ImmunologyMedicalResearchTable");
		//
		//CREATIVE TABS
		//
		blockMedResearchTable.setCreativeTab(tabImmunology);
		disease.setCreativeTab(tabImmunology);
		
		//
		//BLOCK REGISTRY
		//
		GameRegistry.registerBlock(plantBlueBell, "immunologybluebell");
		GameRegistry.registerBlock(blockMedResearchTable, "medicalresearchtable");
		
		
		//
		//NAME REGISTRY
		//
		
		
		LanguageRegistry.addName(plantBlueBell, "Blue Bell");
		LanguageRegistry.addName(disease, "Disease Item");
		LanguageRegistry.addName(blockMedResearchTable, "Medical Research Table");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabImmunology", "en_US", "Immunology");
		LanguageRegistry.instance().addStringLocalization("container.medicalresearchtable", "en_US", "Medical Research Table");
		
		
	}
	private void sounds(Minecraft mc)
	{
		mc.installResource("sound3/piefarmer/immunology/sneeze.wav", new File(mc.mcDataDir, "resources/mod/Immunology/sneeze.wav"));
		mc.installResource("sound3/piefarmer/immunology/sniff.wav", new File(mc.mcDataDir, "resources/mod/Immunology/sniff.wav"));
		mc.installResource("sound3/piefamrer/immunology/cough.wav", new File(mc.mcDataDir, "resources/mod/Immunology/cough.wav"));
	}
	@PostInit
	public void postInit(FMLPostInitializationEvent evt) {

	}
	public static CreativeTabs tabImmunology = new CreativeTabs("tabImmunology") {
        public ItemStack getIconItemStack() {
                return new ItemStack(blockMedResearchTable, 1, 0);
        }
	};

}