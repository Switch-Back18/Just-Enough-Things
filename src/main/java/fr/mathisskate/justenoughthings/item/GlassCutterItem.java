package fr.mathisskate.justenoughthings.item;

import fr.mathisskate.justenoughthings.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public class GlassCutterItem extends Item {

    public GlassCutterItem() {
        super(new Item.Properties().stacksTo(1).durability(250));
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos pos, LivingEntity entity) {
        if(!level.isClientSide()) {
            itemStack.hurtAndBreak(1, entity, (player) -> {
                player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            if(entity instanceof Player player) {
                ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockState.getBlock()));
                if (!player.isCreative() && Utils.isGlass(blockState))
                    level.addFreshEntity(itemEntity);
            }
        }
        return super.mineBlock(itemStack, level, blockState, pos, entity);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState blockState) {
        return Utils.isGlass(blockState);
    }

    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return Utils.isGlass(blockState) ? 10.0F : super.getDestroySpeed(itemStack, blockState);
    }
}
