package com.lyscms.assembly.config.qualifier;

import com.lyscms.assembly.support.qualifier.PreventQualifier;
import com.lyscms.assembly.support.qualifier.impl.GetPreventQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetQualifier {

    @Bean
    public PreventQualifier getPreventQualifier() {
        return new GetPreventQualifier();
    }
}
