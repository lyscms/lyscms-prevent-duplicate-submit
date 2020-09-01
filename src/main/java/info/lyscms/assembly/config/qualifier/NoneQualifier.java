package info.lyscms.assembly.config.qualifier;


import info.lyscms.assembly.support.qualifier.PreventQualifier;
import info.lyscms.assembly.support.qualifier.impl.NonePreventQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoneQualifier {

    @Bean
    public PreventQualifier nonePreventQualifier() {
        return new NonePreventQualifier();
    }
}
