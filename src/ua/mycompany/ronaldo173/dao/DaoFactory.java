package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

	Connection geConnection() throws SQLException;

	GroupDao getGroupDao(Connection connection);

	StudentDao getStudentDao(Connection connection);

}
