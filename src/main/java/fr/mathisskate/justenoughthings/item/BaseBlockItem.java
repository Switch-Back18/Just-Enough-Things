package fr.mathisskate.justenoughthings.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import static fr.mathisskate.justenoughthings.util.References.jet_Group;

public class BaseBlockItem extends BlockItem {
    public BaseBlockItem(Block block) {
        super(block, new Item.Properties().group(jet_Group));
    }
}