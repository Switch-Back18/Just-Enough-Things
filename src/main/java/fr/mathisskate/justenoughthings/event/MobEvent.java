package fr.mathisskate.justenoughthings.event;

import net.minecraft.entity.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
        if(event.getEntity() instanceof SkeletonEntity) {
            SkeletonEntity entity = (SkeletonEntity) event.getEntity();
            World world = entity.getEntityWorld();
            double x = entity.getPosX();
            double y = event.getY();
            double z = event.getZ();
            if(world.getDimensionKey() == World.THE_NETHER) {
                event.setCanceled(true);
                entity.remove();
                WitherSkeletonEntity witherSkeleton = EntityType.WITHER_SKELETON.create(world);
                witherSkeleton.moveToBlockPosAndAngles(new BlockPos(x, y, z), 0, 0);
                world.addEntity(witherSkeleton);
                witherSkeleton.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BOW));
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
                if (isTools(item.getItem().getItem()) || isArmor(item.getItem().getItem()) || isSword(item.getItem().getItem()) || isBow(item.getItem().getItem()))
                    if (!item.getItem().isEnchanted())
                        toRemove.add(item);
        for(ItemEntity item : toRemove)
            event.getDrops().remove(item);
    }

    private boolean isMobEntity(LivingEntity entity) {
        return (entity instanceof MobEntity || entity instanceof CreatureEntity);
    }

    private boolean isBow(Item item) {
        return item == Items.BOW;
    }

    private boolean isArmor(Item item) {
        ITag<Item> armor = ItemTags.getCollection().get(new ResourceLocation("forge", "armor"));
        if (armor != null)
            return item.isIn(armor);
        return false;
    }

    private boolean isTools(Item item) {
        ITag<Item> tool = ItemTags.getCollection().get(new ResourceLocation("forge", "tools"));
        if (tool != null)
            return item.isIn(tool);
        return false;
    }

    private boolean isSword(Item item) {
        ITag<Item> sword = ItemTags.getCollection().get(new ResourceLocation("forge", "swords"));
        if (sword != null)
            return item.isIn(sword);
        return false;
    }
}
