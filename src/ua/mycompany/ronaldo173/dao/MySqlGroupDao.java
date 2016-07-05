package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

import ua.mycompany.ronaldo173.domain.Group;

public class MySqlGroupDao extends AbstractJDBCDAO<Group, Integer> {

	private class PersistGroup extends Group {
		public void setId(int id) {
			super.setId(id);
		}
	}

	public MySqlGroupDao(Connection connection) {
		super(connection);
	}

	@Override
	public Group create() throws PersistException {
		Group g = new Group();
		return persist(g);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id, number, department FROM daotalk.Group";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO daotalk.Group (number, department) \n" + "VALUES (?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE daotalk.Group SET number= ?, department = ? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM daotalk.Group WHERE id= ?;";
	}

	@Override
	protected List<Group> parseResSet(ResultSet rs) throws PersistException {
		List<Group> listRes = new LinkedList<>();

		try {
			while (rs.next()) {
				PersistGroup group = new PersistGroup();
				group.setId(rs.getInt("id"));
				group.setNumber(rs.getInt("number"));
				group.setDepartment(rs.getString("department"));
				listRes.add(group);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return listRes;

	}

	@Override
	protected void prepareStForUpdateSetArgs(PreparedStatement statement, Group object) throws PersistException {
		try {
			statement.setInt(1, object.getNumber());
			statement.setString(2, object.getDepartment());
			statement.setInt(3, object.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Group object) throws PersistException {
		try {
			statement.setInt(1, object.getNumber());
			statement.setString(2, object.getDepartment());
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

}
