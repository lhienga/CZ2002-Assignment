import java.util.Calendar;
import java.io.Serializable;

/**
 * Represents a reservation, can be either a booking or walk&#8208;in.
 * There can only be 1 reservation under each contact number.
 */
public class Reservation implements Serializable {

	/**
	 * Booking time of this reservation
	 */
	private Calendar bookingTime;
	
	/**
	 * Number of people in this reservation
	 */
	private int numOfPax;
	
	/**
	 * Name of the customer making this reservation
	 */
	private String name;
	
	/**
	 * Contact number of the customer making this reservation
	 */
	private int contact;
	
	/**
	 * Expiration time of this reservation
	 */
	private Calendar expiryTime;
	
	/**
	 * Fixed duration of 30 minutes from booking time, after which the reservation will expire
	 */
	public int EXPIRYDURATION = 30;
	
	/**
	 * Table number allocated to this reservation
	 */
	private int tableid;

	/**
	 * Constructor specifying the booking time, 
	 * number of people under this reservation, 
	 * name and contact number of the customer making this reservation, 
	 * and the table number allocated to this reservation.
	 * 
	 * @param bookTime Booking time for this reservation
	 * @param numOfPax Number of people under this reservation
	 * @param name Name of the customer making this reservation
	 * @param contact Contact number of the customer making this reservation
	 * @param tableid Table number allocated to this reservation
	 */
	public Reservation(Calendar bookTime, int numOfPax, String name, int contact, int tableid) {
		this.bookingTime = bookTime;
		this.numOfPax = numOfPax;
		this.name = name;
		this.contact = contact;
		this.tableid = tableid;
		Calendar expiryTime = (Calendar) bookTime.clone();
		expiryTime.add(Calendar.MINUTE, +EXPIRYDURATION);
		this.expiryTime = expiryTime;
	}

	/**
	 * Gets the booking time of this reservation.
	 * 
	 * @return booking time of this reservation
	 */
	public Calendar getBookingTime() {
		return this.bookingTime;
	}

	/**
	 * Gets the number of people under this reservation.
	 * 
	 * @return number of people under this reservation
	 */
	public int getNumOfPax() {
		return this.numOfPax;
	}

	/**
	 * Gets the name of the customer making this reservation.
	 * 
	 * @return name of the customer making this reservation
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the contact number of the customer making this reservation.
	 * 
	 * @return contact number of the customer making this reservation
	 */
	public int getContact() {
		return this.contact;
	}

	/**
	 * Gets the expiration time of this reservation.
	 * 
	 * @return expiration time of this reservation
	 */
	public Calendar getExpiryTime() {
		return this.expiryTime;
	}

	/**
	 * Sets the booking time for this reservation.
	 * 
	 * @param date Booking date and time for this reservation.
	 */
	public void setBookingTime(Calendar date) {
		this.bookingTime = date;
	}

	/**
	 * Sets the number of people under this reservation.
	 * 
	 * @param num Number of people under this reservation
	 */
	public void setNumOfPax(int num) {
		this.numOfPax = num;
	}

	/**
	 * Sets the name of the customer making this reservation.
	 * 
	 * @param name Name of the customer making this reservation
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the contact number of the customer making this reservation.
	 * 
	 * @param contact Contact number of the customer making this reservation
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

	/**
	 * Sets the expiration time for this reservation.
	 * 
	 * @param date Expiration date and time for this reservation
	 */
	public void setExpiryTime(Calendar date) {
		this.expiryTime = date;
	}

	/**
	 * Gets the table number allocated to this reservation.
	 * 
	 * @return table number allocated to this reservation
	 */
	public int getTableID() {
		return this.tableid;
	}

	/**
	 * Sets the table number allocated to this reservation.
	 * 
	 * @param tableid Table number allocated to this reservation
	 */
	public void setTableID(int tableid) {
		this.tableid = tableid;
	}
}