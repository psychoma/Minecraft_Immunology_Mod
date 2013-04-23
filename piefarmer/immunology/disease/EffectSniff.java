package piefarmer.immunology.disease;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EffectSniff extends DiseaseEffect{

	World worldObj = null;
	public EffectSniff(int id, int stgAct, int stgEnd) {
		super(id, stgAct, stgEnd);
	}
	public void performEffect(Disease disease, EntityLiving living)
	{
		worldObj = living.worldObj;
		EntityPlayer player = null;
		if(living instanceof EntityPlayer)
		{
			player = (EntityPlayer)living;
		}
		int rint = rand.nextInt(200);
		if(rint < 2)
		{
			String name = living.getEntityName();
			String villagername = "Villager";
			if(player != null)
			{
				Float rfloat = rand.nextFloat();
				Float pitch = 0.7F + (rfloat / 2);
				worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "piefarmer.immunology.sniff", 0.1F, pitch);
			}
			else if(name.equals(villagername))
			{
				Float rfloat = rand.nextFloat();
				Float pitch = 0.5F + (rfloat / 2);
				worldObj.playSoundEffect(living.posX, living.posY, living.posZ, "piefarmer.immunology.sniff", 0.1F, pitch);
			}
		}
	}

}
