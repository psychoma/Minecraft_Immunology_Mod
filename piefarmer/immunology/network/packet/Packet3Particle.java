package piefarmer.immunology.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import piefarmer.immunology.client.particle.ParticleEffects;
import piefarmer.immunology.disease.Disease;

import net.minecraft.entity.player.EntityPlayer;

public class Packet3Particle extends ImmunPacket{

public Packet3Particle(){}
	
	public Packet3Particle(int diseaseid, Disease disease)
	{
		
	}
	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			int i = iStream.readInt();
			double posX = iStream.readDouble();
			double posY = iStream.readDouble();
			double posZ = iStream.readDouble();
			switch(i)
			{
				case 0:
					ParticleEffects.spawnParticle("chickenPox", posX, posY, posZ, 0, 0, 0);
					break;
				case 1:
					ParticleEffects.spawnParticle("test2", posX, posY, posZ, 0, 0, 0);
					break;
			}
		
		} catch (IOException e) {
		e.printStackTrace();
		}

		
	}

}
