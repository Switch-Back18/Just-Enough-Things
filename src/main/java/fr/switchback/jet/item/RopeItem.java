package fr.switchback.jet.item;

import fr.switchback.jet.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class RopeItem extends Item {
    public RopeItem() {
        super(new Properties().stacksTo(1).durability(20));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockPos pos = new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ());
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCrouching() && !level.canSeeSky(pos) && level.getSeaLevel() > player.getY()) {
            BlockPos top = Utils.getTop(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ()), level);
            if(!level.isClientSide) {
                player.teleportTo(top.getX() + .5, top.getY(), top.getZ() + .5);
                stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(player.getItemInHand(hand)));
            }
            level.playSound(player, top, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Teleport to the top when crouching in a cave").withColor(9861240));
    }
}
