package fr.nicos.dal;

import java.util.List;

import fr.nicos.model.User;

public interface UserDao {
	
	void insert(User user);
	
	User selectById(int Id);
	
	void removeOne(int Id);
	
	User update(User user);
	
	List<User> getAll();
	
	void deleteAll();


}
