package com.teammetallurgy.metallurgy.base.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.inventory.container.*;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(MetallurgyBase.MOD_ID)
public class MetallurgyGuis {
    public static List<ContainerType> CONTAINERS = Lists.newArrayList();
    public static final ContainerType<AlloyerContainer> ALLOYER = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new AlloyerContainer(windowID, pos, inv);
    }), "alloyer");
    public static final ContainerType<BlendingFurnaceContainer> BLENDING_FURNACE = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new BlendingFurnaceContainer(windowID, pos, inv);
    }), "blending_furnace");
    public static final ContainerType<CrucibleContainer> CRUCIBLE = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new CrucibleContainer(windowID, pos, inv);
    }), "crucible");
    public static final ContainerType<ForgeFurnaceContainer> FORGE_FURNACE = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new ForgeFurnaceContainer(windowID, pos, inv);
    }), "forge_furnace");
    public static final ContainerType<OreBreakerContainer> ORE_BREAKER = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new OreBreakerContainer(windowID, pos, inv);
    }), "ore_break");
    public static final ContainerType<OreCrusherContainer> ORE_CRUSHER = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new OreCrusherContainer(windowID, pos, inv);
    }), "ore_crusher");
    public static final ContainerType<SifterContainer> SIFTER = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new SifterContainer(windowID, pos, inv);
    }), "sifter");
    public static final ContainerType<SiftingTableContainer> SIFTING_TABLE = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new SiftingTableContainer(windowID, pos, inv);
    }), "sifting_table");
    public static final ContainerType<SlagPotContainer> SLAG_POT = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new SlagPotContainer(windowID, pos, inv);
    }), "slag_pot");
    public static final ContainerType<SubsurfaceDrillContainer> SUBSURFACE_DRILL = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new SubsurfaceDrillContainer(windowID, pos, inv);
    }), "subsurface_drill");
    public static final ContainerType<WaterPumpContainer> WATER_PUMP = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new WaterPumpContainer(windowID, pos, inv);
    }), "water_pump");
    public static final ContainerType<WellContainer> WELL = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new WellContainer(windowID, pos, inv);
    }), "well");


    private static <T extends Container> ContainerType<T> register(@Nonnull ContainerType<T> container, @Nonnull String name) {
        container.setRegistryName(new ResourceLocation(MetallurgyBase.MOD_ID, name));
        CONTAINERS.add(container);
        return container;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        for (ContainerType container : CONTAINERS) {
            event.getRegistry().register(container);
        }
    }
}
