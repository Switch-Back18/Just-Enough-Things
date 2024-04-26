package fr.mathisskate.justenoughthings.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class MobEvent {
    @SubscribeEvent
    public void tranformSkeletonToWitherEvent(MobSpawnEvent.EntityConstructing event) {
        if(event.getEntity() instanceof Skeleton) {
            Skeleton entity = (Skeleton) event.getEntity();
            Level world = entity.level();
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            if(world.dimension() == Level.NETHER) {
                event.setCanceled(true);
                entity.remove(Entity.RemovalReason.CHANGED_DIMENSION);
                WitherSkeleton witherSkeleton = EntityType.WITHER_SKELETON.create(world);
                witherSkeleton.moveTo(new BlockPos((int)x, (int)y, (int) z), 0, 0);
                world.addFreshEntity(witherSkeleton);
                witherSkeleton.setItemInHand(InteractionHand.MAIN_HAND.MAIN_HAND, new ItemStack(Items.BOW));
            }
        }
    }

    @SubscribeEvent
    public void mobDropsEvent(LivingDropsEvent event) {
        remDrops(event);
    }

    public void remDrops(LivingDropsEvent event) {
        List<ItemEntity> toRemove = new ArrayList<>();
        if (isMobEntity(event.getEntity()))
            for (ItemEntity item : event.getDrops())
                if (isItemBlacklist(item.getItem().getItem()))
                    if (!item.getItem().isEnchanted())
                        toRemove.add(item);
        for(ItemEntity item : toRemove)
            event.getDrops().remove(item);
    }

    private boolean isMobEntity(LivingEntity entity) {
        return (entity instanceof Mob || entity instanceof AmbientCreature);
    }

    private boolean isItemBlacklist(Item item) {
        return item instanceof ProjectileWeaponItem || item instanceof ArmorItem || item instanceof DiggerItem || item instanceof SwordItem;
    }
}
