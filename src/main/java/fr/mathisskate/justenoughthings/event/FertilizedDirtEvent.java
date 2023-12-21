package fr.mathisskate.justenoughthings.event;

import fr.mathisskate.justenoughthings.block.FertilizedDirtBlock;
import fr.mathisskate.justenoughthings.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;

public class FertilizedDirtEvent {
    @SubscribeEvent
    public void useHoe(UseHoeEvent event) {
        UseOnContext context = event.getContext();
        Player player = event.getPlayer();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && !state.getValue(FertilizedDirtBlock.TILLED)) {
            event.setResult(Event.Result.ALLOW);
            world.setBlockAndUpdate(pos, state.setValue(FertilizedDirtBlock.TILLED, true));
            world.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
    @SubscribeEvent
    public void useShovel(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        Level world = event.getWorld();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        TagKey<Item> shovels = ItemTags.create(new ResourceLocation("forge", "shovels"));
        if(stack.is(shovels))
            if (state.getBlock().equals(ModBlocks.FERTILIZED_DIRT_BLOCK.get()) && state.getValue(FertilizedDirtBlock.TILLED)) {
                event.setResult(Event.Result.ALLOW);
                world.setBlockAndUpdate(pos, state.setValue(FertilizedDirtBlock.TILLED, false));
                world.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
    }

}
