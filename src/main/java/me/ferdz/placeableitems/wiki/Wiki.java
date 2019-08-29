package me.ferdz.placeableitems.wiki;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Wiki {
    String description() default "This block has no description.";
    String model() default "";
    String texture() default "";
}
