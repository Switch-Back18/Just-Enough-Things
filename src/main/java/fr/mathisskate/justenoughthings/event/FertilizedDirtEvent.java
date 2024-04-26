package fr.mathisskate.justenoughthings.event;

import fr.mathisskate.justenoughthings.block.FertilizedDirtBlock;
import fr.mathisskate.justenoughthings.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FertilizedDirtEvent {
    @SubscribeEvent
    public void useHoe(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        if(event.getItemStack().getItem() instanceof HoeItem)
            if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && !state.getValue(FertilizedDirtBlock.TILLED)) {
                event.setResult(Event.Result.ALLOW);
                world.setBlockAndUpdate(pos, state.setValue(FertilizedDirtBlock.TILLED, true));
                world.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(!player.isCreative())
                    stack.hurtAndBreak(1, player, pPlayer -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
            }
    }
    @SubscribeEvent
    public void useShovel(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        if(stack.getItem() instanceof ShovelItem)
            if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && state.getValue(FertilizedDirtBlock.TILLED)) {
                event.setResult(Event.Result.ALLOW);
                world.setBlockAndUpdate(pos, state.setValue(FertilizedDirtBlock.TILLED, false));
                world.playSound(player, pos, SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(!player.isCreative())
                    stack.hurtAndBreak(1, player, pPlayer -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
            }
    }
}
