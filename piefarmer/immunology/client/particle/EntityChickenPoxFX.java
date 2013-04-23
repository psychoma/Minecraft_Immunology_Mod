package piefarmer.immunology.client.particle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityChickenPoxFX extends EntityFX
{

	float particleScaleOverTime;


	public EntityChickenPoxFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10)
	{
		this(par1World, par2, par4, par6, par8, par9, par10, 2.0F);
	}

	public EntityChickenPoxFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.009999999776482582D;
        this.motionY *= 0.009999999776482582D;
        this.motionZ *= 0.009999999776482582D;
        this.motionY += 0.1D;
        this.particleScale *= this.rand.nextFloat() * 0.21F + 0.3F;
        this.particleScaleOverTime = this.particleScale;
        this.particleMaxAge = 8;
        this.noClip = false;
        this.setParticleTextureIndex(3);

	}

	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
       
        Tessellator tessellator1 = new Tessellator();
        tessellator1.startDrawingQuads();
        tessellator1.setBrightness(getBrightnessForRender(par2));
       
        float var8 = ((float)this.particleAge + par2) / (float)this.particleMaxAge * 32.0F;
        if (var8 < 0.0F)
        {
                var8 = 0.0F;
        }
        if (var8 > 1.0F)
        {
                var8 = 1.0F;
        }
        //this.particleScale = this.particleScaleOverTime * var8;
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/Immunology/particles.png"));
        //For the location of the texture X direction.
        float f0 = (float)(getParticleTextureIndex() % 16) / 16F;
        //For how big of the texture is used in X direction.
        float f7 = f0 + 0.0624375F;
        //For the location of the texture Y direction.
        float f8 = (float)(getParticleTextureIndex() / 16) / 16F;
        //For how big of the texture is used in Y direction.
        float f9 = f8 + 0.0624375F;
        //Size of the particle
        float f10 = 0.1F;
        float f11 = (float)((prevPosX + (posX - prevPosX) * (double)par2) - interpPosX);
        float f12 = (float)((prevPosY + (posY - prevPosY) * (double)par2) - interpPosY);
        float f13 = (float)((prevPosZ + (posZ - prevPosZ) * (double)par2) - interpPosZ);
        //Brightness setting for the particle.
        float f14 = 1.0F;
        tessellator1.setColorOpaque_F(particleRed * f14, particleGreen * f14, particleBlue * f14);
        tessellator1.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, f7, f9);
        tessellator1.addVertexWithUV((f11 - par3 * f10) + par6 * f10, f12 + par4 * f10, (f13 - par5 * f10) + par7 * f10, f7, f8);
        tessellator1.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, f0, f8);
        tessellator1.addVertexWithUV((f11 + par3 * f10) - par6 * f10, f12 - par4 * f10, (f13 + par5 * f10) - par7 * f10, f0, f9);
       
        tessellator1.draw();
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/particles.png"));
}


	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		this.setParticleTextureIndex(2 - this.particleAge * 8 / this.particleMaxAge);
		//this.setParticleTextureIndex(16);
       //this.motionY += 0.002D;
        //this.moveEntity(this.motionX, 0, this.motionZ);
       // this.moveEntity(0, 0, 0);
        //this.motionX *= 0.8500000238418579D;
        //this.motionY *= 0.8500000238418579D;
        //this.motionZ *= 0.8500000238418579D;
		
		/*if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}*/
	}
}

