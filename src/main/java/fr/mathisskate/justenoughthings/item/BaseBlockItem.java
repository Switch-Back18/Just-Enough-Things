package fr.mathisskate.justenoughthings.item;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static fr.mathisskate.justenoughthings.util.References.jet_tab;

public class BaseBlockItem extends BlockItem {
    public BaseBlockItem(Block block) {
        super(block, new Item.Properties().tab(jet_tab));
    }
}