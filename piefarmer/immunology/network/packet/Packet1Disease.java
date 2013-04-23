package piefarmer.immunology.network.packet;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import piefarmer.immunology.disease.Disease;

public class Packet1Disease extends ImmunPacket{

	public Packet1Disease(){}
	
	public Packet1Disease(int entityid, Disease disease)
	{
		
	}
	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		int diseaseid;
		int stage;
		int duration;
		int length;
		ArrayList<Integer> effects = new ArrayList<Integer>();
		try{
			diseaseid = iStream.readInt();
			stage = iStream.readInt();
			duration = iStream.readInt();
			length = iStream.readInt();
			for(int index = 0; index < length; index++)
			{
				effects.add(iStream.readInt());
			}
			Disease disease = Disease.getInstancebyName(Disease.diseaseTypes[diseaseid]);
			disease.setLoad(false);
			disease.setDuration(duration);
			disease.setStage(stage);
			disease.setDiseaseEffects(effects);
			player.addDisease(disease);
		} catch (IOException e){
			e.printStackTrace();
			return;
		}
	}
}
