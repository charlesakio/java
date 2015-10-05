/**
 * 
 */
package enterprise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
	PreparedStatement insertStmt;
	PreparedStatement selectStmt;
	
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

	public void createTable() throws SQLException {
		System.out.println("Creating table customer if it does not exist.");
		statement = conn.createStatement();
		String word = "CREATE TABLE IF NOT EXISTS customer(name VARCHAR(20), studentID VARCHAR(10), "
				+ "PRIMARY KEY(studentID), FOREIGN KEY(studentID) REFERENCES student(SNUM));";
		statement.executeUpdate(word);
	}//createTable() -->//
	
	public void dropTable() throws SQLException {
		String drop = "DROP TABLE myTable;";
		System.out.println(drop);
		statement.executeUpdate(drop);
	}//dropTable()-->//
	
	public void insertCustomer() throws SQLException {
		String rowInsert = "INSERT INTO customer(name, studentID) VALUES ('Test', '1236');";
		System.out.println("Inserting.." +  rowInsert);
		statement.execute(rowInsert);
	}//insertCustomer() -->//
	
	public void deleteCustomer() throws SQLException {
		String deleteRow = "DELETE FROM customer WHERE studentID=1235;";
		System.out.println("DELETING.." + deleteRow);
		statement.execute(deleteRow);
	}//deleteCustomer()-->//
	
	public void showAllCustomers() throws SQLException {
		String showAll = "SELECT * FROM customer";
		System.out.println("Executing.." + showAll);
		ResultSet result = statement.executeQuery(showAll);
		
		while(result.next()) {
			String name = result.getString("name");
			String studentID = result.getString("studentID");

			System.out.println(name + "  " + studentID);
		}
	}//showAllCustomer()-->//
	
	public void getCS() throws SQLException {
		String getComp = "SELECT * FROM student WHERE major='CS'";
		System.out.println("Executing..." + getComp);
		ResultSet result = statement.executeQuery(getComp);
		
		while(result.next()) {
			String snum = result.getString("snum");
			String name = result.getString("name");
			String major = result.getString("major");
			String hobby = result.getString("hobby");
			
			System.out.println(snum + " " + name + " " + major + " " + hobby);
		}
		                      
	}//getCS -->//
	
	public void closeConnection() throws SQLException {
		System.out.println("Closing database connection..closed");
		conn.close();
	}
}// Access DB --//

	

	