package piefarmer.immunology.disease;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import piefarmer.immunology.client.particle.ParticleEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class DiseaseChickenPox extends Disease{
	
	static World worldObj = ModLoader.getMinecraftInstance().theWorld;
	Random rand = new Random();
	
	public DiseaseChickenPox(int par1, int par2, List<ItemStack> cures, List<Integer> effects, String name) {
		super(par1, par2, 0, cures, effects, name);
	}
	public DiseaseChickenPox(Disease par1Disease)
	{
		super(par1Disease);
	}
	@Override
	public void performEffect(EntityLiving entityliving)
	{
		super.performEffect(entityliving);
		if(this.getDuration() < 240000)
		{
			int randint = this.getDuration();
			int rint = rand.nextInt(randint);
			if(rint <= 1)
			{
				this.setStage(1);
				this.setDuration(168000);
			}
		}
		this.spread(entityliving);	
	}

	private void spread(EntityLiving entityliving)
	{
		EntityLiving living = this.checkIfEntitiesAreNearToPlayer(entityliving.posX, entityliving.posY, entityliving.posZ, 5.0D, entityliving);
		if(living != null)
		{
			int i = rand.nextInt(2000 * ((this.getStage() + 1) * 5));
			if(i < 10 && living.entityId != entityliving.entityId)
			{
				living.addDisease(Disease.getInstancebyName(chickenPox));
				System.out.println(living.getEntityName() + " has caught ChickenPox at " + living.posX + " "+ living.posY + " " + living.posZ + " by proxy of " + entityliving.getEntityName());
			}
		}
	}
	private EntityLiving checkIfEntitiesAreNearToPlayer(double par1, double par3, double par5, double par7, EntityLiving infectedEntity)
    {
		worldObj = ModLoader.getMinecraftInstance().theWorld;
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
						if(var13 instanceof EntityChicken || var13 instanceof EntityPlayer)
						{
							EntityLiving entityliving = (EntityLiving)var13;
							return entityliving;
                	
						}
					}
				}
        
			}
		}
		return null;
    }
	public static void entityUpdate(EntityLiving entityliving){
		if(entityliving instanceof EntityChicken)
		{
			Random rand = new Random();
			int i = rand.nextInt(200000);
			if(i == 1)
			{
				entityliving.addDisease(Disease.getInstancebyName(chickenPox));
				System.out.println(entityliving.getEntityName() + " has caught ChickenPox at " + entityliving.posX + " "+ entityliving.posY + " " + entityliving.posZ);
			}
		}
	}

}
