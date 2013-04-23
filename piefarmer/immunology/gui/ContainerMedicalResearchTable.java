package piefarmer.immunology.gui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMedicalResearchTable extends Container{

	private TileEntityMedicalResearchTable medrestbl;
	private boolean isExpanded = false;
	public ContainerMedicalResearchTable(InventoryPlayer par1InventoryPlayer, TileEntityMedicalResearchTable par2TileEntityMedResTbl)
	{
		this.medrestbl = par2TileEntityMedResTbl;
		
		addSlotToContainer(new Slot(par2TileEntityMedResTbl, 0, 19, -8));
		addSlotToContainer(new Slot(par2TileEntityMedResTbl, 1, 141, -8));
		
		bindPlayerInventory(par1InventoryPlayer);
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
			this.medrestbl.sendPacket(par1InventoryPlayer.player);
		}
		
	}
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return medrestbl.isUseableByPlayer(var1);
	}
	 protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
         for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 9; j++) {
                         addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                         8 + j * 18, 110 + i * 18));
                 }
         }

         for (int i = 0; i < 9; i++) {
                 addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 168));
         }
	 }
	 @Override
     public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber)
     {
		 ItemStack itemstack = null;
		 Slot slot = (Slot)inventorySlots.get(slotnumber);
		 if (slot != null && slot.getHasStack())
		 {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(slotnumber < 3 && slotnumber > 0)
            {
            	if(!mergeItemStack(itemstack1,  2, 43, false))
            	{
            		return null;
                }
            }
            else
            {
               	 if(!mergeItemStack(itemstack1, 0, 7, false))
               	 {
               		 return null;
               	 }
               	 if (itemstack1.stackSize == 0)
                 {
                     slot.putStack(null);
                 }
                 else
                 {
                     slot.onSlotChanged();
                 }

                 if (itemstack1.stackSize == itemstack.stackSize)
                 {
                     return null;
                 }
                     
                 slot.onPickupFromSlot(player, itemstack);
                                
            }
		 }
            
         return itemstack;
     }
		


}
