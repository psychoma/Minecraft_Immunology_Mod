package piefarmer.immunology.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import piefarmer.immunology.common.*;
import piefarmer.immunology.gui.ContainerMedicalResearchTable;
import piefarmer.immunology.gui.GuiMedicalResearchTable;
import piefarmer.immunology.item.ItemMedicalResearchTableRenderer;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTableRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderThings() 
	 {
		 MinecraftForgeClient.preloadTexture("/Immunology/Blocks/Plants.png");
		 MinecraftForgeClient.preloadTexture("/Immunology/Items.png");
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMedicalResearchTable.class, new TileEntityMedicalResearchTableRenderer());
		 MinecraftForgeClient.registerItemRenderer(Immunology.blockMedResearchTable.blockID, new ItemMedicalResearchTableRenderer());
	 }
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
	
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null)
		{
			switch(ID)
			{
				case 0:
					return new GuiMedicalResearchTable(player.inventory, (TileEntityMedicalResearchTable)tileEntity);
			}
		}
		return null;
	}
}
