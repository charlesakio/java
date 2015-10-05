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
import java.util.Scanner;
public class EnterpriseApp {

	private static Scanner userInput;

	/**
	 * @param args
	 */
	
	public static void main(String[] args)  throws SQLException, InstantiationException, ClassNotFoundException, IllegalAccessException {
		// TODO Auto-generated method stub
		
		//Initialize menu
		Menu menu = new Menu();
		String choice;
		userInput = new Scanner(System.in);

		boolean mainMenu = false;
		do {			
			System.out.println("Choose the following:");
			System.out.println("1- Insert into likes table");
			System.out.println("2- Delete from likes table");
			System.out.println("3- Insert into student table");
			System.out.println("4- Delete from student table");
			System.out.println("5- Reports from likes");
			System.out.println("$- Exit");


			choice = userInput.next();
			
			switch (choice) {
		
				case "1":
					menu.menuLikes();
					break;
				case "2":
					menu.menuDelete();
					break;
				case "3":
					menu.menuInsertStudent();
					break;
				case "4":
					menu.menuDeleteStudent();
					break;
				case "5":
					menu.menuReports();
					break;
				case "$":
					mainMenu = true;
					break;
				default:
					System.out.println("Invalid choice");
				}//switch -->//
			} while (!mainMenu);
			System.out.println("Good bye");
		}//main args -->//	
	}//EnterpriseApp-->
