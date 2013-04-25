package piefarmer.immunology.disease;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import java.util.Random;
public class DiseaseEffect {
	
	private int DiseaseEffectID;
	private int stageActivate;
	private int stageEnd;
	public static final DiseaseEffect[] diseaseEffects = new DiseaseEffect[64];
	public static DiseaseEffect sneeze = new EffectSneeze(0, 0, 0);
	public static DiseaseEffect sniff = new EffectSniff(1, 0, 0);
	public static DiseaseEffect chickenSpots = new EffectSpots(2, 1, 1);
	public static DiseaseEffect cough = new EffectCough(3, 0, 0);
	public static DiseaseEffect fever = new EffectFever(4, 0, 0);
	public static DiseaseEffect headache = new EffectHeadache(5, 0, 0);
	public static DiseaseEffect chills = new EffectChills(6, 0, 0);
	
	protected Random rand = new Random();
	
	public DiseaseEffect(int id, int stgAct, int stgEnd)
	{
		this.DiseaseEffectID = id;
		this.stageActivate = stgAct;
		this.stageEnd = stgEnd;
		diseaseEffects[id] = this;
	}
	public DiseaseEffect(int par1)
    {
    	this(par1, 0, 0);
    }
	public DiseaseEffect(DiseaseEffect effect)
	{
		this.DiseaseEffectID = effect.getDiseaseEffetcID();
		this.stageActivate = effect.getStageStart();
		this.stageEnd = effect.getStageEnd();
	}
	public void performEffect(Disease disease, EntityLiving living){
		
	}
	public int getDiseaseEffetcID()
	{
		return this.DiseaseEffectID;
	}
	public int getStageStart()
	{
		return this.stageActivate;
	}
	public int getStageEnd()
	{
		return this.stageEnd;
	}
	
}
