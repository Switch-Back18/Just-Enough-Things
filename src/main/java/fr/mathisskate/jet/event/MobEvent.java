package fr.mathisskate.jet.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class MobEvent {

    @SubscribeEvent
    public static void tranformSkeletonToWitherEvent(EntityJoinLevelEvent event) {
            if(event.getEntity() instanceof Skeleton skeleton && !skeleton.isRemoved()) {
                Level world = skeleton.level();
                if(world.dimension() == Level.NETHER && !world.isClientSide()) {
                    event.setCanceled(true);
                    skeleton.convertTo(EntityType.WITHER_SKELETON, new ConversionParams(ConversionType.SINGLE, true, false, null), EntitySpawnReason.CONVERSION, witherSkeleton -> {
                        witherSkeleton.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
                    });
                }
            }
    }

    @SubscribeEvent
    public static void mobDropsEvent(LivingDropsEvent event) {
        remDrops(event);
    }

    public static void remDrops(LivingDropsEvent event) {
        List<ItemEntity> toRemove = new ArrayList<>();
        if (isMobEntity(event.getEntity()))
            for (ItemEntity item : event.getDrops())
                if (isItemBlacklist(item.getItem().getItem()))
                    if (!item.getItem().isEnchanted())
                        toRemove.add(item);
        for(ItemEntity item : toRemove)
            event.getDrops().remove(item);
    }
    
    private static boolean isMobEntity(LivingEntity entity) {
        return (entity instanceof Mob || entity instanceof AmbientCreature);
    }

    private static boolean isItemBlacklist(Item item) {
        return item instanceof ProjectileWeaponItem || item instanceof ArmorItem || item instanceof DiggerItem || item instanceof SwordItem;
    }
}
