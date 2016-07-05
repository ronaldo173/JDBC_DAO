package ua.mycompany.ronaldo173.dao;

public interface DaoFactory<Context> {

	interface DaoCreator<Context> {
		GenericDao create(Context context);
	}

	Context getContext() throws PersistException;

	GenericDao getDao(Context context, Class dtoClass) throws PersistException;
}
