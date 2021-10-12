package fr.nicos.dal.jdbcImlp;

import java.sql.Statement;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.nicos.dal.UserDao;
import fr.nicos.model.User;

public class UserDaoJdbcImpl implements UserDao{

private DataSource dataSource;
	
	public UserDaoJdbcImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public void insert(User user) {
		try(Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO user(`first_name`, `last_name`, `email`, `phone`, `password`) VALUES (?, ?, ?, ?, ? );", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getPassword());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				user.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public User selectById(int id) {
		User result = null;
		try (Connection cnx = dataSource.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM user WHERE id=?;");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("phone"), rs.getString("password"));
				result.setId(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;		
	}
	@Override
	public void removeOne(int id) {
		
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM user WHERE id = ?;");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public User update(User user) {
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("UPDATE user SET first_name=?, last_name=?, email=?, phone=?, password=?  WHERE id=? ;");
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getPassword());
			pstmt.setInt(6, user.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public List<User> getAll() {
		
		List<User> users = new ArrayList<User>();
		try (Connection cnx = dataSource.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM user;");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("phone"), rs.getString("password"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return users;
	}

	@Override
	public void deleteAll() {

		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM user;");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] encrypt(String pswd) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		KeySpec spec = new PBEKeySpec(pswd.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hash = factory.generateSecret(spec).getEncoded();
		return hash;
		
	}
}
