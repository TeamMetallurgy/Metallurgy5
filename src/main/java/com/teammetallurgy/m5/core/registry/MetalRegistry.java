package com.teammetallurgy.m5.core.registry;

import com.teammetallurgy.m5.core.MetalSet;
import com.teammetallurgy.m5.core.registry.data.WorldData;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;


public class MetalRegistry {

    public List<MetalDefinition> registry = new ArrayList<>();
    public List<Block> blocks = new ArrayList<>();
    public List<Item> items = new ArrayList<>();

    private boolean init;
    private final MetalSet pack;

    public MetalRegistry(MetalSet pack) {
        this.pack = pack;
    }

    public void registerMetal(MetalDefinition metal) {
        registry.add(metal);
    }

    public MetalDefinition getMetal(String name) {
        for (MetalDefinition metal : registry) {
            if (name.equals(metal.data.name))
                return metal;
        }
        return null;
    }

    private void init() {
        for (MetalDefinition metal : registry) {
            metal.createItems();
            this.blocks.addAll(metal.blocks);
            this.items.addAll(metal.items);
        }
        this.init = true;
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        if (!this.init)
            init();

        registerItemBlocks(event.getRegistry());
        registerItems(event.getRegistry());
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        if (!this.init)
            init();

        registerBlocks(event.getRegistry());
    }

    public void registerWorldGen() {
        for (MetalDefinition metal : registry) {
            if (metal.ORE == null)
                continue;

            WorldData worldGen = metal.data.world;
            Block oreBlock = metal.ORE;

            //TODO Add types

            Biome.BIOMES.forEach(biome -> {
                if (biome.getRegistryName().getNamespace().equalsIgnoreCase("minecraft")) {
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, oreBlock.getDefaultState(), worldGen.orePerVein), Placement.COUNT_RANGE, new CountRangeConfig(worldGen.veinsPerChunk, worldGen.heightMin, 0, worldGen.heightMax)));
                }
            });
        }
    }

    // ********************
    // * HELPER FUNCTIONS *
    // ********************
    public void registerItems(IForgeRegistry<Item> registry) {
        for (Item item : items) {
            registry.register(item);
        }
    }

    public void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (Block block : blocks) {
            Item itemblock = new BlockItem(block, new Item.Properties().group(pack.getItemGroup())).setRegistryName(block.getRegistryName());
            registry.register(itemblock);
        }
    }

    public void registerBlocks(IForgeRegistry<Block> registry) {
        for (Block block : blocks) {
            registry.register(block);
        }
    }
}
