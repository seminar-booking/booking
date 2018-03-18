package com.example.member;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemberServiceApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(MemberServiceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
