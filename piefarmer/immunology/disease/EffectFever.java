package piefarmer.immunology.disease;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EffectFever extends DiseaseEffect{
	
	World worldObj = null;
	public EffectFever(int id, int stgAct, int stgEnd) {
		super(id, stgAct, stgEnd);
	}
	public void performEffect(Disease disease, EntityLiving living)
	{
		
	}

}
