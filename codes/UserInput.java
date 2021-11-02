import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

public class UserInput {
	
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Get an integer from user
	 * Error message showed when input entered is not an integer
	 * @param inMsg Prompt displayed for users
	 * @returnEntered Integer
	 */
	public static int nextInt(String inMsg) {
		int n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextInt();
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a integer number value");
			}
		} while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
	}
	
	/**
	 * Get an integer from user within a range of numbers
	 * Error message showed when input entered is not an integer or not within the range
	 * @param inMsg Prompt displayed for users
	 * @param lLimit Lower limit of accepted value, inclusive
	 * @param uLimit Upper limit of accepted value, inclusive
	 * @return Entered Integer
	 */
	public static int nextInt(String inMsg, int lLimit, int uLimit){
		
		int n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextInt();
				valid = (n >= lLimit && n <= uLimit);
				if(!valid){
					System.out.println("Please enter an integer between " + lLimit + " and " + uLimit + " (inclusive).");
					sc.nextLine();	// get dummy line
				}
					
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a integer number value\n");
			}
		} while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
	}
	
	/**
	 * Get a double from user
	 * Error message showed when input entered is not an integer
	 * @param inMsg Prompt displayed for users
	 * @return Entered Double
	 */
	public static double nextDouble(String inMsg){
		
		double n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextDouble();
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a double number value\n");
			}
		}while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
		
	}
	
	/**
	 * Get an integer from user within a range of numbers
	 * Error message showed when input entered is not a double or not within the range
	 * @param inMsg Prompt displayed for users
	 * @param lLimit Lower limit of accepted value, inclusive
	 * @param uLimit Upper limit of accepted value, inclusive
	 * @return
	 */
	public static double nextDouble(String inMsg, double lLimit, double uLimit){
		
		double n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextDouble();
				valid = (n >= lLimit && n <= uLimit);
				if(!valid){
					System.out.println("Please enter a double between " + lLimit + " and " + uLimit + " (inclusive).");
					sc.nextLine();	// get dummy line
				}
					
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a double number value\n");
			}
		} while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
	}
	
	/**
	 * Get input for Gender from user
	 * Error message if input is not M or F
	 * @param inMsg Prompt displayed for users
	 * @return Entered Gender
	 */
	public static char getGender(String inMsg){
		
		char n = 0;
		boolean valid = true;
		
		do{
			valid = true;
			System.out.print(inMsg);
			n = sc.next().charAt(0);
			if (Character.compare(n, 'M') != 0 && Character.compare(n, 'F') != 0) {
				valid = false;
				System.out.println("Error: expected M or F for Gender\n");
			} else {
				break;
			}
				
		}while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
		
	}
	/**
	 * Get input for Smoking or Not from user
	 * Error message if input is not Y or N
	 * @param inMsg Prompt displayed for users
	 * @return Entered Boolean
	 */
	public static boolean getSmoking(String inMsg){
		
		char n = 0;
		boolean valid = true;
		
		do{
			valid = true;
			System.out.print(inMsg);
			n = sc.next().charAt(0);
			if (Character.compare(n, 'Y') != 0 && Character.compare(n, 'N') != 0) {
				valid = false;
				System.out.println("Error: expected Y for smoking and N for not smoking\n");
			} else {
				break;
			}
				
		}while(!valid);
		
		sc.nextLine();	// get dummy line
		if (n=='Y') return true;
		return false;
		
	}
	
	/**
	 * Get input String from user
	 * Error message if input is not a string
	 * @param inMsg Prompt displayed for users
	 * @return Entered String
	 */
	public static String getString(String inMsg) {
		String n = null;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextLine();
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a String\n");
			}
		}while(!valid);
		
		return n;
	}
	
	/**
	 * Get Food Type from user
	 * @param inMsg Prompt displayed for users
	 * @return Food Type
	 */
	public static Food.TYPE getType(String inMsg) {

		int choice;

		System.out.println(inMsg);
        System.out.println("(1) Appetizer");
        System.out.println("(2) Main Course");
        System.out.println("(3) Dessert");
        System.out.println("(4) Drink");
        choice = UserInput.nextInt("Enter the number of your choice: ", 1, 4);
        switch (choice) {
        case 1:
        	return Food.TYPE.APPETIZER;
        	
        
		case 2:
        	return Food.TYPE.MAINCOURSE;

		case 3:
        	return Food.TYPE.DESSERT;
        
		case 4:
	    	return Food.TYPE.DRINK;

		default:
			return null;
        }
			    
	}
	
	/**
	 * Get Job Title from user
	 * @param inMsg Prompt displayed for users
	 * @return Job Title
	 */
	public static Staff.JOB getJobTitle(String inMsg) {

		int choice;

		System.out.println(inMsg);
        System.out.println("(1) Manager");
        System.out.println("(2) Cashier");
        System.out.println("(3) Part-Time");
        System.out.println("(4) Full-Time");
        choice = UserInput.nextInt("Enter the number of your choice: ", 1, 4);
        switch (choice) {
        case 1:
        	return Staff.JOB.MANAGER;
        	
        
		case 2:
        	return Staff.JOB.CASHIER;

		case 3:
        	return Staff.JOB.PART_TIME;
        
		case 4:
	    	return Staff.JOB.FULL_TIME;

		default:
			return null;
        }

	}
	
	/**
	 * Get a contact number from user
	 * @param inMsg Prompt displayed for users, Error message showed when input entered is not an integer or integer is not 8 digit long
	 * @return Integer
	 */
	public static int getContact(String inMsg) {
		int n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextInt();
				int length = (int) (Math.log10(n) + 1);
				if (length != 8) {
					System.out.println("Error: expected a 8 digit number");
					valid = false;
				}
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a integer number value");
			}
		} while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
	}

	/**
	 * Get a date time input from user
	 * Error message showed when input entered is not in dd/mm/yy
	 * @param inMsg Prompt displayed for users
	 * @return Time
	 */
	public static Calendar getDateTime(String inMsg) {
		String date = "";
	    Date inputDate = null;
	    SimpleDateFormat dateFormat = null;
		boolean validDate = false;		
		Calendar time = Calendar.getInstance();
		
		do{
		    System.out.print("Enter reservation date and time in the correct format (dd/MM/yyyy HH:mm): ");	
			date  = sc.nextLine();
		    dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    try {
		    	inputDate = dateFormat.parse(date);
		    } catch (ParseException e) {
		        System.out.println("Entered date and time is not in the correct format!");
		        continue;
		    }
		    time.setTime(inputDate);
			if (time.compareTo(Calendar.getInstance())<0){
				System.out.println("Entered a passed date time!");
			}
			else if (time.get(Calendar.HOUR_OF_DAY)<9 || time.get(Calendar.HOUR_OF_DAY)>18){
				System.out.println("Restaurant opens from 9 am to 6 pm!");
			}
			else validDate = true;
		} while(!validDate);
				
		return time;
	}
}
