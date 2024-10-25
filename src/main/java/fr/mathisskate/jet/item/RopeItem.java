package fr.mathisskate.jet.item;

import fr.mathisskate.jet.JustEnoughThings;
import fr.mathisskate.jet.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("jet", "rope"))).stacksTo(1).durability(20));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        BlockPos pos = new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ());
        if (player.isCrouching() && !level.canSeeSky(pos) && level.getSeaLevel() > player.getY()) {
            BlockPos top = Utils.getTop(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ()), level);
            ItemStack stack = player.getItemInHand(hand);
            player.teleportTo(top.getX() + .5, top.getY(), top.getZ() + .5);
            stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(player.getItemInHand(hand)));
            player.makeSound(SoundEvents.PLAYER_TELEPORT);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Teleport to the top when crouching in a cave").withColor(9861240));
    }
}
