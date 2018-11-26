package net.rim99.demo.account.support.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class HikariCPDataSourceFactory extends UnpooledDataSourceFactory {

    public HikariCPDataSourceFactory() {
        String filepath = this.getClass()
                .getClassLoader()
                .getResource("config/hikari.properties")
                .getPath();
        HikariConfig config = new HikariConfig(filepath);
        HikariDataSource dataSource = new HikariDataSource(config);
        this.dataSource = dataSource;
    }
}
