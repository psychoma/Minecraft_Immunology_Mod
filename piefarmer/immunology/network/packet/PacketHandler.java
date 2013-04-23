package piefarmer.immunology.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	private Map<String, ImmunPacket> map = new Hashtable<String, ImmunPacket>();
	
	Packet1Disease packet1disease = new Packet1Disease();
	Packet2RemoveDisease packet2removedisease = new Packet2RemoveDisease();
	Packet3Particle packet3particle = new Packet3Particle();
	Packet4TileEntityMedicalResearchTable packet4medrestbl = new Packet4TileEntityMedicalResearchTable();
	
	public PacketHandler()
	{
		map.put("ImmunDisease", packet1disease);
		map.put("RemoveDisease", packet2removedisease);
		map.put("ImmunParticle", packet3particle);
		map.put("ImmunTileMedRes", packet4medrestbl);
	}
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player par3player) {
		
		EntityPlayer player = (EntityPlayer)par3player;
		DataInputStream iStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		map.get(packet.channel).handle(iStream, player);
		
	}

}
