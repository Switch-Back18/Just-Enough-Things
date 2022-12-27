package fr.mathisskate.justenoughthings.item;

import fr.mathisskate.justenoughthings.registry.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import static fr.mathisskate.justenoughthings.util.References.jet_Group;

public class AngelBlockItem extends BlockItem {
    public AngelBlockItem() {
        super(ModBlocks.ANGEL_BLOCK.get(), new Properties().group(jet_Group));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        if (!world.isRemote){
            double x = player.getPosX() + 3 * player.getLookVec().x;
            double y = 1.5 + player.getPosY() + 3 * player.getLookVec().y;
            double z = player.getPosZ() + 3 * player.getLookVec().z;

            BlockPos pos = new BlockPos(x,y,z);

            if (world.isAirBlock(pos) || !world.getFluidState(pos).isEmpty()) {
                world.setBlockState(pos, ModBlocks.ANGEL_BLOCK.get().getDefaultState());
                if (!player.isCreative())
                    player.getHeldItem(hand).shrink(1);
            }
        }
        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }
}
