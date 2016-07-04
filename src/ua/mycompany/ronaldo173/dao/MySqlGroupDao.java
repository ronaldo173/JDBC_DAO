package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.mycompany.ronaldo173.entity.Group;

public class MySqlGroupDao implements GroupDao {
	private final Connection connection;

	@Override
	public Group create() {
		return null;
	}

	@Override
	public Group read(int key) throws SQLException {
		String sqlQuerry = "SELECT * FROM daotalk.group where id = ?";
		PreparedStatement prepareStatement = connection.prepareStatement(sqlQuerry);

		prepareStatement.setInt(1, key);
		ResultSet resultSet = prepareStatement.executeQuery();
		resultSet.next();

		Group group = new Group();
		group.setId(resultSet.getInt("id"));
		group.setNumber(resultSet.getInt("number"));
		group.setDepartment(resultSet.getString("department"));

		return group;

	}

	@Override
	public void update(Group group) {
	}

	@Override
	public void delete(Group group) {

	}

	@Override
	public List<Group> getAll() throws SQLException {
		String sqlQuerry = "SELECT * FROM daotalk.Group";
		java.sql.PreparedStatement statement = connection.prepareStatement(sqlQuerry);
		ResultSet rs = statement.executeQuery();

		List<Group> list = new ArrayList<>();
		while (rs.next()) {
			Group group = new Group();
			group.setId(rs.getInt("id"));
			group.setNumber(rs.getInt("number"));
			group.setDepartment(rs.getString("department"));
			list.add(group);
		}
		return list;
	}

	/**
	 * @param connection
	 */
	public MySqlGroupDao(Connection connection) {
		this.connection = connection;
	}

}
