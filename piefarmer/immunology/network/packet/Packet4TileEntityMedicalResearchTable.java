package piefarmer.immunology.network.packet;

import java.io.DataInputStream;
import java.io.IOException;

import piefarmer.immunology.disease.Disease;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Packet4TileEntityMedicalResearchTable extends ImmunPacket{

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		
		try {
			int x = iStream.readInt();
			int y = iStream.readInt();
			int z = iStream.readInt();
			int i = iStream.readInt();
			String name = "";
			char c[] = new char[i];
			for (int j = 0; j < i; j++)
			{
				c[j] = iStream.readChar();
				name += c[j];
				
			}
			int count = iStream.readInt();
			World worldObj = player.worldObj;
			TileEntityMedicalResearchTable tile = (TileEntityMedicalResearchTable) worldObj.getBlockTileEntity(x, y, z);
			tile.entityname = name;
			for(int j = 0; j < count; j++)
			{
				int diseaseid = iStream.readInt();
				int stage = iStream.readInt();
				int duration = iStream.readInt();
				Disease disease = Disease.getInstancebyName(Disease.diseaseTypes[diseaseid]);
				disease.setLoad(false);
				disease.setDuration(duration);
				disease.setStage(stage);
				tile.entityDiseases.put(Integer.valueOf(disease.getdiseaseID()), disease);
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
