package piefarmer.immunology.disease;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EffectSneeze extends DiseaseEffect{

	World worldObj = null;
	public EffectSneeze(int id, int stgAct, int stgEnd) {
		super(id, stgAct, stgEnd);
	}
	public void performEffect(Disease disease, EntityLiving living)
	{
		worldObj = living.worldObj;
		EntityPlayer player = null;
		int rint = rand.nextInt(3000);
		if(living instanceof EntityPlayer)
		{
			player = (EntityPlayer)living;
		}
		if(rint < 3)
		{
			if(living.isEntityUndead())
			{
				living.setEntityHealth(living.getHealth() - 1);
			}
			else
			{
				
				if(player != null)
				{
					Float rfloat = rand.nextFloat();
					Float pitch = 0.9F + (rfloat / 8);
					worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "piefarmer.immunology.sneeze", 0.65F, pitch);
					this.sneezeMovement(living);
				}
				else
				{
					this.sneezeSound(living);
				}
			}
		}
	}
	private void sneezeMovement(EntityLiving entityliving)
	{
		//entityliving.rotationPitch = -200F;
	}
	private void sneezeSound(EntityLiving entityliving)
	{
		Random rand = new Random();
		switch(entityliving.getEntityName())
		{
			case "Cow":
				//worldObj.playSound(entityliving.posX, entityliving.posY, entityliving.posZ, "piefarmer.immunology.sneeze", 10, 0.5F, false);
				break;
			case "Pig":
				//worldObj.playSound(entityliving.posX, entityliving.posY, entityliving.posZ, "piefarmer.immunology.sneeze", 10, 0.6F, false);
				break;
			case "Villager":
				Float rfloat = rand.nextFloat();
				Float pitch = 1.0F + (rfloat / 8);
				worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "piefarmer.immunology.sneeze", 0.65F, pitch);
				break;
			case "Bat":
				//worldObj.playSound(entityliving.posX, entityliving.posY, entityliving.posZ, "piefarmer.immunology.sneeze", 10, 1.2F, false);
				break;
			case "Wolf":
				//worldObj.playSound(entityliving.posX, entityliving.posY, entityliving.posZ, "piefarmer.immunology.sneeze", 10, 0.9F, false);
				break;
		}
	}
}
