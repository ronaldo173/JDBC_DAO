package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySqlDaoFactory implements DaoFactory {
	ResourceBundle bundle = ResourceBundle.getBundle("resourse");
	private String user = bundle.getString("user");
	private String password = bundle.getString("password");
	private String url = bundle.getString("url");
	private String driver = bundle.getString("driver");

	@Override
	public Connection geConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public GroupDao getGroupDao(Connection connection) {
		return new MySqlGroupDao(connection);
	}

	@Override
	public StudentDao getStudentDao(Connection connection) {
		return null;
	}

	public MySqlDaoFactory() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
