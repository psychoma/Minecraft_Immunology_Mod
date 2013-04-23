package piefarmer.immunology.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import cpw.mods.fml.common.IWorldGenerator;

public class ImmunologyWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		
		
		switch(world.provider.dimensionId){
		case -1:
			generateNether(world, random, chunkX*16, chunkZ*16);
			break;
		case 0:
			generateSurface(world, random, chunkX*16, chunkZ*16);
			break;
		case 1:
			generateEnd(world, random, chunkX*16, chunkZ*16);
			break;
		}
		
	}
	public void generateSurface(World world, Random random, int blockX, int blockZ){
		new WorldGenFlowers(Immunology.plantBlueBell.blockID).generate(world, random, blockX + random.nextInt(16), + random.nextInt(140), blockZ + random.nextInt(16));
	}
	public void generateNether(World world, Random random, int blockX, int blockZ){}
	public void generateEnd(World world, Random random, int blockX, int blockZ){}

	public static BiomeGenBase getBiomeAt(int posX, int posY, World world){
		return world.getBiomeGenForCoords(posX, posY);
	}

}
