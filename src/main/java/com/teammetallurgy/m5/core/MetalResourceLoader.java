package com.teammetallurgy.m5.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.teammetallurgy.m5.core.registry.HSLColor;
import com.teammetallurgy.m5.core.utils.JSONMaker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class MetalResourceLoader implements IResourcePack {
    
    enum Type {
        BLOCKSTATE,
        BLOCKMODEL,
        ITEMMODEL,
        ITEMBLOCKMODEL,
        PNG
    }
    

    public static MetalResourceLoader instance = new MetalResourceLoader();
    
    private String[] items = new String[] { "item", "axe", "boots", "chestplate", "dust", "helmet", "hoe", "ingot", "leggings", "nugget", "pickaxe", "shovel", "sword" };
    private String[] blocks = new String[] { "ore", "block", "large_bricks" };
    private Set<String> domains = new HashSet<>();
    private Map<String, Type> registry = new HashMap<String, Type>();
    
    private MetalResourceLoader() {
        
    }
    
    @Override
    public InputStream getInputStream(ResourceLocation location) throws IOException {        
        if(registry.containsKey(location.toString())) {
            Type type = registry.get(location.toString());
            if(type == Type.BLOCKMODEL)
            {
                String name = location.getPath().replaceAll("models/block/", "").split("\\.")[0];
                String json = JSONMaker.getBlockModelString(location.getNamespace(), name);
                return new ByteArrayInputStream(json.getBytes());
            }
            if(type == Type.ITEMBLOCKMODEL)
            {
                String name = location.getPath().replaceAll("models/item/", "").split("\\.")[0];
                String json = JSONMaker.getItemBlockModelString(location.getNamespace(), name);
                return new ByteArrayInputStream(json.getBytes());
            }
            if(type == Type.ITEMMODEL)
            {
                String name = location.getPath().replaceAll("models/item/", "").split("\\.")[0];
                String json = JSONMaker.getItemModelString(location.getNamespace(), name);
                return new ByteArrayInputStream(json.getBytes());
            }
            if(type == Type.BLOCKSTATE)
            {
                String name = location.getPath().replaceAll("blockstates/", "").split("\\.")[0];
                String json = JSONMaker.getBlockstateString(location.getNamespace(), name);
                return new ByteArrayInputStream(json.getBytes());
            }
            
            if(type == Type.PNG)
            {
                String[] path = location.getPath().split("\\.");
                ResourceLocation new_location = new ResourceLocation(location.getNamespace(), path[0] + "_." + path[1]);
                System.out.println(new_location);
                
                InputStream image = Minecraft.getMinecraft().defaultResourcePack.getInputStream(new_location);
                
                BufferedImage bimage = ImageIO.read(image);
                System.out.println();
                int pixel = bimage.getRGB(0, 0);
                System.out.println(pixel);
                for(int x = 0; x < 16; x++)
                {
                    for(int y = 0; y < 16; y++)
                    {
                        
                        int c = bimage.getRGB(x, y);
                        int a = (c >> 24) & 0xFF;
                        int r = (c >> 16) & 0xFF;
                        int g = (c >>  8) & 0xFF;
                        int b = (c >>  0) & 0xFF;
                        HSLColor hsl = new HSLColor(new Color(c));
                        c = hsl.adjustHue(0).getRGB();
                        c = (a << 24) | (c & 0x00FFFFFF);
                        bimage.setRGB(x, y, c);
                    }
                }
                
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(bimage, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                
                return is;
            }
        }
        try {
            InputStream ret = Minecraft.getMinecraft().defaultResourcePack.getInputStream(location);
            if(ret != null)
                return ret;
        } catch (FileNotFoundException e) {}
        
        return null;
    }

    @Override
    public boolean resourceExists(ResourceLocation location) {
        if (location.getPath().contains("mcmeta") || location.getPath().contains("armature")) {
            return false;
        }
        
        String path = location.getPath();
        for (String item : items) {
            if (path.matches("models/item/.*_" + item + ".json"))
            {
                registry.put(location.toString(), Type.ITEMMODEL);
                return true;
            }
        }
        for (String block : blocks) {
            if (path.matches("models/item/.*_" + block + ".json"))
            {
                registry.put(location.toString(), Type.ITEMBLOCKMODEL);
                return true;
            }
            if (path.matches("models/block/.*_" + block + ".json"))
            {
                registry.put(location.toString(), Type.BLOCKMODEL);
                return true;
            }
            if (path.matches("blockstates/.*_" + block + ".json"))
            {
                registry.put(location.toString(), Type.BLOCKSTATE);
                return true;
            }
        }
        
        return false;
    }
    
    public void registerDomain(String domain) {
        domains.add(domain);
    }

    @Override
    public Set<String> getResourceDomains() {
        return domains;
    }

    @Override
    public <T extends IMetadataSection> T getPackMetadata(MetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
        return null;
    }

    @Override
    public BufferedImage getPackImage() throws IOException {
        return null;
    }

    @Override
    public String getPackName() {
        return "metallurgy5resloader";
    }

}
