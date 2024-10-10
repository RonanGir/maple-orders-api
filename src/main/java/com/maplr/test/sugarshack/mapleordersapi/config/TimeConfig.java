package com.maplr.test.sugarshack.mapleordersapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class TimeConfig {

    @Value("${app.default.tz}")
    private String defaultTimeZone;

    @Bean
    public Clock clock() {
        // Define the default clock with a specific zone
        return Clock.system(ZoneId.of(defaultTimeZone));
    }
}