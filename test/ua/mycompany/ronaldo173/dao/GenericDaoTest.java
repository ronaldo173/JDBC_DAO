package ua.mycompany.ronaldo173.dao;

import java.io.Serializable;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public abstract class GenericDaoTest<Context> {

	/**
	 *  ласс тестируемого дао объекта
	 */
	protected Class daoClass;

	/**
	 * Ёкземпл€р доменного объекта, которому не соответствует запись в системе
	 * хранени€
	 */
	protected Identified notPersistedDto;

	/**
	 * Ёкземпл€р тестируемого дао объекта
	 */
	public abstract GenericDao dao();

	/**
	 *  онтекст взаимодействи€ с системой хранени€ данных
	 */
	public abstract Context context();

	@Test
	public void getByPKTest() throws Exception {
		System.out.println("getByPKTest: " + dao());
		Identified dto = dao().getByPK(1);
		Assert.assertNotNull(dto);
	}

	@Test
	public void testGetAll() throws Exception {
		List list = (List) dao().getAll();
		System.out.println("\n" + dao().getClass());
		for (Object object : list) {
			System.out.println(object);
		}
		Assert.assertNotNull(list);
		Assert.assertTrue((list).size() > 0);
	}

	public GenericDaoTest(Class clazz, Identified<Integer> notPersistedDto) {
		this.daoClass = clazz;
		this.notPersistedDto = notPersistedDto;
	}
}
