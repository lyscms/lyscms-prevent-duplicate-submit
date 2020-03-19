package info.lyscms.assembly.annotation;

import info.lyscms.assembly.config.PreventRepeatImportSelector;
import info.lyscms.assembly.strategy.PreventStrategy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {PreventRepeatImportSelector.class})
@Documented
public @interface EnableLyscmsPreventRepeated {

    /**
     * 外部传入策略
     */
    PreventStrategy strategy() default PreventStrategy.POST;
}
