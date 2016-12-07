package authoring.model;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Hide {
//	public boolean Hidden() default true;
}
