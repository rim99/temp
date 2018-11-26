package net.rim99.demo.account.support.service.repository;

import net.rim99.demo.account.support.guice.GlobalInjector;
import net.rim99.demo.account.support.service.repository.help.Mybatis;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class RepositoryTest {

    static private Connection conn;

    @BeforeClass
    public static void setUp() {
        GlobalInjector.builder().addModule(Mybatis.getModule());
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
        conn.prepareStatement("delete from PERSON;").execute();
        conn.close();
        GlobalInjector.clear();
    }
}
