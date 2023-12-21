package fr.mathisskate.justenoughthings.event;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class MobEvent {
    @SubscribeEvent
    public void tranformSkeletonToWitherEvent(LivingSpawnEvent.SpecialSpawn event) {
        if(event.getEntity() instanceof Skeleton) {
            Skeleton entity = (Skeleton) event.getEntity();
            Level world = entity.getLevel();
            double x = entity.getX();
            double y = event.getY();
            double z = event.getZ();
            if(world.dimension() == Level.NETHER) {
                event.setCanceled(true);
                entity.remove(Entity.RemovalReason.CHANGED_DIMENSION);
                WitherSkeleton witherSkeleton = EntityType.WITHER_SKELETON.create(world);
                witherSkeleton.moveTo(new BlockPos(x, y, z), 0, 0);
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
        if (isMobEntity(event.getEntityLiving()))
            for (ItemEntity item : event.getDrops())
                if (isTools(item.getItem()) || isArmor(item.getItem()) || isSword(item.getItem()) || isBow(item.getItem().getItem()))
                    if (!item.getItem().isEnchanted())
                        toRemove.add(item);
        for(ItemEntity item : toRemove)
            event.getDrops().remove(item);
    }

    private boolean isMobEntity(LivingEntity entity) {
        return (entity instanceof Mob || entity instanceof AmbientCreature);
    }

    private boolean isBow(Item item) {
        return item == Items.BOW;
    }

    private boolean isArmor(ItemStack item) {
        TagKey<Item> armor = ItemTags.create(new ResourceLocation("forge", "armor"));
        if (armor != null)
            return item.is(armor);
        return false;
    }

    private boolean isTools(ItemStack item) {
        TagKey<Item> tool = ItemTags.create(new ResourceLocation("forge", "tools"));
        if (tool != null)
            return item.is(tool);
        return false;
    }

    private boolean isSword(ItemStack item) {
        TagKey<Item> sword = ItemTags.create(new ResourceLocation("forge", "swords"));
        if (sword != null)
            return item.is(sword);
        return false;
    }
}
