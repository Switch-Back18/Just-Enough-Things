package fr.mathisskate.justenoughthings.event;

import fr.mathisskate.justenoughthings.block.FertilizedDirtBlock;
import fr.mathisskate.justenoughthings.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FertilizedDirtEvent {
    @SubscribeEvent
    public void useHoe(UseHoeEvent event) {
        ItemUseContext context = event.getContext();
        PlayerEntity player = event.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && !state.get(FertilizedDirtBlock.TILLED)) {
            event.setResult(Event.Result.ALLOW);
            world.setBlockState(pos, state.with(FertilizedDirtBlock.TILLED, true));
            world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

}
