package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.impl.BoneBlockComponent;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import me.ferdz.placeableitems.wiki.Wiki;
import me.ferdz.placeableitems.wiki.WikiDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// TODO: Make this class streamlined for registration

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlaceableItemsBlockRegistry {

    private static final boolean GENERATE_WIKI = System.getenv().containsKey("GENERATE_WIKI");

    @Wiki(description = "A bone")
    public static PlaceableItemsBlock BONE;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        BONE = new PlaceableItemsBlock()
                .setShape(VoxelShapesUtil.create(0, 0, 0, 16, 4, 16))
                .addComponent(new BoneBlockComponent())
                .register("bone_block", Items.BONE);

        if(GENERATE_WIKI) {
            WikiDataGenerator generator = new WikiDataGenerator();
            generator.generate("test.json");
        }
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        // Keep this for debugging purposes to use an ItemBlock
        // event.getRegistry().register(new BlockItem(BONE, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(BONE.getRegistryName()));
    }
}
