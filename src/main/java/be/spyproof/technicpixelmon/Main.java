package be.spyproof.technicpixelmon;

import com.pixelmonmod.pixelmon.util.SensitiveCode;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.IEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Spyproof.
 */
@Mod(modid = "TechnicPixelmonFix")
public class Main
{
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception
    {
        Field f = MinecraftForge.EVENT_BUS.getClass().getDeclaredField("listeners");
        f.setAccessible(true);
        ConcurrentHashMap<Object, ArrayList<IEventListener>> listeners = (ConcurrentHashMap<Object, ArrayList<IEventListener>>) f.get(MinecraftForge.EVENT_BUS);
        for(Map.Entry<Object, ArrayList<IEventListener>> entry : listeners.entrySet())
        {
            if (entry.getKey().getClass().equals(SensitiveCode.class))
            {
                MinecraftForge.EVENT_BUS.unregister(entry.getKey());
                return;
            }
        }
    }
}
