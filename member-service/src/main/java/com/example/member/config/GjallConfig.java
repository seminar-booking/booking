package com.example.member.config;

import com.example.member.log.entity.ApiRequest;
import com.example.member.log.repository.ApiRequestRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import tech.sollabs.gjall.annotation.EnableGjall;
import tech.sollabs.gjall.configurer.GjallConfigurerAdapter;
import tech.sollabs.gjall.configurer.GjallConfigurerBuilder;
import tech.sollabs.gjall.handlers.AfterRequestLoggingHandler;

@EnableGjall
@Configuration
public class GjallConfig extends GjallConfigurerAdapter {

    private ApiRequestRepository apiRequestRepository;

    @Override
    public void configure(GjallConfigurerBuilder gjallConfigurerBuilder) {
        gjallConfigurerBuilder
                .includeClientInfo(true)
                .includeQueryString(true)
                .request()
                    .includeHeaders(true)
                    .payloadSize(1000)
                    .and()
                .response()
                    .includeHeaders(true)
                    .payloadSize(3000);
    }

    @Bean
    public AfterRequestLoggingHandler afterRequestLoggingHandler() {
        return (httpServletRequest, httpServletResponse, apiLog) -> {

            boolean isNeedLogging = StringUtils.equalsAny(httpServletRequest.getContentType(), MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE);

            if (!isNeedLogging) {
                return;
            }

            String uri = httpServletRequest.getRequestURI();

            if (uri.equals("/login")) {
                apiLog.setRequestBody(null);
            }

            apiRequestRepository.save(new ApiRequest(apiLog));
        };
    }

    @Autowired
    public void setApiRequestRepository(ApiRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }
}
