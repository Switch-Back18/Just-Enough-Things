package fr.mathisskate.justenoughthings.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AngelBlock extends Block {

    public AngelBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.IRON).hardnessAndResistance(1.0F).sound(SoundType.STONE));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(world, pos, state, player);
        if (!world.isRemote && !player.abilities.isCreativeMode){
            player.addItemStackToInventory(new ItemStack(this));
        }
    }
}
