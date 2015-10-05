package enterprise;

import java.sql.SQLException;
import java.util.Scanner;


public class Menu {
	
	Scanner userInput = new Scanner(System.in);
	AccessDB test = new AccessDB();

	
	public Menu()  throws SQLException, IllegalAccessException, 
	 ClassNotFoundException, InstantiationException {
		// TODO Auto-generated constructor stub
		test.csLikes(); // Connect to Database
		

	}//Menu()-->//
	
	public boolean menuLikes() {
		String student, food;
		String choice;
		
		boolean mainMenu = false;
		do {
			System.out.println("Type $ if you want to go to main menu");
			System.out.println("Type Y if you want to proceed");
			choice = userInput.next();
		
			switch (choice.toUpperCase()) {
				case "Y":
					System.out.println("Enter your student ID");
					student = userInput.next();
					System.out.println("Enter your favorite food");
					food = userInput.next();
					
					System.out.println(student + food);
					test.insertLikes(student, food);
					System.out.println("Done..");
					break;
				case "$":
					mainMenu = true;
					System.out.println("Return to main menu");
				default:
					System.out.println("Invalid choice");
					break;
			}//switch-->//
		} while (!mainMenu);
			return true;	
	}//MenuLikes()-->//
	
	public boolean menuDelete() {
		String choice;
		String student, food;
		String blank;
		
		boolean mainMenu = false;
		do {			
			System.out.println("Choose the following to delete:");
			System.out.println("A-Delete by StudentID");
			System.out.println("B-Delete by Food Name");
			System.out.println("C-Delete by both");
			System.out.println("$-Go back to the main menu");

			choice = userInput.next();
			
			switch (choice.toUpperCase()) {
		
				case "A":
					System.out.println("Which student ID do you want to delete?");
					student = userInput.next();
					blank = "";
					
					test.deleteLikes(student, blank, "student");
					break;
				case "B":
					System.out.println("Which food do you want to delete");
					food = userInput.next();
					blank = "";
					
					test.deleteLikes(blank, food,  "food");
					break;
				case "C":
					System.out.println("Which student ID do you want to delete?");
					student = userInput.next();
					food = userInput.next();
					blank = "";
					
					test.deleteLikes(student, food, blank);
					break;
				case "$":
					System.out.println("Back to main menu");
					mainMenu = true;
					break;
				default:
					System.out.println("Invalid choice");
				}
			} while (!mainMenu);
			System.out.println("Good bye");
			return true;
		}//MenuDelete -->//
	
	public boolean menuInsertStudent() {
		String choice;
		String snum;
		String name;
		String major;
		String hobby;
		
		boolean mainMenu = false;
		
		do {
			System.out.println("Enter new student info.");
			System.out.println("Types $ to go back to Main menu. Otherwise, type Y key to proceed.");
			choice = userInput.next();
				switch(choice.toUpperCase()) {
				
				case "$":
					System.out.println("Back to main menu");
					mainMenu = true;
					break;
				case "Y":
					System.out.println("Insert SNUM");
					snum = userInput.next();
					System.out.println("Insert name");
					name = userInput.next();
					System.out.println("Insert major");
					major = userInput.next();
					System.out.println("Insert hobby");
					hobby = userInput.next();
					
					test.insertStudents(snum, name, major, hobby);
					break;
				default:
					System.out.println("Invalid input please try again.");
				}//switch-->//
		} while (!mainMenu);
		return true;
	}//menuInsert-->//
	
	public boolean menuDeleteStudent() {
		String input;
		String choice;
		
		boolean mainMenu = false;
		do {			
			System.out.println("Choose the following to delete:");
			System.out.println("A-Delete by StudentID");
			System.out.println("B-Delete by Student Name");
			System.out.println("C-Delete by Student Major");
			System.out.println("$-Go back to the main menu");

			choice = userInput.next();
			
			switch (choice.toUpperCase()) {
		
				case "A":
					System.out.println("Which student ID do you want to delete?");
					input = userInput.next();
					
					test.deleteStudents(input, "snum");
					
					break;
				case "B":
					System.out.println("Which name do you want to delete");
					input = userInput.next();
					
					test.deleteStudents(input, "name");
					break;
				case "C":
					System.out.println("Which major do you want to delete?");
					input = userInput.next();
					
					test.deleteStudents(input, "major");
					break;
				case "$":
					System.out.println("Back to main menu");
					mainMenu = true;
					break;
				default:
					System.out.println("Invalid choice");
				}
			} while (!mainMenu);
			return true;
	}//menuDeleteStudents()-->//
	
	public boolean menuReports() {
		String choice;
		
		boolean mainMenu = false;
		do {			
			System.out.println("Choose the following to see a report of:");
			System.out.println("A-Only show student name");
			System.out.println("B-Only show food name");
			System.out.println("C-Show both");
			System.out.println("$-Go back to the main menu");

			choice = userInput.next();
			
			switch (choice.toUpperCase()) {
		
				case "A":
					System.out.println("Showing all student name");
					test.showLikes(choice);

					
					break;
				case "B":
					System.out.println("Showing all food name");
					test.showLikes(choice);

					break;
				case "C":
					System.out.println("Showing both student name and food name");
					test.showLikes(choice);
					
					break;
				case "$":
					System.out.println("Back to main menu");
					mainMenu = true;
					break;
				default:
					System.out.println("Invalid choice");
				}
			} while (!mainMenu);
		
		return true;
	} //reportMenu()-->//
}
