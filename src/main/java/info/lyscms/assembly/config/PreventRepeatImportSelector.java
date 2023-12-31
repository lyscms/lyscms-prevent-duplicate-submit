package info.lyscms.assembly.config;


import info.lyscms.assembly.annotation.EnableLyscmsPreventRepeated;
import info.lyscms.assembly.config.qualifier.AllQualifier;
import info.lyscms.assembly.config.qualifier.GetQualifier;
import info.lyscms.assembly.config.qualifier.NoneQualifier;
import info.lyscms.assembly.config.qualifier.PostQualifier;
import info.lyscms.assembly.strategy.PreventStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class PreventRepeatImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata
                .getAnnotationAttributes(EnableLyscmsPreventRepeated.class.getName()));

        assert attributes != null;
        PreventStrategy strategy = attributes.getEnum("strategy");
        log.info("=================【Lyscms Prevent Repeated】 enabled================");
        log.info(" ");
        log.info("                  strategy is {}               ", strategy);
        log.info("");
        log.info("=================【Lyscms Prevent Repeated】 enabled================");

        return new String[]{PreventRepeatConfig.class.getName(),
                this.chooseQualifierStrategy(strategy).getName()};
    }

    /**
     * 根据策略标识获取对应的策略实例
     *
     * @param strategy
     * @return
     * @author sunkl
     * @version 2020/3/18 18:07
     */
    private Class<?> chooseQualifierStrategy(PreventStrategy strategy) {
        Class<?> clazz = null;
        switch (strategy) {
            case ALL:
                clazz = AllQualifier.class;
                break;
            case GET:
                clazz = GetQualifier.class;
                break;
            case POST:
                clazz = PostQualifier.class;
                break;
            case NONE:
                clazz = NoneQualifier.class;
                break;
        }
        return clazz;
    }
}
