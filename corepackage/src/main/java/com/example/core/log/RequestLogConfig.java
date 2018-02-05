package com.example.core.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Configuration
public class RequestLogConfig {

    @Bean
    public AbstractRequestLoggingFilter requestLoggingFilter() {

        AbstractRequestLoggingFilter loggingFilter = new RequestLoggingFilter();

        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setMaxPayloadLength(2000);
        loggingFilter.setIncludeClientInfo(true);

        return loggingFilter;
    }
}
