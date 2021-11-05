import java.util.ArrayList;
import java.util.Calendar;
public class ReservationManager {

	private ArrayList<Reservation> reservations;
	public ArrayList<Reservation> settledReservations;
	public TableManager tableManager;
	public int RESERVATIONDURATION=3; 
	/**
	 * constructor of reservation manager
	 * @param tableManager
	 */
	public ReservationManager(TableManager tableManager) {
		this.tableManager = tableManager;
		reservations = new ArrayList<Reservation>();
	}
	public ReservationManager(TableManager tableManager, ArrayList<Reservation> reservations, ArrayList<Reservation> settledReservations) {
		this.tableManager = tableManager;
		this.reservations = reservations;
		this.settledReservations = settledReservations;
	}
	/**
	 * print all reservation
	 */
	public void printAllReservation() {
		for (int i = 0; i<reservations.size(); i++){
			int contact = reservations.get(i).getContact();
			printReservation(contact);
			System.out.println();
		}
	}

	/**
	 * print a reservation of a contact
	 * @param contact
	 */
	public void printReservation(int contact){
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
	 * get reservation by customer's contact
	 * @param contact
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
	 * find a table
	 * @param bookTime
	 * @param smoking
	 * @param numOfPax
	 * @param choice choice - 0 if normal reservation, 1 if walk-in
	 * @return
	 */
	public ArrayList<Integer> findAvailableTables(Calendar bookTime, boolean smoking, int numOfPax, int choice){
		//System.out.println("call findavai");
		ArrayList<Table> tables;
		if (choice == 0) {
			tables = tableManager.getAllTables();
		} else {
			tables = tableManager.getUnoccupiedTables();
		}

		ArrayList<Integer> possibleTables = new ArrayList<Integer>();
		Calendar bookingTime = bookTime;
		for (int i=0; i<tables.size(); i++){
			possibleTables.add(tables.get(i).getTableId());
			System.out.println("id added"+possibleTables.get(i));
		}
		
		for (int i = 0; i<reservations.size(); i++){
			int tableid = reservations.get(i).getTableID();
			Calendar reservedTime = reservations.get(i).getBookingTime();
			bookingTime.add(Calendar.HOUR_OF_DAY, -RESERVATIONDURATION);
			int lower = bookTime.get(Calendar.HOUR_OF_DAY);
			bookingTime.add(Calendar.HOUR_OF_DAY,+RESERVATIONDURATION*2);
			int upper = bookTime.get(Calendar.HOUR_OF_DAY);
			bookingTime.add(Calendar.HOUR_OF_DAY,-RESERVATIONDURATION);
			int reservedHour = reservedTime.get(Calendar.HOUR_OF_DAY);
			//System.out.println(upper+" "+ lower+" "+reservedHour);
			if (reservedHour<= upper && reservedHour>=lower){
				possibleTables.remove(possibleTables.indexOf(tableid));
				//System.out.println("remove " + tableid);
			}
		}
		return possibleTables;
	}

	
	/**
	 * create new reservation
	 * @param bookingTime
	 * @param choice - 0 if normal reservation, 1 if walk-in
	 * @return reservation
	 */
	public Reservation createReservation(Calendar bookingTime, int choice) {
		//find a table with suitable capacity and smoking option
		System.out.println("call create reser");
		int contact = UserInput.getContact("Please enter customer's contact number (format = 8 digit number): ");
		if (getReservationByContact(contact)!=null){
			System.out.println("This contact number has already booked a reservation!");
			return null;
		}
		String name = UserInput.getString("Please enter customer's name: ");
		int numOfPax = UserInput.nextInt("Please enter number of customers: ");
		boolean smoking = UserInput.getSmoking("Please choose Smoking option: (Y for smoking, N for non-smoking)\n");
		ArrayList<Table> tables = tableManager.getAllTables();
		ArrayList<Integer> availableTableIDs = findAvailableTables(bookingTime, smoking, numOfPax,choice);
		for (int i = 0; i< availableTableIDs.size(); i++){
			System.out.println("id "+ availableTableIDs.get(i));
		}
		if (availableTableIDs.size()==0){
			return null;
		}
		//assign to the first table on the list
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
		//int tableid = availableTableIDs.get(i);
		if (choice == 0) {
			tableManager.changeTableStatus(tableid, Table.STATUS.RESERVED);
		} else {
			tableManager.changeTableStatus(tableid, Table.STATUS.OCCUPIED);
		}
		Reservation reservation = new Reservation(bookingTime, numOfPax, name, contact, tableid);
		this.reservations.add(reservation);
		return reservation;
	}

	/**
	 * 
	 * @param contact
	 */
	public void updateReservationDetails(int contact) {
		int reservationType = UserInput.nextInt("Is it (1) pre-order or (2) walk-in reservation?", 1, 2);
		Reservation reservation = getReservationByContact(contact);
		if (reservation == null){
			System.out.println("There is no reservation under this contact number");
			return;
		}
		System.out.println("What do you want to update?");
		System.out.println("1.Update customer personal inforamtion"+ '\n'+
							"2.Update reservation information");
		int choice = UserInput.nextInt("Enter your choice", 1, 2);
		switch(choice){
			case 1:
			int newContact = UserInput.getContact("Enter new contact number");
			Reservation exist = getReservationByContact(newContact);
			if (exist!=null){
				System.out.println("This contact number exists!");
				return;
			}
			String newName = UserInput.getString("Enter new name");
			reservation.setContact(contact);
			reservation.setName(newName);
			break;
			case 2:
			int newNumPax = UserInput.nextInt("Enter new number of guests", 1, 10);
			boolean newSmoking = UserInput.getSmoking("Enter new smoking option");
			Calendar newBookingTime = UserInput.getDateTime("Enter new booking time");
			Reservation updated = createReservation(newBookingTime, reservationType);
			if (updated==null){
				System.out.println("Cannot find any table");
				return;
			}
			reservations.remove(reservation);
			reservations.add(updated);
			
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * remove reservation by contact number
	 * @param contact
	 */
	public void removeReservationByContact(int contact) {
		Reservation reservation = getReservationByContact(contact);
		if (reservation == null) {
			System.out.println("There is no reservation under this contact "+contact);
			return;
		}
		int tableId = reservation.getTableID();
		
		reservations.remove(reservation);
		// if there are other reservations under same table, change status to reserved
		for (Reservation r: reservations) {
			if (r.getTableID() == tableId) {
				tableManager.changeTableStatus(tableId, Table.STATUS.RESERVED);
				return;
			}
		}
		tableManager.changeTableStatus(reservation.getTableID(), Table.STATUS.AVAILABLE);
	}
	/**
	 * get expired reservations
	 * @return
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
	 * print expired reservation
	 */
	public void printExpiredReservations() {
		// TODO - implement ReservationManager.printExpiredReservations
		ArrayList<Reservation> expiredReservations = getExpiredReservations();
		for (int i = 0; i<expiredReservations.size(); i++){
			printReservation(expiredReservations.get(i).getContact());
		}
	}

	/**
	 * get all reservations
	 * @return
	 */
	public ArrayList<Reservation> getAllReservations() {
		// TODO - implement ReservationManager.getAllReservations

		return reservations;
	}

	/**
	 * check if a reservation expired
	 * @param contact
	 */
	public boolean isReservationExpired(int contact) {
		// TODO - implement ReservationManager.isReservationExpired
		Reservation reservation = getReservationByContact(contact);
		Calendar expiryTime = reservation.getExpiryTime();
		if (expiryTime.compareTo(Calendar.getInstance())<=0){
				return true;
		}
		return false;
		
	}

	/**
	 * get not expired reservations
	 * @return
	 */
	public ArrayList<Reservation> getNotExpiredReservations() {
		// TODO - implement ReservationManager.getNotExpiredReservations
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
	 * print not expired reservations
	 */
	public void printNotExpiredReservations() {
		// TODO - implement ReservationManager.printNotExpiredReservations
		ArrayList<Reservation> validReservations = getNotExpiredReservations();
		for (int i = 0; i<validReservations.size(); i++){
			printReservation(validReservations.get(i).getContact());
		}
	}

	
	public void releaseTablesWithExpiredReservations() {
		// TODO - implement ReservationManager.printNotExpiredReservations
		ArrayList<Reservation> expiredReservations = getExpiredReservations();
		for (Reservation rv : expiredReservations) {
			tableManager.changeTableStatus(rv.getTableID(), Table.STATUS.AVAILABLE);
		}
	}
}
