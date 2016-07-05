package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import ua.mycompany.ronaldo173.domain.Group;
import ua.mycompany.ronaldo173.domain.Student;

public class MySqlDaoFactory implements DaoFactory<Connection> {
	ResourceBundle bundle = ResourceBundle.getBundle("resourse");
	private String user = bundle.getString("user");
	private String password = bundle.getString("password");
	private String url = bundle.getString("url");
	private String driver = bundle.getString("driver");
	private Map<Class, DaoCreator> creators;

	@Override
	public Connection getContext() throws PersistException {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new PersistException(e);
		}
		return connection;
	}

	@Override
	public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
		DaoCreator creator = creators.get(dtoClass);
		if (creator == null) {
			throw new PersistException("Dao object for " + dtoClass + " not found.");
		}
		return creator.create(connection);
	}

	public MySqlDaoFactory() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		creators = new HashMap<>();
		creators.put(Group.class, new DaoCreator<Connection>() {

			@Override
			public GenericDao create(Connection connection) {
				return new MySqlGroupDao(connection);
			}
		});

		creators.put(Student.class, new DaoCreator<Connection>() {

			@Override
			public GenericDao create(Connection connection) {
				// TODO
				return null;
			}
		});
	}

}
