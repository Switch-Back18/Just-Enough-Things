package fr.mathisskate.justenoughthings.item;

import fr.mathisskate.justenoughthings.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

import static fr.mathisskate.justenoughthings.util.References.jet_tab;


public class AngelBlockItem extends BlockItem {
    public AngelBlockItem() {
        super(ModBlocks.ANGEL_BLOCK.get(), new Properties().tab(jet_tab));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getPlayer().getLevel();
        double x = player.getX() + 3 * player.getLookAngle().x;
        double y = 1.5 + player.getY() + 3 * player.getLookAngle().y;
        double z = player.getZ() + 3 * player.getLookAngle().z;

        BlockPos pos = new BlockPos(x,y,z);

        if (world.isEmptyBlock(pos) || !world.getFluidState(pos).isEmpty()) {
            world.setBlock(pos, ModBlocks.ANGEL_BLOCK.get().defaultBlockState(),1);
            if (!player.isCreative())
                player.getInventory().getSelected().shrink(1);
        }
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()){
            double x = player.getX() + 3 * player.getLookAngle().x;
            double y = 1.5 + player.getY() + 3 * player.getLookAngle().y;
            double z = player.getZ() + 3 * player.getLookAngle().z;

            BlockPos pos = new BlockPos(x,y,z);

            if (world.isEmptyBlock(pos) || !world.getFluidState(pos).isEmpty()) {
                world.setBlockAndUpdate(pos, ModBlocks.ANGEL_BLOCK.get().defaultBlockState());
                if (!player.isCreative())
                    player.getItemInHand(hand).shrink(1);
            }
        }
        return super.use(world, player, hand);
    }
}
