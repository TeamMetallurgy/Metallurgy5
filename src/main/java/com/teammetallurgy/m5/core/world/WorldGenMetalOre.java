package com.teammetallurgy.m5.core.world;

import java.util.Random;

import com.google.common.base.Predicate;
import com.teammetallurgy.m5.core.registry.MetalDefinition;

import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenMetalOre extends WorldGenerator implements IWorldGenerator {

    private final MetalDefinition metal;
    private final IBlockState oreBlock;
    /** The number of blocks to generate. */
    private final int minBlocks;
    private final int maxBlocks;
    private int numberOfBlocks;
    private final Predicate<IBlockState> predicate;

    public WorldGenMetalOre(MetalDefinition metal, IBlockState state, int minBlockCount, int maxBlockCount)
    {
        this(metal, state, minBlockCount, maxBlockCount, new WorldGenMetalOre.StonePredicate());
    }

    public WorldGenMetalOre(MetalDefinition metal, IBlockState state, int minBlockCount, int maxBlockCount, Predicate<IBlockState> p_i45631_3_)
    {
        this.metal = metal;
        this.oreBlock = state;
        this.minBlocks = minBlockCount;
        this.maxBlocks = maxBlockCount;
        this.predicate = p_i45631_3_;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        random.setSeed(random.nextLong() ^ metal.name.hashCode());
        this.numberOfBlocks = random.nextInt(maxBlocks - minBlocks + 1) + minBlocks;
        for (int i = 0; i < metal.veinsPerChunk; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = random.nextInt(metal.maxY - metal.minY + 1) + metal.minY;
            int z = chunkZ * 16 + random.nextInt(16);

            generate(world, random, new BlockPos(x, y, z));
        }
    }
    
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        float f = rand.nextFloat() * (float)Math.PI;
        double minX = (double)((float)(position.getX() + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double maxX = (double)((float)(position.getX() + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double minZ = (double)((float)(position.getZ() + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double maxZ = (double)((float)(position.getZ() + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double minY = (double)(position.getY() + rand.nextInt(3) - 2);
        double maxY = (double)(position.getY() + rand.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i)
        {
            float percentCompleted = (float)i / (float)this.numberOfBlocks;
            double cx = minX + (maxX - minX) * (double)percentCompleted;
            double cy = minY + (maxY - minY) * (double)percentCompleted;
            double cz = minZ + (maxZ - minZ) * (double)percentCompleted;
            double roll = rand.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double d10 = (double)(MathHelper.sin((float)Math.PI * percentCompleted) + 1.0F) * roll + 1.0D;
            double d11 = (double)(MathHelper.sin((float)Math.PI * percentCompleted) + 1.0F) * roll + 1.0D;
            int startX = MathHelper.floor(cx - d10 / 2.0D);
            int startY = MathHelper.floor(cy - d11 / 2.0D);
            int startZ = MathHelper.floor(cz - d10 / 2.0D);
            int endX = MathHelper.floor(cx + d10 / 2.0D);
            int endY = MathHelper.floor(cy + d11 / 2.0D);
            int endZ = MathHelper.floor(cz + d10 / 2.0D);

            for (int x = startX; x <= endX; ++x)
            {
                double distX = ((double)x + 0.5D - cx) / (d10 / 2.0D);

                if (distX * distX < 1.0D)
                {
                    for (int y = startY; y <= endY; ++y)
                    {
                        double distY = ((double)y + 0.5D - cy) / (d11 / 2.0D);

                        if (distX * distX + distY * distY < 1.0D)
                        {
                            for (int z = startZ; z <= endZ; ++z)
                            {
                                double distZ = ((double)z + 0.5D - cz) / (d10 / 2.0D);

                                if (distX * distX + distY * distY + distZ * distZ < 1.0D)
                                {
                                    BlockPos blockpos = new BlockPos(x, y, z);

                                    IBlockState state = worldIn.getBlockState(blockpos);
                                    if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, this.predicate))
                                    {
                                        worldIn.setBlockState(blockpos, this.oreBlock, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    static class StonePredicate implements Predicate<IBlockState>
        {
            private StonePredicate()
            {
            }

            public boolean apply(IBlockState p_apply_1_)
            {
                if (p_apply_1_ != null && p_apply_1_.getBlock() == Blocks.STONE)
                {
                    BlockStone.EnumType blockstone$enumtype = (BlockStone.EnumType)p_apply_1_.getValue(BlockStone.VARIANT);
                    return blockstone$enumtype.isNatural();
                }
                else
                {
                    return false;
                }
            }
        }
}
