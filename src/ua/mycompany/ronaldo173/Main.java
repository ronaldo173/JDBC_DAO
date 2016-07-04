package ua.mycompany.ronaldo173;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Jdbc test.
 * 
 * @author Alexandr Efimov
 *
 */

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ResourceBundle bundle = ResourceBundle.getBundle("resourse");
		System.out.println(bundle);

		String urlDB = bundle.getString("url");
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection(urlDB, "root", "root");
				Statement statement = connection.createStatement();) {

			String query = "SELECT * FROM daotalk.student";

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String surName = resultSet.getString("surname");
				String enrDate = resultSet.getString("enrolment_date");

				String formated = String.format("%-5s= %-15s%-15s%-15s", id, name, surName, enrDate);
				System.out.println(formated);

			}
		}

	}

}
