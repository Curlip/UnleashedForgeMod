package com.curlip.unleashed.wip;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.curlip.unleashed.UnleashedInfo;

public class BackpackGuiContainer extends GuiContainer {

	private float xSize_lo;
	private float ySize_lo;

	private static final ResourceLocation iconLocation = new ResourceLocation(UnleashedInfo.MODID, "textures/gui/backpackInventory.png");

	private final BackpackInventory inventory;



	public BackpackGuiContainer(BackpackContainer containerItem) {
		super(containerItem);
		this.inventory = containerItem.inventory;
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
            //draw text and stuff here
            //the parameters for drawString are: string, x, y, color
            fontRendererObj.drawString("Tiny", 8, 6, 4210752);
            //draws "Inventory" or your regional equivalent
            fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                    int par3) {
            //draw your Gui here, only thing you need to change is the path
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(iconLocation);
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
