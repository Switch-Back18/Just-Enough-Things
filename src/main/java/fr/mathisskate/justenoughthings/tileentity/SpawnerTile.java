package fr.mathisskate.justenoughthings.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;

public class SpawnerTile extends MobSpawnerTileEntity {
    private Entity entity;
    private PlayerEntity player;

    private MobSpawnerTileEntity old_spawner;

    public SpawnerTile() {
        super();
    }

    public SpawnerTile(PlayerEntity player, Entity entity) {
        super();
        this.player = player;
        this.entity = entity;
    }
}
