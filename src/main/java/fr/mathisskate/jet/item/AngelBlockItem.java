package fr.mathisskate.jet.item;

import fr.mathisskate.jet.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;

public class AngelBlockItem extends BlockItem {
    public AngelBlockItem() {
        super(ModBlocks.ANGEL_BLOCK.get(), new Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("jet", "angel_block"))));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
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