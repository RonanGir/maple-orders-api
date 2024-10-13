package com.maplr.test.sugarshack.mapleordersapi;

import com.maplr.test.sugarshack.mapleordersapi.testconfig.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

// Run app in test containers
public class TestMapleOrdersApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(MapleOrdersApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
