package ua.mycompany.ronaldo173.dao;

import java.sql.SQLException;
import java.util.List;

import ua.mycompany.ronaldo173.domain.Group;

public interface GroupDao {

	Group create();

	Group read(int key) throws SQLException;

	void update(Group group);

	void delete(Group group);

	List<Group> getAll() throws SQLException;
}
