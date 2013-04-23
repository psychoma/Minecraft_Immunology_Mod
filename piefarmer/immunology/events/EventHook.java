package piefarmer.immunology.events;

import java.util.Collection;

import piefarmer.immunology.common.Immunology;
import piefarmer.immunology.disease.Disease;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.living.*;

public class EventHook {
	boolean load = true;
	@ForgeSubscribe
	public void PlayerEvent(PlayerEvent event)
    {
		//System.out.println("EVENT");
    }
	@ForgeSubscribe
	public void PlayerInteractEvent(PlayerInteractEvent event)
	{
		//System.out.println("EVENT");
	}
	@ForgeSubscribe
	public void LivingHurtEvent(LivingHurtEvent event)
	{
		//System.out.println("HURTEVENT");
	}
	@ForgeSubscribe
	public void LivingEvent(LivingEvent event)
    {
		Disease.entityUpdateHook(event.entityLiving);
    }

}
