package piefarmer.immunology.disease;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class EffectSpots extends DiseaseEffect{

	World worldObj = null;
	public EffectSpots(int id, int stgAct, int stgEnd) {
		super(id, stgAct, stgEnd);
	}
	public void performEffect(Disease disease, EntityLiving living)
	{
		worldObj = living.worldObj;
		if(worldObj != null)
		{
			if(!worldObj.isRemote)
			{
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX + 0.3D,living.posY + 1.6D,living.posZ));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX, living.posY + 1.6D,living.posZ + 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX - 0.3D,living.posY + 1.7D,living.posZ));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX + 0.1D,living.posY + 1.8D,living.posZ + 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX, living.posY + 1.4D,living.posZ + 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX + 0.3D,living.posY + 1.4D,living.posZ - 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX, living.posY + 1.6D,living.posZ - 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX - 0.1D,living.posY + 1.7D,living.posZ - 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX + 0.1D, living.posY + 1.8D,living.posZ - 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX - 0.1D, living.posY + 1.9D,living.posZ - 0.3D));
				PacketDispatcher.sendPacketToAllPlayers(disease.getParticlePacket(0, living.posX - 0.1D,living.posY + 1.8D,living.posZ + 0.3D));
					
			}
				
		}
	}

}
