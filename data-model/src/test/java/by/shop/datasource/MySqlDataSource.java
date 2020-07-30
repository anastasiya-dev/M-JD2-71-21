package by.shop.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlDataSource {

    private final static Logger log = LoggerFactory.getLogger(MySqlDataSource.class);
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(MySqlDataSource.class
                    .getResourceAsStream("/shopTestDataSource.properties"));
            Class.forName(properties.getProperty("jdbc.drivers"));
        } catch (ClassNotFoundException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties
        );
    }

}
