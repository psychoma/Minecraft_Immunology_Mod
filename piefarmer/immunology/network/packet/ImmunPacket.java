package piefarmer.immunology.network.packet;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;

public abstract class ImmunPacket {
	
	public abstract void handle(DataInputStream iStream, EntityPlayer player); 

}
