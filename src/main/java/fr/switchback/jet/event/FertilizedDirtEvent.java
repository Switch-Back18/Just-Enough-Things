package fr.switchback.jet.event;

import fr.switchback.jet.block.FertilizedDirtBlock;
import fr.switchback.jet.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class FertilizedDirtEvent {

   @SubscribeEvent
    public static void useHoe(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        if(event.getItemStack().getItem() instanceof HoeItem)
            if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && !state.getValue(FertilizedDirtBlock.TILLED)) {
                event.setCancellationResult(InteractionResult.SUCCESS);
                world.setBlockAndUpdate(pos, state.setValue(FertilizedDirtBlock.TILLED, true));
                world.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(!player.isCreative())
                    stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(player.getUseItem()));
            }
    }

    @SubscribeEvent
    public static void useShovel(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        if(stack.getItem() instanceof ShovelItem)
            if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && state.getValue(FertilizedDirtBlock.TILLED)) {
                event.setCancellationResult(InteractionResult.SUCCESS);
                world.setBlockAndUpdate(pos, state.setValue(FertilizedDirtBlock.TILLED, false));
                world.playSound(player, pos, SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(!player.isCreative())
                    stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(player.getUseItem()));
            }
    }
}
