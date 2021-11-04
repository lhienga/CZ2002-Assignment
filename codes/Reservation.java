import java.util.Calendar;

public class Reservation {

	private Calendar bookingTime;
	private int numOfPax;
	private String name;
	private int contact;
	private Calendar expiryTime;
	public int EXPIRYDURATION = 30;
	private int tableid;
	public int RESERVATIONDURATION=3; 

	/**
	 * constructor of reservation - 1 contact number can only book 1 reservation
	 * @param bookTime
	 * @param numOfPax
	 * @param name
	 * @param contact
	 * @param smoking
	 */
	public Reservation(Calendar bookTime, int numOfPax, String name, int contact, int tableid) {
		// TODO - implement Reservation.Reservation
		this.bookingTime = bookTime;
		this.numOfPax = numOfPax;
		this.name = name;
		this.contact = contact;
		this.tableid = tableid;
		Calendar expiryTime = (Calendar) bookTime.clone();
		expiryTime.add(Calendar.HOUR_OF_DAY, +RESERVATIONDURATION);
		this.expiryTime = expiryTime;
	}


	/**
	 * get booking time of reservation
	 * @return booking time
	 */
	public Calendar getBookingTime() {
		return this.bookingTime;
	}

	/**
	 * get number of customers
	 * @return number of customers
	 */
	public int getNumOfPax() {
		return this.numOfPax;
	}

	/**
	 * get customer's name
	 * @return customer's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get customer's contact
	 * @return customer's contact
	 */
	public int getContact() {
		return this.contact;
	}

	/**
	 * get expiry time
	 * @return expiry time
	 */
	public Calendar getExpiryTime() {
		return this.expiryTime;
	}

	/**
	 * set booking time
	 * @param date
	 */
	public void setBookingTime(Calendar date) {
		this.bookingTime = date;
	}

	/**
	 * set number of customers
	 * @param num
	 */
	public void setNumOfPax(int num) {
		this.numOfPax = num;
	}

	/**
	 * set customer's name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set customer's contact number
	 * @param contact
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

	/**
	 * set reservation expiry time
	 * @param date
	 */
	public void setExpiryTime(Calendar date) {
		this.expiryTime = date;
	}

	/**
	 * get table id of the reservation
	 * @return table id
	 */
	public int getTableID() {
		// TODO - implement Reservation.getTableID
		return this.tableid;
	}

	/**
	 * set table id for reservation
	 * @param tableid
	 */
	public void setTableID(int tableid) {
		// TODO - implement Reservation.setTableID
		this.tableid = tableid;
	}
}
