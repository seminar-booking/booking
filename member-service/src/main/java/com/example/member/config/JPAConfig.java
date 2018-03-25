package com.example.member.config;

import com.example.member.security.SimpleAuthentication;
import com.example.member.util.envers.QuerydslEnversRevisionRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.example.member.**.repository", repositoryFactoryBeanClass = QuerydslEnversRevisionRepositoryFactoryBean.class)
public class JPAConfig {

    @Bean
    public AuditorAware auditorAware() {
        return () ->
                Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                    .map(authentication -> {
                        if (authentication instanceof SimpleAuthentication) {
                            return ((SimpleAuthentication) authentication).getId();
                        }
                        return null;
                    });
    }
}
