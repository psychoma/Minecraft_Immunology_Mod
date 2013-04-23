package piefarmer.immunology.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

import piefarmer.immunology.common.Immunology;
import piefarmer.immunology.disease.DiseaseChickenPox;
import piefarmer.immunology.disease.DiseaseCommonCold;
import piefarmer.immunology.disease.Disease;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDisease extends Item{

	public ItemDisease(int par1) {
		super(par1);
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		par3EntityPlayer.clearActiveDiseases();
		par3EntityPlayer.addDisease(Disease.getInstancebyName(Disease.commonCold));
		par3EntityPlayer.addDisease(Disease.getInstancebyName(Disease.chickenPox));
		return par1ItemStack;
    }

}
