package com.teammetallurgy.m5.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.teammetallurgy.m5.core.MetallurgyCore;

public class JSONMaker {

    private static String blockstateJson = "{\r\n" + "    \"variants\": {\r\n" + "        \"normal\": { \"model\": \"%modid%:%name%\" }\r\n" + "    }\r\n" + "}";

    private static String blockModelJson = "{\r\n" + "    \"parent\": \"block/cube_all\",\r\n" + "    \"textures\": {\r\n" + "        \"all\": \"%modid%:blocks/%name%\"\r\n" + "    }\r\n" + "}";

    private static String itemblockModelJson = "{\r\n" + "  \"parent\": \"%modid%:block/%name%\"\r\n" + "}";

    private static String itemModelJson = "{\r\n" + "    \"parent\": \"item/generated\",\r\n" + "    \"textures\": {\r\n" + "        \"layer0\": \"%modid%:items/%name%\"\r\n" + "    }\r\n" + "}";

    public static void createItemJson(String modid, String name) {
        if(!MetallurgyCore.createJson)
            return;
        String path = "../src/main/resources/assets";
        File file = new File(path + "/" + modid + "/models/item/" + name + ".json");
        if (file.exists())
            return;

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(getItemModelString(modid, name));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBlockJson(String modid, String name) {
        if(!MetallurgyCore.createJson)
            return;
        String path = "../src/main/resources/assets";
        File file = new File(path + "/" + modid + "/models/block/" + name + ".json");
        if (file.exists())
            return;

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(getBlockModelString(modid, name));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        path = "../src/main/resources/assets";
        file = new File(path + "/" + modid + "/blockstates/" + name + ".json");
        if (file.exists())
            return;

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(getBlockstateString(modid, name));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        path = "../src/main/resources/assets";
        file = new File(path + "/" + modid + "/models/item/" + name + ".json");
        if (file.exists())
            return;

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(getItemModelString(modid, name));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getItemModelString(String modid, String name) {
        return itemModelJson.replace("%modid%", modid).replace("%name%", name);
    }
    
    public static String getBlockModelString(String modid, String name) {
        return blockModelJson.replace("%modid%", modid).replace("%name%", name);
    }
    
    public static String getBlockstateString(String modid, String name) {
        return blockstateJson.replace("%modid%", modid).replace("%name%", name);
    }
    
    public static String getItemBlockModelString(String modid, String name) {
        return itemblockModelJson.replace("%modid%", modid).replace("%name%", name);
    }
}
