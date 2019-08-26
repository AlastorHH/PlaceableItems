package me.ferdz.placeableitems.wiki;

import com.google.gson.Gson;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WikiDataGenerator {

    private Class<PlaceableItemsBlockRegistry> registryClass;

    public WikiDataGenerator() {
        this.registryClass = PlaceableItemsBlockRegistry.class;
    }

    public void generate(String filePath) {
        try {
            List<WikiItem> items = new ArrayList<>();
            List<Field> fields = Stream.of(this.registryClass.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Wiki.class))
                    .collect(Collectors.toList());
            for (Field field : fields) {
                field.setAccessible(true);
                Wiki wikiAnnotation = field.getAnnotation(Wiki.class);
                PlaceableItemsBlock block = (PlaceableItemsBlock) field.get(null);
                items.add(new WikiItem(block, wikiAnnotation.description()));
            }
            Gson gson = new Gson();
            System.out.println(gson.toJson(items));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Should never happen
        }
    }

}
