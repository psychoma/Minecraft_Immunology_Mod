package piefarmer.immunology.disease;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class DiseaseInfluenza extends Disease{
	
	static World worldObj = ModLoader.getMinecraftInstance().theWorld;
	public DiseaseInfluenza(int par1, int par2, int par3,
			List<ItemStack> cures, List<Integer> list, String name) {
		super(par1, par2, par3, cures, list, name);
	}
	public DiseaseInfluenza(Disease par1Disease)
	{
		super(par1Disease);
	}
	@Override
	public void performEffect(EntityLiving entityliving)
	{
		super.performEffect(entityliving);
		if(this.getDuration() > 0)
		{
			worldObj = entityliving.worldObj;
			if(worldObj != null)
			{
				if(!worldObj.isRemote)
				{
					PacketDispatcher.sendPacketToAllPlayers(getParticlePacket(1, entityliving.posX, entityliving.posY + 5, entityliving.posZ));
				}
			}
			this.spread(entityliving);
		}
		else
		{
			entityliving.removeDisease(this.getdiseaseID());
		}
	}
	private void spread(EntityLiving entityliving)
	{
		this.checkIfEntitiesAreNearToPlayer(entityliving.posX, entityliving.posY, entityliving.posZ, 5.0D, entityliving);
	}
	private void checkIfEntitiesAreNearToPlayer(double par1, double par3, double par5, double par7, EntityLiving infectedEntity)
    {
		if(worldObj != null)
		{
			for (int var12 = 0; var12 < worldObj.getLoadedEntityList().size(); ++var12)
			{
				if(worldObj.getLoadedEntityList().size() > var12)
				{
					Entity var13 = (Entity)worldObj.getLoadedEntityList().get(var12);
					double var14 = var13.getDistanceSq(par1, par3, par5);
            
					if ((par7 < 0.0D || var14 < par7 * par7))
					{
							Random rand = new Random();
							int i = rand.nextInt(2000);
							if(var13 instanceof EntityLiving)
							{
								EntityLiving entityliving = (EntityLiving)var13;
								if(i <= 10 && entityliving.entityId != infectedEntity.entityId)
								{
									entityliving.addDisease(Disease.getInstancebyName(influenza));
                					System.out.println(entityliving.getEntityName() + " has caught Influenza at " + entityliving.posX + " "+ entityliving.posY + " " + entityliving.posZ + " by proxy of " + infectedEntity.getEntityName());
								}
							}
					}
				}
        
			}
		}
    }

}
