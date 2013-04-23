package piefarmer.immunology.network.packet;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import piefarmer.immunology.disease.Disease;

public class Packet2RemoveDisease extends ImmunPacket{
	
public Packet2RemoveDisease(){}
	
	public Packet2RemoveDisease(int diseaseid, Disease disease)
	{
		
	}
	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		int diseaseid;
		
		try{
			diseaseid = iStream.readInt();

			player.removeDiseaseClient(diseaseid);
		} catch (IOException e){
			e.printStackTrace();
			return;
		}
	}

}
