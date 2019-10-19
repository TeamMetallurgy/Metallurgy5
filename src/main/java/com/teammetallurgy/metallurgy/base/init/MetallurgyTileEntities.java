package com.teammetallurgy.metallurgy.base.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.block.machine.tileentity.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(MetallurgyBase.MOD_ID)
public class MetallurgyTileEntities {

    public static List<TileEntityType> TILE_ENTITIES = Lists.newArrayList();
    public static final TileEntityType<AlloyerTileEntity> ALLOYER = register("alloyer", TileEntityType.Builder.create(AlloyerTileEntity::new, MetallurgyBaseBlock.ALLOYER));
    public static final TileEntityType<BlendingFurnaceTileEntity> BLENDING_FURNACE = register("blending_furnace", TileEntityType.Builder.create(BlendingFurnaceTileEntity::new, MetallurgyBaseBlock.BLENDING_FURNACE));
    public static final TileEntityType<CrucibleTileEntity> CRUCIBLE = register("crucible", TileEntityType.Builder.create(CrucibleTileEntity::new, MetallurgyBaseBlock.CRUCIBLE));
    public static final TileEntityType<ForgeFurnaceTileEntity> FORGE_FURNACE = register("forge_furnace", TileEntityType.Builder.create(ForgeFurnaceTileEntity::new, MetallurgyBaseBlock.FORGE_FURNACE));
    public static final TileEntityType<OreBreakerTileEntity> ORE_BREAKER = register("ore_breaker", TileEntityType.Builder.create(OreBreakerTileEntity::new, MetallurgyBaseBlock.ORE_BREAKER));
    public static final TileEntityType<OreCrusherTileEntity> ORE_CRUSHER = register("ore_crusher", TileEntityType.Builder.create(OreCrusherTileEntity::new, MetallurgyBaseBlock.ORE_CRUSHER));
    public static final TileEntityType<SifterTileEntity> SIFTER = register("sifter", TileEntityType.Builder.create(SifterTileEntity::new, MetallurgyBaseBlock.SIFTER));
    public static final TileEntityType<SiftingTableTileEntity> SIFTING_TABLE = register("sifting_table", TileEntityType.Builder.create(SiftingTableTileEntity::new, MetallurgyBaseBlock.SIFTING_TABLE));
    public static final TileEntityType<SlagPotTileEntity> SLAG_POT = register("slag_pot", TileEntityType.Builder.create(SlagPotTileEntity::new, MetallurgyBaseBlock.SLAG_POT));
    public static final TileEntityType<SubsurfaceDrillTileEntity> SUBSURFACE_DRILL = register("subsurface_drill", TileEntityType.Builder.create(SubsurfaceDrillTileEntity::new, MetallurgyBaseBlock.SUBSURFACE_DRILL));
    public static final TileEntityType<WaterPumpTileEntity> WATER_PUMP = register("water_pump", TileEntityType.Builder.create(WaterPumpTileEntity::new, MetallurgyBaseBlock.WATER_PUMP));
    public static final TileEntityType<WellTileEntity> WELL = register("well", TileEntityType.Builder.create(WellTileEntity::new, MetallurgyBaseBlock.WELL));

    public static <T extends TileEntity> TileEntityType<T> register(@Nonnull String name, @Nonnull TileEntityType.Builder<T> builder) {
        TileEntityType<T> tileEntityType = builder.build(null);
        tileEntityType.setRegistryName(new ResourceLocation(MetallurgyBase.MOD_ID, name));
        TILE_ENTITIES.add(tileEntityType);
        return tileEntityType;
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
        for (TileEntityType tileEntity : TILE_ENTITIES) {
            event.getRegistry().register(tileEntity);
        }
    }

}
