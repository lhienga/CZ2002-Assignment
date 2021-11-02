import java.util.ArrayList;
import java.util.Calendar;
public class ReservationManager {

	private ArrayList<Reservation> reservations;
	private TableManager tableManager;
	public int RESERVATIONDURATION=3; 
	/**
	 * constructor of reservation manager
	 * @param tableManager
	 */
	public ReservationManager(TableManager tableManager) {
		this.tableManager = tableManager;
		reservations = new ArrayList<Reservation>();
	}

	/**
	 * print all reservation
	 */
	public void printAllReservation() {
		for (int i = 0; i<reservations.size(); i++){
			int contact = reservations.get(i).getContact();
			printReservation(contact);
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
							   "Reservation Time:"+ reservation.getBookingTime()+"\n"+
							   "Reservation Expiry Time: "+ reservation.getExpiryTime());
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
	 * @param numOfPax
	 * @param smoking
	 */
	public ArrayList<Integer> findAvailableTables(Calendar bookTime, boolean smoking, int numOfPax){
		ArrayList<Table> tables = tableManager.getAvailableTables();
		ArrayList<Integer> possibleTables = new ArrayList<Integer>();
		for (int i=0; i<tables.size(); i++){
			possibleTables.add(tables.get(i).getTableId());
		}
		
		for (int i = 0; i<reservations.size(); i++){
			int tableid = reservations.get(i).getTableID();
			Calendar reservedTime = reservations.get(i).getBookingTime();
			bookTime.add(Calendar.HOUR_OF_DAY, -RESERVATIONDURATION);
			int lower = bookTime.get(Calendar.HOUR_OF_DAY);
			bookTime.add(Calendar.HOUR_OF_DAY,+RESERVATIONDURATION*2);
			int upper = bookTime.get(Calendar.HOUR_OF_DAY);
			int reservedHour = reservedTime.get(Calendar.HOUR_OF_DAY);
			if (reservedHour<= upper && reservedHour>=lower){
				possibleTables.remove(tableid);
			}
			else if (tables.get(i).getCapacity()!= tableManager.calculateTableCapacity(numOfPax)){
				possibleTables.remove(tableid);
			}
			else if (smoking && !tables.get(i).getSmoking()){
				possibleTables.remove(tableid);
			}
		}
		return possibleTables;
	}
	/**
	 * create new reservation
	 * @param bookTime
	 * @param numOfPax
	 * @param name
	 * @param contact
	 * @param smoking
	 * @return
	 */
	public Reservation createReservation(Calendar bookingTime) {
		//find a table with suitable capacity and smoking option
		int numOfPax = UserInput.nextInt("Please enter number of customers: ");
		String name = UserInput.getString("Please enter customer's name: ");
		int contact = UserInput.getContact("Please enter customer's contact number: ");
		boolean smoking = UserInput.getSmoking("Please choose Smoking option: ");
		ArrayList<Integer> availableTableIDs = findAvailableTables(bookingTime, smoking, numOfPax);
		if (availableTableIDs.size()==0){
			return null;
		}
		//assign to the first table on the list
		int tableid = availableTableIDs.get(0);
		Reservation reservation = new Reservation(bookingTime, numOfPax, name, contact, tableid);
		this.reservations.add(reservation);
		return reservation;
	}

	/**
	 * 
	 * @param contact
	 */
	public void updateReservationDetails(int contact) {
		
		throw new UnsupportedOperationException();
	}

	/**
	 * remove reservation by contact numeber
	 * @param contact
	 */
	public void removeReservationByContact(int contact) {
		Reservation reservation = getReservationByContact(contact);
		reservations.remove(reservation);
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

}