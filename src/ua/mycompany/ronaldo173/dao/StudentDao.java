package ua.mycompany.ronaldo173.dao;

import java.util.List;

import ua.mycompany.ronaldo173.domain.Student;

public interface StudentDao {

	Student create();

	Student read(int key);

	void update(Student student);

	void delete(Student student);

	List<Student> getAll();
}
