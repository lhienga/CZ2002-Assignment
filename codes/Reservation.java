import java.util.ArrayList;
import java.util.Date;
public class Reservation {

	private Date date;
	private Date bookingTime;
	private int numOfPax;
	private String name;
	private int contact;
	private Date expiryTime;
	private boolean smokingOrNot;
	private int tableCapacity;
	public int EXPIRYDURATION = 30;

	/**
	 * 
	 * @param date
	 * @param bookTime
	 * @param numOfPax
	 * @param name
	 * @param contact
	 * @param smoking
	 */
	public Reservation(Date date, Date bookTime, int numOfPax, String name, int contact, boolean smoking) {
		// TODO - implement Reservation.Reservation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param contact
	 */
	public boolean IsTableExpired(int contact) {
		// TODO - implement Reservation.IsTableExpired
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numOfPax
	 */
	public int getTableCapacity(int numOfPax) {
		return this.tableCapacity;
	}

	public Date getDate() {
		return this.date;
	}

	public Date getBookingTime() {
		return this.bookingTime;
	}

	public int getNumOfPax() {
		return this.numOfPax;
	}

	public String getName() {
		return this.name;
	}

	public int getContact() {
		return this.contact;
	}

	public Date getExpiryTime() {
		return this.expiryTime;
	}

	public boolean getSmoking() {
		// TODO - implement Reservation.getSmoking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setBookingTime(Date date) {
		this.bookingTime = date;
	}

	/**
	 * 
	 * @param num
	 */
	public void setNumOfPax(int num) {
		this.numOfPax = num;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param contact
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

	/**
	 * 
	 * @param date
	 */
	public void setExpiryTime(Date date) {
		this.expiryTime = date;
	}

	/**
	 * 
	 * @param smoking
	 */
	public void setSmoking(boolean smoking) {
		// TODO - implement Reservation.setSmoking
		throw new UnsupportedOperationException();
	}

}