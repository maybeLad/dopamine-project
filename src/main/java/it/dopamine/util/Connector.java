package it.dopamine.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class Connector {
    private static DataSource dataSource;

    public static void setDataSource(DataSource ds) {
        dataSource = ds;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}