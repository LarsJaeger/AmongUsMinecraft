package net.wargearworld.missileWarsMain.main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	public DataSource() {
		String database = net.wargearworld.missileWarsMain.main.Main.database;
		String host = net.wargearworld.missileWarsMain.main.Main.host;
		String user = net.wargearworld.missileWarsMain.main.Main.user;
		String passwd = net.wargearworld.missileWarsMain.main.Main.passwd;
		String connectionCommand = "jdbc:mysql://" + host + "/" + database;
		config.setJdbcUrl(connectionCommand);
		config.setUsername(user);
		config.setPassword(passwd);
//		config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setMaxLifetime(500000);
		ds = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}