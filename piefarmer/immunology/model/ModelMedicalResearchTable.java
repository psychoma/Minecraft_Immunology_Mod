package piefarmer.immunology.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMedicalResearchTable extends ModelBase
{
	//This was built using Techne. Please go support Techne project.
    ModelRenderer TableLeft;
    ModelRenderer TableRight;
    ModelRenderer TableTop;
    ModelRenderer TableMid;
    ModelRenderer BottleBottom;
    ModelRenderer BottleFront;
    ModelRenderer BottleBack;
    ModelRenderer BottleRight;
    ModelRenderer BottleLeft;
    ModelRenderer BottleMid;
    ModelRenderer BookMid;
  
  public ModelMedicalResearchTable()
  {
    textureWidth = 96;
    textureHeight = 32;
    
      TableLeft = new ModelRenderer(this, 64, 0);
      TableLeft.addBox(0F, 0F, 0F, 1, 14, 14);
      TableLeft.setRotationPoint(6F, 10F, -7F);
      TableLeft.setTextureSize(96, 32);
      TableLeft.mirror = true;
      setRotation(TableLeft, 0F, 0F, 0F);
      TableRight = new ModelRenderer(this, 65, 0);
      TableRight.addBox(0F, 0F, 0F, 1, 14, 14);
      TableRight.setRotationPoint(-7F, 10F, -7F);
      TableRight.setTextureSize(96, 32);
      TableRight.mirror = true;
      setRotation(TableRight, 0F, 0F, 0F);
      TableTop = new ModelRenderer(this, 0, 0);
      TableTop.addBox(0F, 0F, 0F, 16, 1, 16);
      TableTop.setRotationPoint(-8F, 9F, -8F);
      TableTop.setTextureSize(96, 32);
      TableTop.mirror = true;
      setRotation(TableTop, 0F, 0F, 0F);
      TableMid = new ModelRenderer(this, 0, 17);
      TableMid.addBox(0F, 0F, 0F, 12, 1, 4);
      TableMid.setRotationPoint(-6F, 16F, -2F);
      TableMid.setTextureSize(96, 32);
      TableMid.mirror = true;
      setRotation(TableMid, 0F, 0F, 0F);
      BottleBottom = new ModelRenderer(this, 32, 17);
      BottleBottom.addBox(0F, -2F, 0F, 2, 1, 2);
      BottleBottom.setRotationPoint(3F, 10F, 4F);
      BottleBottom.setTextureSize(96, 32);
      BottleBottom.mirror = true;
      setRotation(BottleBottom, 0F, 0F, 0F);
      BottleFront = new ModelRenderer(this, 32, 17);
      BottleFront.addBox(0F, 0F, 0F, 2, 2, 1);
      BottleFront.setRotationPoint(3F, 6F, 3F);
      BottleFront.setTextureSize(96, 32);
      BottleFront.mirror = true;
      setRotation(BottleFront, 0F, 0F, 0F);
      BottleBack = new ModelRenderer(this, 32, 17);
      BottleBack.addBox(0F, 0F, 0F, 2, 2, 1);
      BottleBack.setRotationPoint(3F, 6F, 6F);
      BottleBack.setTextureSize(96, 32);
      BottleBack.mirror = true;
      setRotation(BottleBack, 0F, 0F, 0F);
      BottleRight = new ModelRenderer(this, 32, 17);
      BottleRight.addBox(0F, 0F, 0F, 1, 2, 2);
      BottleRight.setRotationPoint(2F, 6F, 4F);
      BottleRight.setTextureSize(96, 32);
      BottleRight.mirror = true;
      setRotation(BottleRight, 0F, 0F, 0F);
      BottleLeft = new ModelRenderer(this, 32, 17);
      BottleLeft.addBox(0F, 0F, 0F, 1, 2, 2);
      BottleLeft.setRotationPoint(5F, 6F, 4F);
      BottleLeft.setTextureSize(96, 32);
      BottleLeft.mirror = true;
      setRotation(BottleLeft, 0F, 0F, 0F);
      BottleMid = new ModelRenderer(this, 32, 17);
      BottleMid.addBox(0F, 0F, 0F, 2, 3, 2);
      BottleMid.setRotationPoint(3F, 3F, 4F);
      BottleMid.setTextureSize(96, 32);
      BottleMid.mirror = true;
      setRotation(BottleMid, 0F, 0F, 0F);
      BookMid = new ModelRenderer(this, 40, 17);
      BookMid.addBox(0F, 0F, 0F, 4, 1, 6);
      BookMid.setRotationPoint(-4F, 8F, -3F);
      BookMid.setTextureSize(96, 32);
      BookMid.mirror = true;
      setRotation(BookMid, 0F, -0.4537856F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    TableLeft.render(f5);
    TableRight.render(f5);
    TableTop.render(f5);
    TableMid.render(f5);
    BottleBottom.render(f5);
    BottleFront.render(f5);
    BottleBack.render(f5);
    BottleRight.render(f5);
    BottleLeft.render(f5);
    BottleMid.render(f5);
    BookMid.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  public void renderAll()
  {
	  TableLeft.render(0.0625F);
	  TableRight.render(0.0625F);
	  TableTop.render(0.0625F);
	  TableMid.render(0.0625F);
	  BottleBottom.render(0.0625F);
	  BottleFront.render(0.0625F);
	  BottleBack.render(0.0625F);
	  BottleRight.render(0.0625F);
	  BottleLeft.render(0.0625F);
	  BottleMid.render(0.0625F);
	  BookMid.render(0.0625F);
  }

}