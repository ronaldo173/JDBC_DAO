package ua.mycompany.ronaldo173.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

	T create() throws PersistException;

	/** Создает новую запись, соответствующую объекту object */
	public T persist(T object) throws PersistException;

	T getByPK(int key) throws PersistException;

	void update(T object) throws PersistException;

	void delete(T object) throws PersistException;

	List<T> getAll() throws PersistException;
}
