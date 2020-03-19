package info.lyscms.assembly.config.qualifier;


import info.lyscms.assembly.support.qualifier.PreventQualifier;
import info.lyscms.assembly.support.qualifier.impl.AllPreventQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllQualifier {

    @Bean
    public PreventQualifier allPreventQualifier() {
        return new AllPreventQualifier();
    }
}
