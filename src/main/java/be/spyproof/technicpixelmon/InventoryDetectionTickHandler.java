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
        if (event.gui instanceof GuiInventory && !(event.gui instanceof GuiInventoryPixelmonExtended))
            event.gui = new GuiInventoryPixelmonExtended(Minecraft.getMinecraft().thePlayer);
        else if (event.gui instanceof GuiContainerCreative && !(event.gui instanceof GuiCreativeInventoryExtended))
            event.gui = new GuiCreativeInventoryExtended(Minecraft.getMinecraft().thePlayer);
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
        if (event.type == RenderGameOverlayEvent.ElementType.PLAYER_LIST)
        {
            event.setCanceled(true);
            this.guiPlayerList.setResolution(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
            Minecraft.getMinecraft().displayGuiScreen(this.guiPlayerList);
        }
    }
}

