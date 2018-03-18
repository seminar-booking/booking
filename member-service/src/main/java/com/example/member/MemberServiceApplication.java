package com.example.member;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MemberServiceApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(MemberServiceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
