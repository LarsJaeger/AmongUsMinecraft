package one.jgr.amongUs.main;

import java.sql.*;

public class DbConnection {
    public static Connection connection;

    public void newConnection() {
        if(Main.dbenabled) {
            try {
                if (connection != null) {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                }
                Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
                String connectionCommand = "jdbc:mysql://" + Main.host + "/" + Main.database + "?user=" + Main.user + "&password="
                        + Main.passwd;
                connection = DriverManager.getConnection(connectionCommand);

            } catch (SQLException ex) {
                ex.printStackTrace();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                newConnection();
            }
            if (!connection.isValid(2)) {
                newConnection();
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}