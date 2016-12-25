package be.spyproof.technicpixelmon;

import com.pixelmonmod.pixelmon.client.gui.inventory.GuiCreativeInventoryExtended;
import com.pixelmonmod.pixelmon.client.gui.inventory.GuiInventoryPixelmonExtended;
import com.pixelmonmod.pixelmon.client.gui.overlay.GuiPlayerList;
import com.pixelmonmod.pixelmon.config.PixelmonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InventoryDetectionTickHandler
{
    GuiPlayerList guiPlayerList = new GuiPlayerList();

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event)
    {
        if (event.getGui() instanceof GuiInventory && !(event.getGui() instanceof GuiInventoryPixelmonExtended))
            event.setGui(new GuiInventoryPixelmonExtended(Minecraft.getMinecraft().thePlayer));
        else if (event.getGui() instanceof GuiContainerCreative && !(event.getGui() instanceof GuiCreativeInventoryExtended))
            event.setGui(new GuiCreativeInventoryExtended(Minecraft.getMinecraft().thePlayer));
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent event)
    {
        if (!PixelmonConfig.useCustomTabList)
            return;

        if (Minecraft.getMinecraft().currentScreen instanceof GuiPlayerList && event.isCancelable())
        {
            event.setCanceled(true);
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.PLAYER_LIST)
        {
            event.setCanceled(true);
            this.guiPlayerList.setResolution(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
            Minecraft.getMinecraft().displayGuiScreen(this.guiPlayerList);
        }
    }
}

