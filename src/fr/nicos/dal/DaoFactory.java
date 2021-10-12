package fr.nicos.dal;

import fr.nicos.dal.jdbcImlp.UserDaoJdbcImpl;

public abstract class DaoFactory {
	
	private static UserDao userDao;
	
	public static UserDao getUserDao() {
		
		if(userDao == null) userDao = new UserDaoJdbcImpl();
		return userDao;		
	}

}
