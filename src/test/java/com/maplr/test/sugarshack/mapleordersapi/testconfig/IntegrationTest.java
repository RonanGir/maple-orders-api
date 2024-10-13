package com.maplr.test.sugarshack.mapleordersapi.testconfig;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Sql("classpath:sql/fake_data.sql")
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class IntegrationTest {
}
