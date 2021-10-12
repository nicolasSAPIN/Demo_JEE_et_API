package fr.nicos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.nicos.bll.UserManager;
import fr.nicos.model.User;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	
	UserManager um = new UserManager();
	String message =  "";
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void loadUsers(HttpServletRequest request) {
		List<User> users = um.getAll();
		request.setAttribute("listeUsers", users);
	}
	
	
	public void loadUser(int id, HttpServletRequest request){
		
		User user = um.getById(id);
		if(user == null) {
			
			throw new IllegalArgumentException("User :"+ id +" inexistant");
		}
		
		request.setAttribute("userUpdate", user);
		
	}
	
	
	public int getintparameter(HttpServletRequest request, String paramName  ,int defaultValue) {
		
		
		String paramValue = request.getParameter(paramName);
		
		
		if(paramValue == null ){  
			return defaultValue;
		}
		
		try {
			return 	Integer.parseInt(paramValue);
		}
		catch(NumberFormatException e){
			return defaultValue;
		}
		
		
	}
	
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		loadUsers(request);
		int userId = getintparameter(request, "id", -1);

		if (userId > -1) {
			loadUser( userId , request);
		}
		
		
		
		//Renvoi sur la jsp
		request.getRequestDispatcher("/META-INF/jsp/index.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//verif cause de la validation
		String btn_submit = request.getParameter( "btn_submit" );
		String btn_del = request.getParameter( "btn_del" );
		String btn_update = request.getParameter( "btn_update" );//valide une modification
		
		
		

		//Si clique pour Ajout
		if(btn_submit != null) {
			String firstName = request.getParameter( "first_name" );
			String lastName = request.getParameter( "last_name" );
			String email = request.getParameter( "email" );
			String phone = request.getParameter( "phone" );
			String password = request.getParameter( "password" );
			
			User user = new User(firstName, lastName, email, phone, password);
			um.addOne(user);
			
		}
		//Si clique pour suppression
		else if(btn_del != null) {
			int iddel = Integer.parseInt(request.getParameter("id"));
			um.deleteOne(iddel);
		}
		
				
		//Si validation mise à jour
		else if(btn_update != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String firstName = request.getParameter( "first_name" );
			String lastName = request.getParameter( "last_name" );
			String email = request.getParameter( "email" );
			String phone = request.getParameter( "phone" );
			String password = request.getParameter( "password" );
			User user = new User(id, firstName, lastName, email, phone, password);
			um.updateOne(user);
		}
				
		doGet(request, response);
	}

}
