/**
 * 
 */
package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ua.mycompany.ronaldo173.domain.Group;

/**
 * @author Alexandr Efimov
 *
 */
public class MySqlGroupDaoTest {

	/**
	 * Test method for
	 * {@link ua.mycompany.ronaldo173.dao.MySqlGroupDao#getAll()}.
	 * 
	 * @throws SQLException
	 * @throws PersistException 
	 */
	@Test
	public void testGetAll() throws SQLException, PersistException {
		DaoFactory daoFactory = new MySqlDaoFactory();
		List<Group> list;

		try (Connection connection = (Connection) daoFactory.getContext()) {
			GroupDao dao = (GroupDao) daoFactory.getDao(connection, Group.class);
			list = dao.getAll();
		}

		System.out.println(list);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}

}
