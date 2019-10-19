package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.google.common.collect.Maps;
import com.teammetallurgy.metallurgy.base.block.machine.BlendingFurnaceBlock;
import com.teammetallurgy.metallurgy.base.crafting.RecipeTypes;
import com.teammetallurgy.metallurgy.base.crafting.recipe.BlendingFurnaceRecipe;
import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.BlendingFurnaceContainer;
import com.teammetallurgy.metallurgy.base.utils.RecipeUtils;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.Map;

public class BlendingFurnaceTileEntity extends LockableTileEntity implements IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    protected NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;

    protected final IIntArray furnaceData = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return BlendingFurnaceTileEntity.this.burnTime;
                case 1:
                    return BlendingFurnaceTileEntity.this.recipesUsed;
                case 2:
                    return BlendingFurnaceTileEntity.this.cookTime;
                case 3:
                    return BlendingFurnaceTileEntity.this.cookTimeTotal;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    BlendingFurnaceTileEntity.this.burnTime = value;
                    break;
                case 1:
                    BlendingFurnaceTileEntity.this.recipesUsed = value;
                    break;
                case 2:
                    BlendingFurnaceTileEntity.this.cookTime = value;
                    break;
                case 3:
                    BlendingFurnaceTileEntity.this.cookTimeTotal = value;
            }

        }

        public int size() {
            return 4;
        }
    };
    private final Map<ResourceLocation, Integer> field_214022_n = Maps.newHashMap();

    public BlendingFurnaceTileEntity() {
        super(MetallurgyTileEntities.BLENDING_FURNACE);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.blending_furnace");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new BlendingFurnaceContainer(id, this.pos, player, furnaceData);
    }

    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag) {
            this.cookTimeTotal = this.getCookTime();
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.items) {
            helper.accountStack(itemstack);
        }
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null) {
            this.field_214022_n.compute(recipe.getId(), ((resourceLocation, p_214004_1_) -> {
                return 1 + (p_214004_1_ == null ? 0 : p_214004_1_);
            }));
        }
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack fuelSlot = this.items.get(3);
            if (this.isBurning() || !fuelSlot.isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.BLENDING_FURNACE, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    this.burnTime = this.getBurnTime(fuelSlot);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (fuelSlot.hasContainerItem())
                            this.items.set(1, fuelSlot.getContainerItem());
                        else if (!fuelSlot.isEmpty()) {
                            fuelSlot.shrink(1);
                            if (fuelSlot.isEmpty()) {
                                this.items.set(1, fuelSlot.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTime();
                        this.setOutput(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(BlendingFurnaceBlock.LIT, this.isBurning()), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        if (recipeIn != null) {
            return RecipeUtils.canSmelt(this.items.get(4), recipeIn.getRecipeOutput());
        } else {
            return false;
        }
    }

    private void setOutput(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack output = recipe.getRecipeOutput();
            ItemStack outputSlot = this.items.get(4);

            for (int i = 0; i < 3; i++) {
                ItemStack itemStack = this.items.get(i);
                if (!itemStack.isEmpty()) {
                    itemStack.shrink(1);
                }
            }

            if (outputSlot.isEmpty()) {
                this.items.set(4, output.copy());
            } else if (outputSlot.getItem() == output.getItem()) {
                outputSlot.grow(output.getCount());
            }

            this.setRecipeUsed(recipe);
        }
    }

    protected int getBurnTime(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return 0;
        } else {
            return net.minecraftforge.common.ForgeHooks.getBurnTime(itemStack);
        }
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    protected int getCookTime() {
        return this.world.getRecipeManager().getRecipe(RecipeTypes.BLENDING_FURNACE, this, this.world).map(BlendingFurnaceRecipe::getCookTime).orElse(200);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = this.getBurnTime(this.items.get(1));
        int i = compound.getShort("RecipesUsedSize");

        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.field_214022_n.put(resourcelocation, k);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        ItemStackHelper.saveAllItems(compound, this.items);
        compound.putShort("RecipesUsedSize", (short) this.field_214022_n.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.field_214022_n.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return compound;
    }
}