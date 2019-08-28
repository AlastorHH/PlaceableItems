package me.ferdz.placeableitems.wiki;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import me.ferdz.placeableitems.PlaceableItems;
import org.apache.commons.io.IOUtils;

public class WikiItem {
    public static final ObjectIntIdentityMap<BlockState> BLOCK_STATE_IDS = net.minecraftforge.registries.GameData.getBlockStateIDMap();

    public transient PlaceableItemsBlock block;
    public String itemName;
    public String description;

    public Map model;
    public transient byte[] texture;

    public WikiItem(PlaceableItemsBlock block, Wiki wiki) throws IOException {
        this.block = block;
        this.itemName = block.asItem().getRegistryName().getPath();
        this.description = wiki.description();

        String wikiModel = wiki.model().equals("") ? this.itemName : wiki.model();
        String wikiTexture = wiki.texture().equals("") ? this.itemName : wiki.texture();

        this.model = this.modelMap(wikiModel);
        this.texture = this.rawTexture(wikiTexture);
    }

    private Map modelMap(String model) throws IOException {
        InputStream inputStream = Minecraft.getInstance().getResourceManager()
                .getResource(new ResourceLocation(PlaceableItems.MODID, "models/block/" + model + ".json")).getInputStream();
        return new Gson().fromJson(IOUtils.toString(inputStream, Charsets.UTF_8), Map.class);
    }

    private byte[] rawTexture(String texture) throws IOException {
        InputStream inputStream = Minecraft.getInstance().getResourceManager()
                .getResource(new ResourceLocation(PlaceableItems.MODID, "textures/block/" + texture + ".png")).getInputStream();
        return IOUtils.toByteArray(inputStream);
    }
}
