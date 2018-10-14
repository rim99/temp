package net.rim99.demo.account.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.sql.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RepositoryTest {

    private Connection conn;

    @BeforeAll
    void setUp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8801/account_test?" +
                            "user=mysql&password=mysql");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Fail to establish database connectionu, detail: " + ex.getMessage());
        }
    }

    @AfterAll
    void tearDown() {

    }
}
