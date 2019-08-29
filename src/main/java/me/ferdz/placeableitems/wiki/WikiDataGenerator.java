package me.ferdz.placeableitems.wiki;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                items.add(new WikiItem(block, wikiAnnotation));
            }
            Gson gson = new Gson();

            boolean created = new File("../docs/assets/textures").mkdirs();
            created = new File("../docs/assets/models").mkdirs();

            Files.write(Paths.get("../docs/assets/", filePath), gson.toJson(items).getBytes(Charsets.UTF_8));
            for(WikiItem item : items) {
                Files.write(Paths.get("../docs/assets/textures", item.itemName + ".png"), item.texture);
                Files.write(Paths.get("../docs/assets/models", item.itemName + ".json"),
                        gson.toJson(item.model).getBytes(Charsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Should never happen
        }
    }

}
