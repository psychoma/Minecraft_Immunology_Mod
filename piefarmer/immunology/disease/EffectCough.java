package piefarmer.immunology.disease;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EffectCough extends DiseaseEffect {

	World worldObj = null;
	public EffectCough(int id, int stgAct, int stgEnd) {
		super(id, stgAct, stgEnd);
	}
	public void performEffect(Disease disease, EntityLiving entityliving)
	{

		worldObj = entityliving.worldObj;
		EntityPlayer player = null;
		if(entityliving instanceof EntityPlayer)
		{
			player = (EntityPlayer)entityliving;
		}
		int rint = rand.nextInt(250);
		if(rint < 2)
		{
			String name = entityliving.getEntityName();
			String villagername = "Villager";
			if(player != null)
			{
				Float rfloat = rand.nextFloat();
				Float pitch = 0.7F + (rfloat / 2);
				worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "piefarmer.immunology.cough", 0.1F, pitch);
			}
			else if(name.equals(villagername))
			{
				Float rfloat = rand.nextFloat();
				Float pitch = 0.5F + (rfloat / 2);
				worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "piefarmer.immunology.cough", 0.1F, pitch);
			}
		}
	}

}
