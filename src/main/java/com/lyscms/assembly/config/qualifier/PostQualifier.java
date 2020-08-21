package com.lyscms.assembly.config.qualifier;


import com.lyscms.assembly.support.qualifier.PreventQualifier;
import com.lyscms.assembly.support.qualifier.impl.PostPreventQualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostQualifier {

    @Bean
    public PreventQualifier postPreventQualifier() {
        return new PostPreventQualifier();
    }
}
