package fr.nicos.bll;

import java.util.List;

import fr.nicos.dal.DaoFactory;
import fr.nicos.dal.UserDao;
import fr.nicos.model.User;

public class UserManager {
	
	private UserDao userDao;
	
	public UserManager() {
		userDao = DaoFactory.getUserDao();
		
	}

	public List<User> getAll(){
		
		return userDao.getAll();
	}
	
	public User getById(int Id) {
		return userDao.selectById(Id);
	}
	
	public void addOne(User user) {
		userDao.insert(user);
	}
	public User updateOne(User user) {
		return userDao.update(user);
	}
	public void deleteOne(int Id) {
		 userDao.removeOne(Id);
	}
	public void deleteAll() {
		userDao.deleteAll();
	}
	
		
}
