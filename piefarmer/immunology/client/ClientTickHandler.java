package piefarmer.immunology.client;

import java.util.EnumSet;
import java.util.Iterator;

import piefarmer.immunology.disease.Disease;
import piefarmer.immunology.gui.InventoryDiseaseEffectRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler{

	@Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		if (type.equals(EnumSet.of(TickType.GUI)))
        {
            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            Minecraft mc = Minecraft.getMinecraft();
            if (guiscreen != null)
            {
                onTickInGUI(guiscreen, mc);
            } else {
                onTickInGame();
            }
        }
	}

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
    	if (type.equals(EnumSet.of(TickType.RENDER)))
        {
            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            Minecraft mc = Minecraft.getMinecraft();
            if (guiscreen != null)
            {
                onTickInGUI(guiscreen, mc);
            } else {
                onTickInGame();
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.GUI, TickType.RENDER);
        // In my testing only RENDER, CLIENT, & PLAYER did anything on the client side.
        // Read 'cpw.mods.fml.common.TickType.java' for a full list and description of available types
    }

    @Override
    public String getLabel() { return null; }


    public void onRenderTick()
    {
        //System.out.println("onRenderTick");
        //TODO: Your Code Here
    }

    public void onTickInGUI(GuiScreen guiscreen, Minecraft mc)
    {
    	
       if(guiscreen.getClass().getName() == GuiInventory.class.getName())
       {      
    	   InventoryDiseaseEffectRenderer rend = new InventoryDiseaseEffectRenderer(guiscreen);
    	   rend.drawScreen(0, 0, 0);
       }
    }

    public void onTickInGame()
    {
        //System.out.println("onTickInGame");
        //TODO: Your Code Here
    }

}
