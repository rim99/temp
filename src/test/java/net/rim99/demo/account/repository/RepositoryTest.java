package net.rim99.demo.account.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryTest {

    static private Connection conn;

    @BeforeClass
    public static void setUp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8801/account_test?" +
                    "user=mysql&password=mysql");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Fail to establish database connectionu, detail: " + ex.getMessage());
        }
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        conn.close();
    }
}
