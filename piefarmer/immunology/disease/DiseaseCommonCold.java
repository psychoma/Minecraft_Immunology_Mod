package piefarmer.immunology.disease;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import piefarmer.immunology.client.particle.ParticleEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class DiseaseCommonCold extends Disease{

	static World worldObj = ModLoader.getMinecraftInstance().theWorld;
	
	public DiseaseCommonCold(int par1, int par2, List<ItemStack> cures, List<Integer> effects, String name) {
		super(par1, par2, 0, cures, effects, name);

	}
	public DiseaseCommonCold(Disease par1Disease)
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
			EntityPlayer player = null;
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
							int i = rand.nextInt(20000);
							if(var13 instanceof EntityLiving)
							{
								EntityLiving entityliving = (EntityLiving)var13;
								if(i <= 10 && entityliving.entityId != infectedEntity.entityId)
								{
									entityliving.addDisease(Disease.getInstancebyName(commonCold));
                					System.out.println(entityliving.getEntityName() + " has caught Common Cold at " + entityliving.posX + " "+ entityliving.posY + " " + entityliving.posZ + " by proxy of " + infectedEntity.getEntityName());
								}
							}
					}
				}
        
			}
		}
    }
	
	public static void entityUpdate(EntityLiving entityliving)
	{
		if(DiseaseCommonCold.getBiomeTemperature(entityliving) < 4000)
		{
			Random rand = new Random();
			int i = rand.nextInt(200000);
			if(i == 1)
			{
				entityliving.addDisease(Disease.getInstancebyName(commonCold));
				System.out.println(entityliving.getEntityName() + " has caught Common Cold at " + entityliving.posX + " "+ entityliving.posY + " " + entityliving.posZ);
			}
		}
	}
	public static int getBiomeTemperature(EntityLiving entityliving)
	{
		worldObj = ModLoader.getMinecraftInstance().theWorld;
		if(worldObj != null)
		{
			BiomeGenBase biome = worldObj.getBiomeGenForCoords((int)entityliving.posX, (int)entityliving.posZ);
			return biome.getIntTemperature();
		}
		return 0;
	}

}
