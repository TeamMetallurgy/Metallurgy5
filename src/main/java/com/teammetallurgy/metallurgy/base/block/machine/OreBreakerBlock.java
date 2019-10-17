package com.teammetallurgy.metallurgy.base.block.machine;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.OreBreakerTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class OreBreakerBlock extends ContainerBlock {

    public OreBreakerBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13));
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote) {
            INamedContainerProvider container = this.getContainer(state, world, pos);
            if (container != null && player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                if (!player.isSneaking()) {
                    NetworkHooks.openGui(serverPlayer, container, pos);
                }
            }
        }
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new OreBreakerTileEntity();
    }
}