/**
 * 
 */
package enterprise;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author charlesjavelona
 *
 */
public class EnterpriseApp {

	/**
	 * @param args
	 */
	
	public static void main(String[] args)  throws SQLException, InstantiationException, ClassNotFoundException, IllegalAccessException {
		// TODO Auto-generated method stub
		AccessDB test = new AccessDB();
		test.createTable(); // Must always be present before anything else.
		//test.insertCustomer();
		//test.deleteCustomer();
		//test.showAllCustomers();
		//test.dropTable();
		//test.getCS();
		test.closeConnection();
	}

}
