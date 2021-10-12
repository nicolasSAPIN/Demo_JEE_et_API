package fr.nicos.webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.nicos.bll.UserManager;
import fr.nicos.model.User;

@Path("/users")

public class PathDefineUserDB {
	private static UserManager um;
	
	static {
		um = new UserManager();
	}

	@GET
	public List<User> getUsers(){
		return um.getAll();
	}
	
	@POST
	public void AddUser(@FormParam("firstName") String firstName,
						@FormParam("lastName") String lastName,
						@FormParam("email") String email,
						@FormParam("phone") String phone,
						@FormParam("password") String password) {
		
		User user = new User(firstName,lastName,email,phone,password);
		um.addOne(user);
	}
	
	@PUT @Path("/{id : \\d+}")
	public User updateUser(@FormParam("id") int id,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("email") String email,
			@FormParam("phone") String phone,
			@FormParam("password") String password) {

			User user = um.getById(id);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhone(phone);
			user.setPassword(password);
			return um.updateOne(user);
}
	@DELETE @Path("/{id : \\d+}")
	public void deleteOne(@PathParam("id") int id) {
		 um.deleteOne(id);		
	}
}
