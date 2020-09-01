package info.lyscms.assembly.config.qualifier;

import info.lyscms.assembly.support.qualifier.PreventQualifier;
import info.lyscms.assembly.support.qualifier.impl.GetPreventQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetQualifier {

    @Bean
    public PreventQualifier getPreventQualifier() {
        return new GetPreventQualifier();
    }
}
