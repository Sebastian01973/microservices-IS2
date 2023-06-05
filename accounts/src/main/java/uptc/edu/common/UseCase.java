package uptc.edu.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {

    // Solo un alias para abstraernos de Spring Framework
    @AliasFor(annotation = Component.class)
    String value() default "";
}
