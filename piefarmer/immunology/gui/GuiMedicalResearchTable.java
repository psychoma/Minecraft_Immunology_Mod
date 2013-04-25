package piefarmer.immunology.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import piefarmer.immunology.disease.Disease;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiMedicalResearchTable extends GuiContainer{

	int posX = 0;
	int posY = 0;
	int ySize = 219;
	int currentPage = 1;
	int maxPages = 99;
	GuiButtonExpand btnExpand1 = null;
	GuiButtonExpand btnExpand2 = null;
	GuiButtonExpand btnExpand3 = null;
	EntityPlayer entityplayer = null;
	TileEntityMedicalResearchTable tile = null;
	Disease selectedDisease = null;
	Disease[] diseases = new Disease[3];
	List<Disease> Entitydiseases = new ArrayList<Disease>();
	
	
	public GuiMedicalResearchTable(InventoryPlayer player, TileEntityMedicalResearchTable tileentity)
	{
		super(new ContainerMedicalResearchTable(player, tileentity));
		entityplayer = player.player;
		tile = tileentity;
		this.Entitydiseases = new ArrayList<Disease>(tile.entityDiseases.values());
		float size = this.Entitydiseases.size();
		this.pageSetup(size);
	}
	@Override
	public void initGui()
	{
		super.initGui();
		posX = (this.width - this.xSize) / 2;
        posY = (this.height - this.ySize) / 2;
		controlList.clear();
		controlList.add(new GuiButtonNextDisease(0, posX + 102, posY + 27, true));
		controlList.add(new GuiButtonNextDisease(1, posX + 55, posY + 27, false));
		btnExpand1 = new GuiButtonExpand(2, posX + 141, posY + 47);
		btnExpand2 = new GuiButtonExpand(3, posX + 141, posY + 76);
		btnExpand3 = new GuiButtonExpand(4, posX + 141, posY + 105);
		controlList.add(btnExpand1);
		controlList.add(btnExpand2);		
		controlList.add(btnExpand3);
		controlList.add(new GuiButtonAddDisease(5, posX + 119, posY + 23));
		
		
	}
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.medicalresearchtable"), 9, -21, 4210752);
    }
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.drawDefaultBackground();
		int i = mc.renderEngine.getTexture("/Immunology/GUI/MedicalResearchTableGUI.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);
        
        i = mc.renderEngine.getTexture("/Immunology/GUI/MedicalResearchTableGUIExtra.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        
        if(this.btnExpand1.drawButton && this.btnExpand2.drawButton && this.btnExpand3.drawButton)
        {	
        	setup3Bars();
        	 i = mc.renderEngine.getTexture("/Immunology/GUI/MedicalResearchTableGUI.png");
             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
             mc.renderEngine.bindTexture(i);
        	drawTexturedModalRect(posX + 45, posY + 56, 0, 224, 82, 5);
            drawTexturedModalRect(posX + 45, posY + 85, 0, 224, 82, 5);
            drawTexturedModalRect(posX + 45, posY + 114, 0, 224, 82, 5);
            this.setDiseasesDisplay();
        }
        else
        {
        	if(this.btnExpand1.drawButton)
        	{
        		drawTexturedModalRect(posX + 12, posY + 39, 0,0, 150, 86);
        	}
        	else if(this.btnExpand2.drawButton)
        	{
        		drawTexturedModalRect(posX + 12, posY + 39, 0,0, 150, 86);
        	}
        	else if(this.btnExpand3.drawButton)
        	{
        		drawTexturedModalRect(posX + 12, posY + 39, 0,0, 150, 86);
        	}
        	this.setTopDisease(selectedDisease);
        }
        
        this.drawCenteredString(fontRenderer, currentPage + "/" + maxPages, posX + 84, posY + 27, 0xffffff);
        this.drawCenteredString(fontRenderer, this.tile.entityname, posX + 85, posY + 17, 0xffffff);
        
	}
	private void setup3Bars()
	{
		//drawTexturedModalRect(posX + 13, posY + 40, 0,0, 150, 86);
		drawTexturedModalRect(posX + 12, posY + 39, 0, 0, 150, 24);
		drawTexturedModalRect(posX + 12, posY + 63, 0, 82, 150, 4);
		
		drawTexturedModalRect(posX + 12, posY + 68, 0, 0, 150, 24);
		drawTexturedModalRect(posX + 12, posY + 92, 0, 82, 150, 4);
		
		drawTexturedModalRect(posX + 12, posY + 97, 0, 0, 150, 24);
		drawTexturedModalRect(posX + 12, posY + 121, 0, 82, 150, 4);
	}
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 0: 
			if((currentPage + 1) <= maxPages)
			{
				currentPage++;
			}
			break;
		case 1:
			if((currentPage - 1) > 0)
			{
				currentPage--;
			}
			break;
		case 2:
			if(this.btnExpand2.drawButton && this.btnExpand3.drawButton)
			{
				this.btnExpand2.drawButton = false;
				this.btnExpand3.drawButton = false;
				this.btnExpand1.setExpaned(true);
				this.selectedDisease = this.diseases[button.id - 2];
			}
			else if(!this.btnExpand2.drawButton && !this.btnExpand3.drawButton)
			{
				this.btnExpand2.drawButton = true;
				this.btnExpand3.drawButton = true;
				this.btnExpand1.setExpaned(false);
			}
			break;
		case 3:
			if(this.btnExpand1.drawButton && this.btnExpand3.drawButton)
			{
				this.btnExpand2.drawButton = false;
				this.btnExpand3.drawButton = false;
				this.btnExpand1.setExpaned(true);
				this.selectedDisease = this.diseases[button.id - 2];
			}
			else if(!this.btnExpand1.drawButton && !this.btnExpand3.drawButton)
			{
				this.btnExpand1.drawButton = true;
				this.btnExpand3.drawButton = true;
				this.btnExpand2.setExpaned(false);
			}
			break;
		case 4:
			if(this.btnExpand1.drawButton && this.btnExpand2.drawButton)
			{
				this.btnExpand2.drawButton = false;
				this.btnExpand3.drawButton = false;
				this.btnExpand1.setExpaned(true);
				this.selectedDisease = this.diseases[button.id - 2];
			}
			else if(!this.btnExpand1.drawButton && !this.btnExpand2.drawButton)
			{
				this.btnExpand1.drawButton = true;
				this.btnExpand2.drawButton = true;
				this.btnExpand3.setExpaned(false);
			}
			break;
		case 5:
			this.getDiseases();
			break;
			
		}
		
	}
	private void setDiseasesDisplay()
	{
		int count = 0;
		if(currentPage > 0)
		{
			for(int i = this.currentPage - 1; i < 3 * this.currentPage; i++)
			{
				if(i < this.Entitydiseases.size())
				{
					Disease var2 = this.Entitydiseases.get(i);
					Disease disease = Disease.diseaseTypes[var2.getdiseaseID()];
					int j = mc.renderEngine.getTexture("/Immunology/diseases.png");
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mc.renderEngine.bindTexture(j);
					drawTexturedModalRect(posX + 20, posY + 44 + (29 * count), 16 * disease.getStatusIconIndex(), 0, 17, 17 );
    				this.drawCenteredString(fontRenderer, disease.getName(), posX + 83, posY + 45 + (29 * count), 0xffffff);
    				this.diseases[count] = disease;
    				count++;
				}
			}
		}
	}
	private void setTopDisease(Disease disease)
	{
		if(disease != null)
		{
			int i = mc.renderEngine.getTexture("/Immunology/diseases.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(i);
			drawTexturedModalRect(posX + 20, posY + 44, 16 * disease.getStatusIconIndex(), 0, 17, 17 );
			this.drawCenteredString(fontRenderer, disease.getName(), posX + 83, posY + 45, 0xffffff);
			i = mc.renderEngine.getTexture("/Immunology/GUI/MedicalResearchTableGUI.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(i);
			drawTexturedModalRect(posX + 45, posY + 56, 0, 224, 82, 5);
		}
	}
	public void getDiseases()
	{
		int[] effects = this.entityplayer.getDiseaseEffects();
		this.Entitydiseases = Disease.getDiseasesByEffects(effects);
		float size = this.Entitydiseases.size();
		this.tile.setEntityName(entityplayer.username, entityplayer, this.Entitydiseases);
		this.pageSetup(size);
	}
	public void pageSetup(float size)
	{
		maxPages = (int) Math.ceil(size / 3);
		currentPage = 1;
		if(maxPages == 0)
		{
			currentPage = 0;
		}
	}


}
