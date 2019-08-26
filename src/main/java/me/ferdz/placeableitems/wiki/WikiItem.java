package me.ferdz.placeableitems.wiki;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;

import java.io.File;

public class WikiItem {
    public PlaceableItemsBlock block;
    public String itemName;
    public String description;

    public WikiItem(PlaceableItemsBlock block, String description) {
        this.block = block;
        this.itemName = block.asItem().getRegistryName().getPath();
        this.description = description;

        File f = new File("resources/assets/placeableitems/models/block/");
    }
}
