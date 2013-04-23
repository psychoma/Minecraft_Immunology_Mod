package piefarmer.immunology.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonAddDisease extends GuiButton{

	public GuiButtonAddDisease(int par1, int par2, int par3) {
		super(par1, par2, par3, 9, 9, "");

	}
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            boolean var4 = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft mc = par1Minecraft;
            int i = mc.renderEngine.getTexture("/Immunology/GUI/MedicalResearchTableGUI.png");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(i);
            int var5 = 146;
            int var6 = 219;

            if (var4)
            {
                var5 += 9;
            }
            this.drawTexturedModalRect(this.xPosition, this.yPosition, var5, var6, 9, 9);
        }
    }

}
