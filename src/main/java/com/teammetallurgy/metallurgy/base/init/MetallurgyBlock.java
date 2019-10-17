package com.teammetallurgy.metallurgy.base.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.MetallurgyBaseMetalPack;
import com.teammetallurgy.metallurgy.base.block.machine.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(MetallurgyBase.MOD_ID)
public class MetallurgyBlock {

    public static List<Block> BLOCKS = Lists.newArrayList();

    public static final Block ALLOYER = register(new AlloyerBlock(), "alloyer");
    public static final Block BLENDING_FURNACE = register(new BlendingFurnaceBlock(), "blending_furnace");
    public static final Block CRUCIBLE = register(new CrucibleBlock(), "crucible");
    public static final Block FORGE_FURNACE = register(new ForgeFurnaceBlock(), "forge_furnace");
    public static final Block ORE_BREAKER = register(new OreBreakerBlock(), "ore_breaker");
    public static final Block ORE_CRUSHER = register(new OreCrusherBlock(), "ore_crusher");
    public static final Block SIFTER = register(new SifterBlock(), "sifter");
    public static final Block SIFTING_TABLE = register(new SiftingTableBlock(), "sifting_table");
    public static final Block SLAG_POT = register(new SlagPotBlock(), "slag_pot");
    public static final Block SUBSURFACE_DRILL = register(new SubsurfaceDrillBlock(), "subsurface_drill");
    public static final Block WATER_PUMP = register(new WaterPumpBlock(), "water_pump");
    public static final Block WELL = register(new WellBlock(), "well");

    private MetallurgyBlock() {
    }

    /**
     * Same as {@link MetallurgyBlock#register(Block, String, Item.Properties)}, but have group set by default
     */
    public static Block register(@Nonnull Block block, @Nonnull String name) {
        return register(block, name, new Item.Properties());
    }

    /**
     * Registers an block with a basic BlockItem
     *
     * @param block The block to be registered
     * @param name  The name to register the block with
     * @return The Block that was registered
     */
    public static Block register(@Nonnull Block block, @Nonnull String name, @Nullable Item.Properties properties) {
        registerBaseBlock(block, name);
        MetallurgyItem.register(new BlockItem(block, properties == null ? new Item.Properties() : properties.group(MetallurgyBaseMetalPack.getInstance().getItemGroup())), name);
        return block;
    }

    public static Block registerBaseBlock(@Nonnull Block block, @Nonnull String name) {
        block.setRegistryName(new ResourceLocation(MetallurgyBase.MOD_ID, name));
        BLOCKS.add(block);
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }
}
