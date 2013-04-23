package piefarmer.immunology.item;

import piefarmer.immunology.tileentity.TileEntityMedicalResearchTable;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemMedicalResearchTableRenderer implements IItemRenderer{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityMedicalResearchTable(), 0.0D, 0.0D, 0.0D, 0.0F);

	}

}
