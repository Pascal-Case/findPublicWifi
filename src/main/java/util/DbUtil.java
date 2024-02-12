package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String JDBC_URL_PREFIX = "jdbc:sqlite:";
    private static final String DEFAULT_DB_PATH = "default.db";
    private static final String DB_PATH = System.getProperty("DB_PATH", DEFAULT_DB_PATH);

    static {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register SQLite JDBC driver", e);
        }
    }

    private DbUtil() {
        throw new AssertionError();
    }

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = JDBC_URL_PREFIX + DB_PATH;
        return DriverManager.getConnection(jdbcUrl);
    }
}
