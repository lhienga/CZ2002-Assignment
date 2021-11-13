import java.util.Calendar;
import java.util.ArrayList;

/**
 * Provides a UI when the 'Reservation Manager' option 
 * is selected from the 'Main Menu'.
 */
public class ReservationUI {
    	
	/**
	 * Displays the options under the 'Reservation Manager' option, 
	 * namely to make a booking reservation, 
	 * reserve a table for walk&#8208;in dining, 
	 * remove a reservation, 
	 * print a reservation, 
	 * update a reservation, 
	 * and print all reservations.
	 * 
	 * @param reserve Reservation manager
	 * @param orders Order manager
	 * @param staffs Staff manager
	 * @param tables Table manager
	 */
	public static void manageReservationOptions(ReservationManager reserve, OrderManager orders, StaffManager staffs, TableManager tables) {
		
		int choice;
		
		
		// release all table with expired reservation
		reserve.removeExpiredReservations();
		int contactNum;
		
		
        do {

        	System.out.println();
        	choice = UserInput.nextInt("Select a choice:\n" +
					"1. Make reservation\n"+
					"2. Walk-in dining\n" +
					"3. Remove reservation\n" +
					"4. Print a reservation\n"+
					"5. Update reservation\n"+
					"6. Print all reservations\n"+
					"ENTER 0 to return to main menu\n",0,6);

        	System.out.println();
			if (choice >=1 && choice <=5){
				contactNum = UserInput.getContact("Enter customer's contact number: (Enter -1 to exit) ");
				if (contactNum == -1) continue;

			}
			else contactNum = -1;
            
            switch (choice) {
                case 1:
					makeReservation(reserve, contactNum,orders);
                    break;
                case 2:
					walkinReservation(reserve, contactNum, orders, staffs, tables);
						break;
                case 3:
					reserve.removeReservationByContact(contactNum);
                        break;
                case 6:
                	reserve.printAllReservation();
                    break;
				case 5:
					reserve.updateReservationDetails(contactNum);
					break;
				case 4:
					reserve.printReservation(contactNum);
					break;
            }
        } while (choice != 0);

	}

	/**
	 * Makes a booking reservation.
	 * 
	 * @param reserve Reservation manager
	 * @param contactNum Contact number of the customer making this reservation
	 * @param orders Order manager
	 */
    public static void makeReservation(ReservationManager reserve, int contactNum, OrderManager orders){
    	if (reserve.getReservationByContact(contactNum)!=null){
			System.out.println("This contact number has already booked a reservation!");
			return;
		}
        String name = UserInput.getString("Enter customer's name: (Enter -1 to exit) ");
					if (name.compareTo("-1")==0) return;
					Calendar bookingTime = UserInput.getDateTime("Please enter the time you want to reserve table (Enter -1 to exit) ");
					if (bookingTime == null) return;
					Reservation reservation = reserve.createReservation(contactNum, name, bookingTime, 0,orders);
					if (reservation==null){
						System.out.println("\nCannot make reservation, unavailable tables");
					}
					else {
						System.out.println("\nReservation has been successfully created!\n");
						reserve.printReservation(reservation.getContact());
						
					}
    }

	/**
	 * Makes a walk&#8208;in reservation.
	 * 
	 * @param reserve Reservation manager
	 * @param contactNum Contact number of the customer making this reservation
	 * @param orders Order manager
	 * @param staffs Staff manager
	 * @param tables Table manager
	 */
    public static void walkinReservation(ReservationManager reserve, int contactNum, OrderManager orders, StaffManager staffs, TableManager tables){
        int staffId;
		Staff currentStaff;
		if (reserve.getReservationByContact(contactNum)!=null){
			System.out.println("This contact number has already booked a reservation!");
			return;
		}
		
		
        String cusName = UserInput.getString("Enter customer's name: (Enter -1 to exit) ");
        if (cusName.compareTo("-1")==0) return;
        Calendar walkInTime = Calendar.getInstance();
        if (walkInTime.get(Calendar.HOUR_OF_DAY)<9 || walkInTime.get(Calendar.HOUR_OF_DAY)>20) {
            System.out.println("Restaurant opens from 9 am to 11 pm! Latest Order: 20:59");
            return;
        }
        Reservation walkInReservation = reserve.createReservation(contactNum, cusName, walkInTime,1,orders);
        if (walkInReservation==null){
            System.out.println("Cannot make reservation");
        }
        else {
            System.out.println("\nWalk-in created:");
            reserve.printReservation(walkInReservation.getContact());
            
            // create default order for walk-in
            while (true) {
                staffId = UserInput.nextInt("\nEnter Staff ID to create the order for walk-in ");

                currentStaff = staffs.getStaffByID(staffId);
                if (currentStaff == null) {
                    System.out.println("Invalid staff ID!");
                } else {
                    break;
                }
            }
            // change table to occupied since it is a walk-in
            orders.createOrder(walkInReservation.getTableID(), contactNum, currentStaff, new ArrayList<MenuItem>());
            tables.changeTableOccupiedStatus(walkInReservation.getTableID(), true);
            System.out.println("Go back to Order Manager to add items to order\n");
        }       
    }
}
