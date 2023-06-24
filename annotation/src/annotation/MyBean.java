package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: MyBean
 * Package: annotation
 * Description:
 *
 * @Author AQiu
 * @Create 2023/1/8 8:28 AM
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface MyBean {
     String value();
}
