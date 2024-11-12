package fr.switchback.jet.item;

import fr.switchback.jet.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AngelBlockItem extends BlockItem {
    public AngelBlockItem() {
        super(ModBlocks.ANGEL_BLOCK.get(), new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()){
            double x = player.getX() + 5 * player.getLookAngle().x;
            double y = 1.5 + player.getY() + 5 * player.getLookAngle().y;
            double z = player.getZ() + 5 * player.getLookAngle().z;

            BlockPos pos = new BlockPos((int) x, (int) y, (int) z);

            if (level.isEmptyBlock(pos) || !level.getFluidState(pos).isEmpty()) {
                level.setBlockAndUpdate(pos, ModBlocks.ANGEL_BLOCK.get().defaultBlockState());
                if (!player.isCreative())
                    player.getItemInHand(hand).shrink(1);
            }
        }
        return super.use(level, player, hand);
    }
}