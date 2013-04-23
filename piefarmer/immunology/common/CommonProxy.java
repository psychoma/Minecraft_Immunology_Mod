package piefarmer.immunology.common;

import piefarmer.immunology.gui.ContainerMedicalResearchTable;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	public void registerRenderThings() {

	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if(tileEntity != null)
		{
			switch(ID)
			{
				case 0:
					return new ContainerMedicalResearchTable(player.inventory, (TileEntityMedicalResearchTable)tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return null;
	}

}
