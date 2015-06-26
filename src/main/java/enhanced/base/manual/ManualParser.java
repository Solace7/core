package enhanced.base.manual;

import java.io.StringWriter;
import java.util.HashMap;

import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cpw.mods.fml.client.FMLClientHandler;
import enhanced.core.EnhancedCore;

public class ManualParser {
    static String getData(String mod) {
        IReloadableResourceManager resourceManager = (IReloadableResourceManager) FMLClientHandler.instance().getClient().getResourceManager();

        try {
            IResource r = resourceManager.getResource(new ResourceLocation(mod, "manual/" + FMLClientHandler.instance().getCurrentLanguage() + ".json"));
            StringWriter writer = new StringWriter();
            IOUtils.copy(r.getInputStream(), writer);
            return writer.toString();
        } catch (Exception e) {
            EnhancedCore.instance.getLogger().info("Could not find manual for language: " + FMLClientHandler.instance().getCurrentLanguage());
            try {
                IResource r = resourceManager.getResource(new ResourceLocation(mod, "manual/en_US.json"));
                StringWriter writer = new StringWriter();
                IOUtils.copy(r.getInputStream(), writer);
                return writer.toString();
            } catch (Exception ex) {
                EnhancedCore.instance.getLogger().warn("Could not find manual for language: en_US");
                return "ERROR";
            }
        }
    }
    
    public static HashMap<String, PageManual> loadManual(String mod) {
        String data = getData(mod);
        HashMap<String, PageManual> registeredPages = new HashMap<String, PageManual>();
        
        if (data.equals("ERROR")) {
            registeredPages.put("intro", new PageText(null, "Error while loading the manual. Could not find manual for language (" + FMLClientHandler.instance().getCurrentLanguage() + ") additionally, could not fall back to en_US (manual not found).")); // TODO: Localise
        } else {
            Gson gson = new GsonBuilder().create();
            JsonPage[] pages = gson.fromJson(data, JsonPage[].class);
            
            for (JsonPage p : pages) {
                PageManual page = p.getPage();
                
                if (page != null)
                    registeredPages.put(p.getPageName(), page);
            }
        }
        
        return registeredPages;
    }
}
