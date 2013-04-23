package piefarmer.immunology.block;

import java.util.Random;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import piefarmer.immunology.client.ClientProxy;
import piefarmer.immunology.common.*;
import piefarmer.immunology.gui.ContainerMedicalResearchTable;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;
import piefarmer.immunology.tileentity.TileEntityMedicalResearchTableRenderer;

public class BlockMedicalResearchTable extends BlockContainer{

	public BlockMedicalResearchTable(int par1) {
		super(par1, Material.iron);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.935F, 1.0F);
	
	}
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	public int getRenderType()
	{
		return -1;
	}
	public int getRenderBlockPass()
    {
        return 1;
    }
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
	{
		int rotation = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 2.5D) & 3;
		world.setBlockMetadata(i, j, k, rotation);
	}
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityMedicalResearchTable();
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
	{
		TileEntityMedicalResearchTable tileEntity = (TileEntityMedicalResearchTable)world.getBlockTileEntity(x, y, z);
		if(tileEntity == null || player.isSneaking())
		{
			return false;
		}
		player.openGui(Immunology.instance, 0, world, x, y, z);
		return true;
	
	}
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                        if (item.hasTagCompound()) {
                                entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
        }
}






}
