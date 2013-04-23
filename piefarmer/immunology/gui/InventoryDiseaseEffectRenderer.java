package piefarmer.immunology.gui;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import piefarmer.immunology.common.Immunology;
import piefarmer.immunology.disease.Disease;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class InventoryDiseaseEffectRenderer extends GuiScreen
{
	FontRenderer fontRenderer = ModLoader.getMinecraftInstance().fontRenderer;
	GuiScreen gui;
	int guiLeft = 0;
	int guiTop = 0;
	private boolean hasDisease;
	public InventoryDiseaseEffectRenderer(GuiScreen guiscreen) 
	{
		super();
		gui = guiscreen;
		this.mc = ModLoader.getMinecraftInstance();
		this.guiLeft = (gui.width - 176) / 2;
        this.guiTop = (gui.height - 166) / 2;
	}
	public void initGui()
    {
        super.initGui();

        if (!this.mc.thePlayer.getActiveDiseases().isEmpty())
        {
            this.hasDisease = true;
        }
    }
	public void drawScreen(int par1, int par2, float par3)
    {
        	this.displayDiseaseEffects();
    }
	private void displayDiseaseEffects()
	{
		
	    Collection var4 = this.mc.thePlayer.getActiveDiseases();
	    int var1 = guiLeft;
	    int var2 = guiTop - 33;
	    
	    if (!var4.isEmpty())
	    {
	        int var5 = this.mc.renderEngine.getTexture("/Immunology/GUI/Inventory.png");
	        this.mc.renderEngine.bindTexture(var5);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        int var6 = 33;
	        this.drawTexturedModalRect(var1, var2, 0, 0, 140, 32);

	        if (var4.size() > 5)
	        {
	           var6 = 132 / (var4.size() - 1);
	        }
	        
	        for (Iterator var7 = this.mc.thePlayer.getActiveDiseases().iterator(); var7.hasNext(); var1 += var6)
	        {
	                Disease var8 = (Disease)var7.next();
	                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	                this.mc.renderEngine.bindTexture(var5);
	                this.drawTexturedModalRect(var1, var2, 0, 0, 32, 32);
	                int var9 = this.mc.renderEngine.getTexture("/Immunology/diseases.png");
	                this.mc.renderEngine.bindTexture(var9);
	                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    	        GL11.glDisable(GL11.GL_LIGHTING);
	               if (var8.hasStatusIcon())
	                {
	                    int var10 = var8.getStatusIconIndex();
	                    this.drawTexturedModalRect(var1 + 6, var2 + 4, 16 * var10, 0, 17, 17);
	                }
	               
	                String var12 = "I";

	                if (var8.getStage() == 1)
	                {
	                    var12 = var12 + " II";
	                }
	                else if (var8.getStage() == 2)
	                {
	                    var12 = var12 + " III";
	                }
	                else if (var8.getStage() == 3)
	                {
	                    var12 = var12 + " IV";
	                }
	                String var11 = Disease.getDurationString(var8);
	                this.fontRenderer.drawStringWithShadow(var11, (var1 + var11.length()) - 3, var2 - 8, 16777215);
	                
	                this.fontRenderer.drawStringWithShadow(var12, var1 + 13 - var8.getStage(), var2 + 22, 0xFFFFFF);
	                
	        }
	    }
	}
}
