package ua.mycompany.ronaldo173.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractJDBCDAO<T extends Identified<PK>, PK extends Serializable> implements GenericDao<T, PK> {

	private Connection connection;

	/**
	 * ���������� sql ������ ��� ��������� ���� �������.
	 * <p/>
	 * SELECT * FROM [Table]
	 */
	public abstract String getSelectQuery();

	/**
	 * ���������� sql ������ ��� ������� ����� ������ � ���� ������.
	 * <p/>
	 * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
	 */
	public abstract String getCreateQuery();

	/**
	 * ���������� sql ������ ��� ���������� ������.
	 * <p/>
	 * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
	 */
	public abstract String getUpdateQuery();

	/**
	 * ���������� sql ������ ��� �������� ������ �� ���� ������.
	 * <p/>
	 * DELETE FROM [Table] WHERE id= ?;
	 */
	public abstract String getDeleteQuery();

	/**
	 * ��������� ResultSet � ���������� ������ �������� ���������������
	 * ����������� ResultSet.
	 * 
	 * @throws PersistException
	 */
	protected abstract List<T> parseResSet(ResultSet rs) throws PersistException;

	/**
	 * ������������� ��������� update ������� � ������������ �� ��������� �����
	 * ������� object.
	 */
	protected abstract void prepareStForUpdateSetArgs(PreparedStatement statement, T object);

	/**
	 * ������������� ��������� Delete ������� � ������������ �� ��������� �����
	 * ������� object.
	 */
	protected abstract void prepareStForDeleteSetArgs(PreparedStatement statement, T object);

	/**
	 * ������������� ��������� insert ������� � ������������ �� ��������� �����
	 * ������� object.
	 */
	protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

	@Override
	public T getByPK(int key) throws PersistException {
		List<T> list = null;
		String sql = getSelectQuery();
		sql += "WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, key);

			ResultSet resultSet = statement.executeQuery();
			list = parseResSet(resultSet);
		} catch (SQLException e) {
			throw new PersistException(e);
		}

		if (list.size() > 1) {
			throw new PersistException("Received more that 1 record!");
		}

		return list.iterator().next();
	}

	@Override
	public List<T> getAll() throws PersistException {
		List<T> list = null;

		String sql = getSelectQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			list = parseResSet(rs);
		} catch (SQLException e) {
			throw new PersistException();
		}
		return list;
	}

	@Override
	public void update(T object) throws PersistException {
		String sql = getUpdateQuery();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStForUpdateSetArgs(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On update modify more then 1 rec: " + count);
			}
		} catch (SQLException e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void delete(T object) throws PersistException {
		String sql = getDeleteQuery();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStForDeleteSetArgs(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On update modify more then 1 rec: " + count);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.mycompany.ronaldo173.dao.GenericDao#persist(java.lang.Object)
	 */
	@Override
	public T persist(T object) throws PersistException {
		if (object == null || object.getId() != null) {
			throw new PersistException("Object is already persist or null.");
		}

		T persistInstance;
		String sql = getCreateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException("On persist modify just 1 rec, : " + count);
			}
		} catch (SQLException e) {
			throw new PersistException(e);
		}

		sql = getSelectQuery() + "WHERE id = last_insert_id();";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			List<T> list = parseResSet(rs);
			if ((list == null) || (list.size() != 1)) {
				throw new PersistException("Exception on findByPK new persist data.");
			}
			persistInstance = list.iterator().next();
		} catch (SQLException e) {
			throw new PersistException(e);
		}
		return persistInstance;
	}

	public AbstractJDBCDAO(Connection connection) {
		this.connection = connection;
	}

}
