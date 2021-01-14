package com.unibuc.demo.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@JdbcTest
@Sql({"classpath:test-data.sql"})
class CategoryRepositoryTest {

}