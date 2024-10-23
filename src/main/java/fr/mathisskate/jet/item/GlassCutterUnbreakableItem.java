package fr.mathisskate.jet.item;

import fr.mathisskate.jet.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class GlassCutterUnbreakableItem extends Item {
    public GlassCutterUnbreakableItem() {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("jet", "glass_cutter_unbreakable"))).stacksTo(1));
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos pos, LivingEntity entity) {
        if(!level.isClientSide()) {
            itemStack.hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(entity.getUseItem()));
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("You can recover glass block with it").withColor(3315455));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return false;
    }
}
