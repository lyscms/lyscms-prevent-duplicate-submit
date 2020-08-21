package com.lyscms.assembly.config.qualifier;


import com.lyscms.assembly.support.qualifier.PreventQualifier;
import com.lyscms.assembly.support.qualifier.impl.NonePreventQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoneQualifier {

    @Bean
    public PreventQualifier nonePreventQualifier() {
        return new NonePreventQualifier();
    }
}
