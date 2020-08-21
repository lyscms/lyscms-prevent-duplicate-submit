package com.lyscms.assembly.annotation;

import com.lyscms.assembly.config.PreventRepeatImportSelector;
import com.lyscms.assembly.strategy.PreventStrategy;
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
