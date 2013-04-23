package piefarmer.immunology.tileentity;

import org.lwjgl.opengl.GL11;

import piefarmer.immunology.model.ModelMedicalResearchTable;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMedicalResearchTableRenderer extends TileEntitySpecialRenderer{

	private ModelMedicalResearchTable  model;
	public TileEntityMedicalResearchTableRenderer()
	{
		model = new ModelMedicalResearchTable();
	}
	
	
	public void renderAModelAt(TileEntityMedicalResearchTable var1, double var2, double var4, double var6, float var8) {
		
		int rotation = 0;
		if(var1.worldObj != null)
		{
			rotation = var1.getBlockMetadata();
		}

		bindTextureByName("/Immunology/Blocks/MedicalResearchTable.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 1.5F, (float)var6 + 0.5F);
		if(rotation == 0 || rotation == 2)
		{
			GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
		}
		else
		{
			GL11.glRotatef(rotation * 270, 0.0F, 1.0F, 0.0F);
		}
		GL11.glScalef(1.0F, -1F, -1F);
		model.renderAll();
		GL11.glPopMatrix();
	}

	public void renderTileEntityAt(TileEntity var1, double var2, double var4,
			double var6, float var8) {
		renderAModelAt((TileEntityMedicalResearchTable)var1, var2, var4, var6, var8);
		
	}

}
