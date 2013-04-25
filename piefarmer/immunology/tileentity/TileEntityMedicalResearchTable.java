package piefarmer.immunology.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

import piefarmer.immunology.disease.Disease;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMedicalResearchTable extends TileEntity implements IInventory{

	private ItemStack itemstacks[];
	private boolean isActive;
	public String entityname = ""; 
	public HashMap entityDiseases = new HashMap();
	
	public TileEntityMedicalResearchTable(){
		itemstacks = new ItemStack[3];
	}

	@Override
	public int getSizeInventory() {
		return itemstacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return itemstacks[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		 if (itemstacks[var1] != null)
         {
                 if (itemstacks[var1].stackSize <= var2)
                 {
                         ItemStack itemstack = itemstacks[var1];
                         itemstacks[var1] = null;
                         return itemstack;
                 }

                 ItemStack itemstack1 = itemstacks[var1].splitStack(var2);

                 if (itemstacks[var1].stackSize == 0)
                 {
                         itemstacks[var1] = null;
                 }

                 return itemstack1;
         }
         else
         {
                 return null;
         }

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		
		 if (itemstacks[var1] != null)
         {
                 ItemStack itemstack = itemstacks[var1];
                 itemstacks[var1] = null;
                 return itemstack;
         }
         else
         {
                 return null;
         }

	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		itemstacks[var1] = var2;

        if (var2 != null && var2.stackSize > getInventoryStackLimit())
        {
                var2.stackSize = getInventoryStackLimit();
        }

		
	}

	@Override
	public String getInvName() {
		return "container.medicalresearchtable";
	}

	@Override
	public int getInventoryStackLimit() {
		
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && var1.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;

	}

	public void openChest() {
		
	}
	public void closeChest() {
		
	}
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");

		for(int i = 0; i < tagList.tagCount(); i++){
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

			byte slot = tag.getByte("Slot");

			if(slot >= 0 && slot < itemstacks.length){
				itemstacks[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.entityname = tagCompound.getString("EntityName");
		NBTTagList var2;
		int var3;
		if(tagCompound.hasKey("ActiveDiseases"))
        {
        	var2 = tagCompound.getTagList("ActiveDiseases");
        	for(var3 = 0; var3 < var2.tagCount(); ++var3)
        	{
        		NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
        		Disease var5 = Disease.readCustomDiseaseFromNBT(var4);
        		this.entityDiseases.put(Integer.valueOf(var3), var5);
        	}
        }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();

		for(int i = 0; i < itemstacks.length; i++){
			ItemStack stack = itemstacks[i];

			if(stack != null){
				NBTTagCompound tag = new NBTTagCompound();

				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		NBTTagCompound tag = new NBTTagCompound();
		tagCompound.setTag("Inventory", itemList);
		tagCompound.setString("EntityName", this.entityname);
		NBTTagList var6;
		if(!this.entityDiseases.isEmpty())
        {
        	var6 = new NBTTagList();
        	Iterator var8 = this.entityDiseases.values().iterator();
        	while (var8.hasNext())
        	{
        		Disease var5 = (Disease)var8.next();
        		var6.appendTag(var5.writeCustomDiseaseToNBT(new NBTTagCompound()));
        	}
        	tagCompound.setTag("ActiveDiseases", var6);
        }
	}


	public void updateEntity()
	{
		
	}
	public void setEntityName(String name, EntityPlayer player, List list)
	{
		this.entityname = name;
    	for(int i = 0; i < list.size(); i++)
    	{
    		Disease var2 = (Disease)list.get(i);
    		entityDiseases.put(i, var2);
    	}
		this.sendPacket(player);
	}
	public boolean isActive()
	{
		return this.isActive;
	}
	public void sendPacket(EntityPlayer playerEntity)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
		        EntityPlayerMP player = (EntityPlayerMP) playerEntity;
		        PacketDispatcher.sendPacketToPlayer(this.getTileEntityPacket(this.xCoord, this.yCoord, this.zCoord, this.entityname), (Player)player);
		} else if (side == Side.CLIENT) {
		        EntityClientPlayerMP player = (EntityClientPlayerMP) playerEntity;
		        player.sendQueue.addToSendQueue(getTileEntityPacket(this.xCoord, this.yCoord, this.zCoord, this.entityname));
		} else {
		}
	}
	public Packet250CustomPayload getTileEntityPacket(int x, int y, int z, String name)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try {
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeInt(name.length());
			dos.writeChars(name);
			dos.writeInt(this.entityDiseases.size());
			Iterator var8 = this.entityDiseases.values().iterator();
			int count = 0;
        	while (var8.hasNext())
        	{
        		Disease var5 = (Disease)var8.next();
        		dos.writeInt(count);
        		dos.writeInt(var5.getdiseaseID());
    			dos.writeInt(var5.getStage());
    			dos.writeInt(var5.getDuration());
    			count++;
        	}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Packet250CustomPayload("ImmunTileMedRes", bos.toByteArray());
	}


}
