import java.util.ArrayList;
import java.util.Calendar;

/**
 * Manager class for reservations.
 */
public class ReservationManager {

	/**
	 * The list of all active reservations
	 */
	private ArrayList<Reservation> reservations;
	
	/**
	 * The list of all settled reservations
	 */
	private ArrayList<Reservation> settledReservations;
	
	/**
	 * Table manager of the restaurant
	 */
	public TableManager tableManager;
	
	/**
	 * Order manager of the restaurant
	 */
	public OrderManager orders;
	
	/**
	 * Fixed assumed eating duration of 2 hours
	 */
	public int EATINGDURATION=2; 

	/**
	 * Constructor specifying the table manager, 
	 * order manager, list of all active reservations, 
	 * and list of all settled reservations of the restaurant.
	 * 
	 * @param tableManager Table manager for this reservation manager
	 * @param orders Order manager for this reservation manager
	 * @param reservations List of all active reservations
	 * @param settledReservations List of all settled reservations
	 */
	public ReservationManager(TableManager tableManager, OrderManager orders, ArrayList<Reservation> reservations, ArrayList<Reservation> settledReservations) {
		this.tableManager = tableManager;
		this.orders = orders;
		this.reservations = reservations;
		this.settledReservations = settledReservations;
	}
	
	/**
	 * Prints all active reservations.
	 */
	public void printAllReservation() {
		for (int i = 0; i<reservations.size(); i++){
			int contact = reservations.get(i).getContact();
			printReservation(contact);
			System.out.println();
		}
	}

	/**
	 * Prints a reservation by specifying the contact number of the customer making this reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 */
	public void printReservation(int contact){
		removeExpiredReservations();
		Reservation reservation = getReservationByContact(contact);
		
		if (reservation!=null){
			System.out.println("Reservation information:");
			System.out.println("Customer contact number: "+reservation.getContact()+"\n"+
						   	   "Customer name: "+reservation.getName()+"\n"+
						       "Number of customers: "+reservation.getNumOfPax()+"\n"+						   
						       "Reserved table ID: "+reservation.getTableID()+"\n"+
							   "Reservation Time: "+ reservation.getBookingTime().getTime()+"\n"+
							   "Reservation Expiry Time: "+ reservation.getExpiryTime().getTime());
			
		return;
		}
		System.out.println("There is no reservation for this contact");
	}
	
	/**
	 * Gets an active reservation by specifying the contact number of the customer making this reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 * @return the requested active reservation
	 */
	public Reservation getReservationByContact(int contact) {
		for (int i = 0; i<reservations.size(); i++){
			if (contact == reservations.get(i).getContact()){
				return reservations.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets a settled reservation by specifying the contact number of the customer making this reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 * @return the requested settled reservation
	 */
	public Reservation getSettledReservationByContact(int contact) {
		for (int i = 0; i<settledReservations.size(); i++){
			if (contact == settledReservations.get(i).getContact()){
				return settledReservations.get(i);
			}
		}
		return null;
	}

	/**
	 * Finds available tables for a reservation.
	 * 
	 * @param bookTime Booking time of this reservation
	 * @param smoking Smoking/Non smoking option of this reservation
	 * @param numOfPax Number of people under this reservation
	 * @param choice 0 if normal reservation, 1 if walk&#8208;in
	 * @return list of all table numbers available for this reservation
	 */
	public ArrayList<Integer> findAvailableTables(Calendar bookTime, boolean smoking, int numOfPax, int choice){
		ArrayList<Table> tables;
		if (choice == 0) {
			tables = tableManager.getAllTables();
		} else {
			tables = tableManager.getAvailableTables();
		}

		ArrayList<Integer> possibleTables = new ArrayList<Integer>();
		Calendar upperTime = (Calendar) bookTime.clone();
		Calendar lowerTime = (Calendar) bookTime.clone();
		lowerTime.add(Calendar.HOUR, -EATINGDURATION);
		upperTime.add(Calendar.HOUR, +EATINGDURATION);
		for (int i=0; i<tables.size(); i++){
			possibleTables.add(tables.get(i).getTableId());
		}
		
		for (int i = 0; i<reservations.size(); i++){
			int tableid = reservations.get(i).getTableID();
			Calendar reservedTime = reservations.get(i).getBookingTime();
			if (reservedTime.compareTo(upperTime)<0 && reservedTime.compareTo(lowerTime)>0){
				try{
					possibleTables.remove(possibleTables.indexOf(tableid));
				}
				catch(IndexOutOfBoundsException e){
				}
			}
		}
		return possibleTables;
	}

	/**
	 * Creates a new reservation.
	 * 
	 * @param contact Contact number of customer making this reservation
	 * @param name Name of the customer making this reservation
	 * @param bookingTime Booking time of this reservation
	 * @param choice 0 if normal reservation, 1 if walk&#8208;in
	 * @param orders Order manager
	 * @return the created reservation
	 */
	public Reservation createReservation(int contact, String name, Calendar bookingTime, int choice,OrderManager orders) {
		// find a table with suitable capacity and smoking option
		removeExpiredReservations();
		if (getReservationByContact(contact)!=null){
			System.out.println("This contact number has already booked a reservation!");
			return null;
		}
		int numOfPax = UserInput.nextInt("Please enter number of persons: (Enter -1 to exit) ",1,10);
		if (numOfPax == -1 ) return null;
		boolean smoking = UserInput.getSmoking("Please enter Y for smoking, N for non-smoking) ");
		ArrayList<Table> tables = tableManager.getAllTables();
		// choice is used to get suitable tables
		ArrayList<Integer> availableTableIDs = findAvailableTables(bookingTime, smoking, numOfPax,choice);
		if (availableTableIDs.size()==0){
			return null;
		}
		// assign to the first table on the list
		boolean found = false;
		int i = 0;
		int tableid = 0;
		while (!found){
			if (i>=availableTableIDs.size()){
				return null;
			}
			tableid = availableTableIDs.get(i);
			if (tables.get(tableid-1).getCapacity()!= tableManager.calculateTableCapacity(numOfPax)){
				i+=1;
			}
			else if (smoking && !tables.get(tableid-1).getSmoking()){
				i+=1;
			}
			else if (!smoking && tables.get(tableid-1).getSmoking()){
				i+=1;
			}
			else found = true;

		}
		Reservation reservation = new Reservation(bookingTime, numOfPax, name, contact, tableid);
		
		if (choice == 0) { // normal reservation
			tableManager.changeTableReservedStatus(tableid, true);
		} else { // walk in
			tableManager.changeTableOccupiedStatus(tableid, true);
		}
		this.reservations.add(reservation);
		
		return reservation;
	}

	/**
	 * Update details of a reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 */
	public void updateReservationDetails(int contact) {
		Reservation reservation = getReservationByContact(contact);
		if (reservation == null){
			System.out.println("There is no reservation under this contact number!");
			
		}
		int reservationType = UserInput.nextInt("Is it (1) normal reservation or (2) walk-in reservation? (Enter -1 to exit) ", 1, 2);
		if (reservationType==-1) return;
		if (reservationType == 2) {
			System.out.println("You can only update customer personal information:\n");
			int newContact = UserInput.getContact("Enter new contact number: (Enter -1 to exit) ");
			if (newContact == -1) return;
			Reservation exist = getReservationByContact(newContact);
			if (exist!=null){
				System.out.println("This contact number has existing reservation");
				
			}
			String newName = UserInput.getString("Enter new name: (Enter -1 to exit) ");
			if (newName.compareTo("-1")==0) return;
			reservation.setContact(newContact);
			reservation.setName(newName);
			System.out.println("\nReservation updated!");
			
		}
		if (reservationType == 1) {
			System.out.println("\nWhat do you want to update?");
			System.out.println("1.Update customer personal information"+ '\n'+
								"2.Update reservation information");
			int choice = UserInput.nextInt("Enter your choice (Enter -1 to exit) ", 1, 2);
			if (choice == -1 ) return;
			switch(choice){
				case 1:
					int newContact = UserInput.getContact("Enter new contact number: (Enter -1 to exit) ");
					if (newContact == -1) return;
					Reservation exist = getReservationByContact(newContact);
					if (exist!=null){
						System.out.println("This contact number has existing reservation");
						
					}
					String newName = UserInput.getString("Enter new name: (Enter -1 to exit) ");
					if (newName.compareTo("-1")==0) return;
					reservation.setContact(newContact);
					reservation.setName(newName);
					System.out.println("\nReservation updated!");
					break;
					
				case 2:
					Calendar newBookingTime;
					if (reservationType == 1){
						newBookingTime = UserInput.getDateTime("Enter new booking time: (Enter -1 to exit) ");
						if (newBookingTime == null) return;
					}
					else {
						newBookingTime = reservation.getBookingTime();
					}
					removeReservationByContact(contact);
					System.out.println("The new booking time was captured. Please continue updating reservation.\n");
					Reservation updated = createReservation(contact, reservation.getName(), newBookingTime, reservationType-1, orders);
					if (updated==null){
						System.out.println("\nCannot find any table!");
						reservations.add(reservation);
						return;
						
					}
					System.out.println("\nReservation updated!");
			}
	
		}
	}

	/**
	 * Removes a reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 */
	public void removeReservationByContact(int contact) {
		Reservation reservation = getReservationByContact(contact);
		if (reservation == null) {
			System.out.println("There is no reservation under this contact "+contact);
			return;
		}
		int tableId = reservation.getTableID();
		
		reservations.remove(reservation);
		// if there are no other reservations under same table, change reserved status to not reserved
		for (Reservation r: reservations) {
			if (r.getTableID() == tableId) {
				System.out.println("Reservation successfully removed!");
				return;
			}
		}
		tableManager.changeTableReservedStatus(tableId, false);
		System.out.println("Reservation successfully removed!");
	}
	
	/**
	 * Moves a reservation from active to settled reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 */
	public void moveReservationToSettledReservations(int contact) {
		Reservation reservation = getReservationByContact(contact);
		if (reservation == null) {
			System.out.println("There is no reservation under this contact "+contact);
			return;
		}
		
		settledReservations.add(reservation);
		removeReservationByContact(contact);

	}
	
	/**
	 * Gets the list of all expired reservations.
	 * 
	 * @return list of all expired reservations
	 */
	public ArrayList<Reservation> getExpiredReservations() {
		ArrayList<Reservation> expiredReservations = new ArrayList<Reservation>();
		for (int i = 0; i<reservations.size(); i++){
			Calendar expiryTime = reservations.get(i).getExpiryTime();
			if (expiryTime.compareTo(Calendar.getInstance())<=0){
				expiredReservations.add(reservations.get(i));
			}
			
		}
		return expiredReservations;
	}

	/**
	 * Prints all expired reservations.
	 */
	public void printExpiredReservations() {
		ArrayList<Reservation> expiredReservations = getExpiredReservations();
		for (int i = 0; i<expiredReservations.size(); i++){
			printReservation(expiredReservations.get(i).getContact());
		}
	}

	/**
	 * Gets the list of all active reservations.
	 * 
	 * @return list of all active reservations
	 */
	public ArrayList<Reservation> getAllReservations() {

		return reservations;
	}

	/**
	 * Checks if a reservation has expired.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 * @return expiration status of this reservation
	 */
	public boolean isReservationExpired(int contact) {
		Reservation reservation = getReservationByContact(contact);
		Calendar expiryTime = reservation.getExpiryTime();
		if (expiryTime.compareTo(Calendar.getInstance())<=0){
				return true;
		}
		return false;
		
	}

	/**
	 * Gets the list of all non&#8208;expired reservations.
	 * 
	 * @return list of all non&#8208;expired reservations
	 */
	public ArrayList<Reservation> getNotExpiredReservations() {
		ArrayList<Reservation> validReservations = new ArrayList<Reservation>();
		for (int i = 0; i<reservations.size(); i++){
			Calendar expiryTime = reservations.get(i).getExpiryTime();
			if (expiryTime.compareTo(Calendar.getInstance())>0){
				validReservations.add(reservations.get(i));
			}	
		}
		return validReservations;
	}

	/**
	 * Prints all non&#8208;expired reservations.
	 */
	public void printNotExpiredReservations() {
		ArrayList<Reservation> validReservations = getNotExpiredReservations();
		for (int i = 0; i<validReservations.size(); i++){
			printReservation(validReservations.get(i).getContact());
		}
	}

	/**
	 * Removes expired reservations.
	 */
	public void removeExpiredReservations() {
		ArrayList<Reservation> expiredReservations = getExpiredReservations();
		for (Reservation rv : expiredReservations) {
			// if order for reservation is still there (it means customer hasn't leave yet/stil eating, don't remove the reservation
			if (orders.getOrderByContactNum(rv.getContact())==null) {
				removeReservationByContact(rv.getContact());
			}
		}
	}
}
