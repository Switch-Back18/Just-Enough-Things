package fr.mathisskate.justenoughthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;

public class AngelBlock extends Block {

    public AngelBlock() {
        super(Properties.of().mapColor(MapColor.STONE).strength(1.0F).sound(SoundType.STONE));
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!player.isCreative()){
            player.getInventory().placeItemBackInInventory(new ItemStack(this));
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
