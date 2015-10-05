/**
 * 
 */
package enterprise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author charlesjavelona
 *
 */
public class AccessDB {
	//Define instance fields
	Connection conn;
	Statement statement;

	//Database credentials
	static final String JDBC = "com.mysql.jdbc.Driver";
	static final String connUrl = "jdbc:mysql://localhost/fs";
	static final String USER = "root";
	static final String PASS = "";
	
	public AccessDB()  throws SQLException, IllegalAccessException, 
	 ClassNotFoundException, InstantiationException {

		//Register JDBC Driver//
		System.out.println("Registering " + JDBC);
		try {
			Class.forName(JDBC).newInstance();
		} catch (Exception e) {
			System.out.println("Registering failed");
			e.printStackTrace();
		}
		//Open a connection//
		System.out.println("Connecting to Database with " + connUrl);
		try {
			conn = DriverManager.getConnection(connUrl, USER, PASS);
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
				
	}// Access DB() -->//

	public boolean csLikes() {
		System.out.println("Creating table csLikes if it does not exist.");
		try {
			statement = conn.createStatement();
			String word = "CREATE TABLE IF NOT EXISTS csLikes(csStudent VARCHAR(20), food VARCHAR(20), "
					+ "PRIMARY KEY(csStudent, food), FOREIGN KEY(food) REFERENCES likes(food));";
			statement.executeUpdate(word);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}//csLikes() -->//
	
	public boolean dropTable() {
		String drop = "DROP TABLE myTable;";
		
		try {
			System.out.println(drop);
			statement.executeUpdate(drop);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}//dropTable()-->//
	
	public boolean populateCsLikes() {
		String rowInsert = "INSERT INTO csLikes(csStudent, food) "
				+ "SELECT s.name, l.food FROM student s, likes l"
				+ " WHERE s.snum=l.student AND s.major='CS';";
		
		try {
			System.out.println("Inserting.." +  rowInsert);
			statement.execute(rowInsert);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}//insertCustomer() -->//
	
	public boolean deleteCsLikes() throws SQLException {
		String deleteRow = "DELETE FROM csLikes;";
		
		try  {
			System.out.println("DELETING.." + deleteRow);
			statement.execute(deleteRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}//deleteCustomer()-->//
	
	public void showAllCustomers() {
		String showAll = "SELECT * FROM customer";
		
		try {
			System.out.println("Executing.." + showAll);
			ResultSet result = statement.executeQuery(showAll);
		
			while(result.next()) {
				String name = result.getString("name");
				String studentID = result.getString("studentID");
	
				System.out.println(name + "  " + studentID);
			}// while -->//
			
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch -->//
	}//showAllCustomer()-->//
	
	public void getCsLikes() {
		String getCs = "SELECT * FROM csLikes";
		System.out.println("Executing..." + getCs);
		
		try {
			ResultSet result = statement.executeQuery(getCs);
			
			while(result.next()) {
				String csStudent = result.getString("csStudent");
				String food = result.getString("food");
				
				System.out.println(csStudent + " " + food);
			}//while -->//
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch -->//              
	}//getCS -->//
	
	public boolean insertLikes(String studentID, String food) {
		
		String sql = "INSERT INTO likes(student, food)"
				+ " VALUES(?, ?)";

		try {
			System.out.println(sql);
			PreparedStatement insertStatement = conn.prepareStatement(sql);
			
			//Set param values
			insertStatement.setString(1, studentID);
			insertStatement.setString(2, food);
			insertStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch -->//
		return true;
	}
	
	public boolean deleteLikes(String student, String food, String type) {
		
		try {
			//Deactivate Foreign Key checking
			statement.execute("SET FOREIGN_KEY_CHECKS=0");

			if (!student.isEmpty() && !food.isEmpty()) {
				String sql = "DELETE FROM likes WHERE student=? AND food=?";
				
				PreparedStatement delete = conn.prepareStatement(sql);
				delete.setString(1, student);
				delete.setString(2, food);
				
				delete.executeUpdate();
				System.out.println("Done!");
				
				// Finish method 
				return true;
			}//if -->//
			
			//Execute below if student or food is blank//
			String valueToDelete = (!student.isEmpty()) ? student : food;
			
			String sql = "DELETE FROM likes WHERE " + type + "=?";
			
			PreparedStatement delete = conn.prepareStatement(sql);
			delete.setString(1, valueToDelete);
			
			delete.executeUpdate();
			
			//Turn on foreign key checking
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			System.out.println("Done!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}//deleteLikes()-->//
	
	public boolean insertStudents(String snum, String name, String major, String hobby) {
		
		String sql = "INSERT INTO student(snum, name, major, hobby)"
				+ " VALUES(?, ?, ?, ?)";
		
		try {
			System.out.println(sql);
			PreparedStatement insertStatement = conn.prepareStatement(sql);
			
			//Set param values
			insertStatement.setString(1, snum);
			insertStatement.setString(2, name);
			insertStatement.setString(3, major);
			insertStatement.setString(4, hobby);

			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch -->//
		return true;
	}//insertStudents()-->//
	
	public boolean deleteStudents(String input, String type) {
		
		String sql = "DELETE FROM student WHERE " + type + "=?";
		

		try {
			//Deactivate Foreign Key checking
			statement.execute("SET FOREIGN_KEY_CHECKS=0");

			System.out.println(sql);
			PreparedStatement delete = conn.prepareStatement(sql);
			
			//Set param valuess
			delete.setString(1, input);
			
			delete.executeUpdate();
			
			//Turn on foreign key checking
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			System.out.println("Done!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}//deleteStudents()-->//
	
	public boolean showLikes(String type) {
		
		try {
			if (type.toUpperCase().equals("A")) {
				String sql = "SELECT DISTINCT name FROM likes l, student s "
						+ "WHERE l.student=s.snum";				
				
				ResultSet result = statement.executeQuery(sql);
				System.out.println("Done!");
				
				// Finish method 				
				while(result.next()) {
					String getStudent = result.getString("name");					
					System.out.println(getStudent);
				}//while loop-->//
				return true;
			}//if -->//
			if (type.toUpperCase().equals("B")) {
				String sql = "SELECT DISTINCT food FROM likes l, student s "
						+ "WHERE l.student=s.snum";				
				
				ResultSet result = statement.executeQuery(sql);
				System.out.println("Done!");
				
				// Finish method 				
				while(result.next()) {
					String getStudent = result.getString("food");					
					System.out.println(getStudent);
				}//while loop-->//
				return true;
			}//if -->//
			if (type.toUpperCase().equals("C")) {
				String sql = "SELECT name, food FROM likes l, student s"
						+ " WHERE l.student=s.snum";				
				
				ResultSet result = statement.executeQuery(sql);
				System.out.println("Done!");
				
				// Finish method 				
				while(result.next()) {
					String getStudent = result.getString("name");
					String getFood = result.getString("food");
					
					System.out.println(getStudent + " " + getFood);
				}//while loop-->//
				return true;
			}//if -->//
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch -->//
		return true;
	}//showAllCustomer()-->//
	
	public void closeConnection() {
		try {
			System.out.println("Closing database connection..closed");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch-->//
	}//closeConnection()-->//
}// Access DB --//

	

	