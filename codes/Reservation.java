import java.util.Calendar;

import java.io.Serializable;
public class Reservation implements Serializable {

	private Calendar bookingTime;
	private int numOfPax;
	private String name;
	private int contact;
	private Calendar expiryTime;
	public int EXPIRYDURATION = 30;
	private int tableid;

	/**
	 * constructor of reservation - 1 contact number can only book 1 reservation
	 * @param bookTime booking time
	 * @param numOfPax number of customers
	 * @param name customer's name
	 * @param contact customer's contact number
	 * @param smoking smoking option
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
	 * @param date booking time
	 */
	public void setBookingTime(Calendar date) {
		this.bookingTime = date;
	}

	/**
	 * set number of customers
	 * @param num number of customers coming together
	 */
	public void setNumOfPax(int num) {
		this.numOfPax = num;
	}

	/**
	 * set customer's name
	 * @param name customer's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set customer's contact number
	 * @param contact customer's contact number
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

	/**
	 * set reservation expiry time
	 * @param date expiry time
	 */
	public void setExpiryTime(Calendar date) {
		this.expiryTime = date;
	}

	/**
	 * get table id of the reservation
	 * @return table id
	 */
	public int getTableID() {
		return this.tableid;
	}

	/**
	 * set table id for reservation
	 * @param tableid table id
	 */
	public void setTableID(int tableid) {
		this.tableid = tableid;
	}
}