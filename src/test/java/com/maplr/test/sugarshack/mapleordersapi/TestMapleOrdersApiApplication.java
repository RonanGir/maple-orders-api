package com.maplr.test.sugarshack.mapleordersapi;

import org.springframework.boot.SpringApplication;

public class TestMapleOrdersApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(MapleOrdersApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
