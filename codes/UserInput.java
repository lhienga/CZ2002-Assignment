import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
public class UserInput {
	
	public static void main(String[] args) { 
		int num = UserInput.getContact("Enter a 8 digit contact number:");
		System.out.println("The number is: "+num);
	}
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Get an integer from user
	 * @param inMsg Prompt displayed for users, Error message showed when input entered is not an integer
	 * @returnEntered Integer
	 */
	public static int nextInt(String inMsg) {
		int n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg+" ");
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
	 * @param inMsg Prompt displayed for users, Error message showed when input entered is not an integer or not within the range
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
				System.out.print(inMsg+" ");
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
	 * @param inMsg Prompt displayed for users, Error message showed when input entered is not an integer
	 * @return Entered Double
	 */
	public static double nextDouble(String inMsg){
		
		double n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg+" ");
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
	 * Get input for Gender from user
	 * @param inMsg Prompt displayed for users, Error message if input is not M or F
	 * @return Entered Gender
	 */
	public static char getGender(String inMsg){
		
		char n = 0;
		boolean valid = true;
		
		do{
			valid = true;
			System.out.print(inMsg+" ");
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
	 * Get input String from user
	 * @param inMsg Prompt displayed for users, Error message if input is not a string
	 * @return Entered String
	 */
	public static String getString(String inMsg) {
		String n = null;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg+" ");
				n = sc.nextLine();
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error: expected a String\n");
			}
		}while(!valid);
		
		return n;
	}
	
	
	public static Food.TYPE getType(String inMsg) {
		Food.TYPE t;
		int choice;
		boolean valid = true;
		
				valid = true;
				System.out.println(inMsg);
		        System.out.println("(1) Appetizer");
		        System.out.println("(2) Main Course");
		        System.out.println("(3) Dessert");
		        System.out.println("(4) Drink");
		        System.out.println("(5) Back"); 
		        choice = UserInput.nextInt("\n    Enter the number of your choice: ", 1, 5);
		        switch (choice) {
		        case 1:
		        	return Food.TYPE.APPETIZER;
		        	
		        
				case 2:
		        	return Food.TYPE.MAINCOURSE;

				case 3:
		        	return Food.TYPE.DESSERT;
		        
				case 4:
			    	return Food.TYPE.DRINK;

				case 5:
					return null;
		        }
			    
		        return null;
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
				System.out.print(inMsg+" ");
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
	 * @param inMsg Prompt displayed for users, Error message showed when input entered is not in dd/mm/yy hh
	 * @return Integer
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
			validDate = true;
		} while(!validDate);
				
		return time;
	}
}

