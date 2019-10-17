package com.teammetallurgy.metallurgy.base.block;

import com.teammetallurgy.metallurgy.base.registry.MetalDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CatalystOreBlock extends Block {
    private MetalDefinition metal;

    public CatalystOreBlock(MetalDefinition metal) {
        super(Block.Properties.create(Material.ROCK, Material.ROCK.getColor()));
        this.metal = metal;
    }

//    TODO
//    /**
//     * Get the Item that this Block should drop when harvested.
//     */
//    public Item getItemDropped(BlockState state, Random rand, int fortune)
//    {
//        return MetalRegistry.catalysts.get(metal.name);
//    }

//    TODO
    /**
     * Returns the quantity of items to drop on block destruction.
     */
//    public int quantityDropped(Random random)
//    {
//        return 1;
//    }

//    TODO
//    /**
//     * Get the quantity dropped based on the given fortune level
//     */
//    public int quantityDroppedWithBonus(int fortune, Random random)
//    {
//        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getStateContainer().getValidStates().iterator().next(), random, fortune))
//        {
//            int i = random.nextInt(fortune + 2) - 1;
//
//            if (i < 0)
//            {
//                i = 0;
//            }
//
//            return this.quantityDropped(random) * (i + 1);
//        }
//        else
//        {
//            return this.quantityDropped(random);
//        }
//    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
//    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, BlockState state, float chance, int fortune)
//    {
//        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
//    }

//    TODO
//    @Override
//    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
//        Random rand = world instanceof World ? ((World) world).rand : new Random();
//        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
//            int i = MathHelper.nextInt(rand, 0, 2);
//
//            return i;
//        }
//        return 0;
//    }

//    @Override
//    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
//        return new ItemStack(this);
//    }

//    TODO
//    /**
//     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
//     * returns the metadata of the dropped item based on the old metadata of the block.
//     */
//    public int damageDropped(BlockState state)
//    {
//        return 0;
//    }
}