package fr.mathisskate.justenoughthings.tileentity;

/*import fr.mathisskate.justenoughthings.registry.ModItems;
import fr.mathisskate.justenoughthings.registry.ModTilesEntities;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemCollectorTile extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    int currentTickRate = 20;
    int counter = 0;
    public ItemCollectorTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ItemCollectorTile() {
        super(ModTilesEntities.ITEM_COLLECTOR_TILE.get());
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() == ModItems.FILTER_ITEM.get();
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if(!world.isRemote()) {
            counter++;
            if(counter >= currentTickRate) {
                counter = 0;

                List<ItemEntity> entityItemList = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(pos.add(3, 3, 3), pos.add(4, 4, 4)));
                boolean didSomething = false;
                if(!entityItemList.isEmpty()) {
                    System.out.println(world.getBlockState(pos).getValues());
                    //TileEntity tileEntity = world.getTileEntity(pos.offset(facing.getOpposite()));
                }

            }
        }
    }
}*/
