import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

/**
 * Error handling for unexpected user inputs.
 */
public class UserInput {
	
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Gets an integer from user.
	 * Shows relevant error message when input is not an integer.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid input entered
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
	 * Gets an integer within a range of numbers from user.
	 * Shows relevant error message when input entered is not an integer or not within the range.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @param lLimit Lower limit of accepted value, inclusive
	 * @param uLimit Upper limit of accepted value, inclusive
	 * @return valid input entered
	 */
	public static int nextInt(String inMsg, int lLimit, int uLimit){
		
		int n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextInt();
				if (n==-1) return n;
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
	 * Gets a double from user.
	 * Shows relevant error message when input entered is not a double.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid input entered
	 */
	public static double nextDouble(String inMsg){
		
		double n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextDouble();
				if (n==-1) return n;
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
	 * Gets a double within a range of numbers from user.
	 * Shows relevant error message when input entered is not a double or not within the range.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @param lLimit Lower limit of accepted value, inclusive
	 * @param uLimit Upper limit of accepted value, inclusive
	 * @return valid input entered
	 */
	public static double nextDouble(String inMsg, double lLimit, double uLimit){
		
		double n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextDouble();
				if (n==-1) return n;
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
	 * Gets input for Gender (M for male or F for female) from user.
	 * Shows relevant error message when input is not M or F.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid input entered (M for male or F for female)
	 */
	public static char getGender(String inMsg){
		
		char n = 0;
		boolean valid = true;
		
		do{
			valid = true;
			System.out.print(inMsg);
			n = sc.next().charAt(0);
			switch (n){
				case 'M':
				case 'm':
					n = 'M';
					break;
				case 'F':
				case 'f':
					n = 'F';
					break;
				default:
					valid = false;
					System.out.println("Error: expected M or F for Gender\n");
					break;
			}

		}while(!valid);
		
		sc.nextLine();	// get dummy line
		return n;
		
	}
	
	/**
	 * Gets input for Smoking option (Y for smoking or N for non&#8208;smoking) from user.
	 * Shows relevant error message when input is not Y or N.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid input entered (Y for smoking or N for non&#8208;smoking)
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
	 * Gets String input from user.
	 * Shows relevant error message when input is not a string.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid String input entered
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
	 * Gets food type from user.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return food type entered
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
	 * Gets job title from user.
	 * 
	 * @param inMsg Error message of prompt displayed
	 * @return job title entered
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
	 * Gets a contact number from user.
	 * Shows relevant error message input is not an integer or the integer is not 8 digit long.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid 8 digit long integer input entered
	 */
	public static int getContact(String inMsg) {
		int n = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(inMsg);
				n = sc.nextInt();
				if (n==-1) return n;
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
	 * Gets a date and time input from user.
	 * Shows relevant error message when input entered is not in dd/mm/yy hh:mm format.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid date and time input entered
	 */
	public static Calendar getDateTime(String inMsg) {
		String date = "";
	    Date inputDate = null;
	    SimpleDateFormat dateFormat = null;
		boolean validDate = false;		
		Calendar time = Calendar.getInstance();
		System.out.println(inMsg);
		
		do{
		    System.out.print("Enter reservation date and time in the correct format (dd/MM/yyyy HH:mm): ");	
			date  = sc.nextLine();
			if (date.compareTo("-1")==0) return null; 
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
			else if (time.get(Calendar.HOUR_OF_DAY)<9 || time.get(Calendar.HOUR_OF_DAY)>20){
				System.out.println("Restaurant opens from 9 am to 11 pm! Latest booking time: 20:59");
			}
			else validDate = true;
		} while(!validDate);
				
		return time;
	}
	
	/**
	 * Gets date from user for generating 1&#8208;day report.
	 * Shows relevant error message when input entered is not in dd/mm/yyyy format.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid date input entered
	 */
	public static Calendar getDateForReport(String inMsg) {
		String date = "";
	    Date inputDate = null;
	    SimpleDateFormat dateFormat = null;
		boolean validDate = false;		
		Calendar time = Calendar.getInstance();
		
		
		do{
			System.out.println(inMsg);
		    System.out.print("Enter date and time in the correct format (dd/MM/yyyy): ");	
			date  = sc.nextLine();
			if (date.compareTo("-1")==0) return null;
		    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		    	inputDate = dateFormat.parse(date);
		    } catch (ParseException e) {
		        System.out.println("Entered date and time is not in the correct format!");
		        continue;
		    }
		    time.setTime(inputDate);
			if (time.compareTo(Calendar.getInstance())>0){
				System.out.println("You have entered a date after current date! Please enter a valid date!");
			}
			else validDate = true;
		} while(!validDate);
				
		return time;
	}
	
	/**
	 * Gets date from user for generating report 1&#8208;month report.
	 * Shows relevant error message when input entered is not in mm/yyyy format.
	 * 
	 * @param inMsg Error message or prompt displayed
	 * @return valid input entered
	 */
	public static Calendar getMonthYearForReport(String inMsg) {
		String date = "";
	    Date inputDate = null;
	    SimpleDateFormat dateFormat = null;
		boolean validDate = false;		
		Calendar time = Calendar.getInstance();
		
		do{
			System.out.println(inMsg);
		    System.out.print("Enter Month and Date in the correct format (MM/yyyy): ");	
			date  = sc.nextLine();
			if (date.compareTo("-1")==0) return null;
		    dateFormat = new SimpleDateFormat("MM/yyyy");
		    try {
		    	inputDate = dateFormat.parse(date);
		    } catch (ParseException e) {
		        System.out.println("Entered date and time is not in the correct format!");
		        continue;
		    }
		    time.setTime(inputDate);
			if (time.compareTo(Calendar.getInstance())>0){
				System.out.println("You have entered period after current date! Please enter a valid period!");
			}
			else validDate = true;
		} while(!validDate);
				
		return time;
	}
}