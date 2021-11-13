import java.io.Serializable;

/**
 * Represents an entry in the membership record of the restaurant.
 */
public class Membership implements Serializable{
	
	/**
	 * Name of the customer in this membership
	 */
	private String name;
	
	/**
	 * Contact number of the customer in this membership
	 */
	private int contact;
	
	/**
	 * Constructor specifying the name and contact number of the customer 
	 * in this membership that is to be created.
	 * 
	 * @param name Name of the customer
	 * @param contact Contact number of the customer
	 */
	public Membership(String name, int contact) {
		this.name = name;
		this.contact = contact;
	}
	
	/**
	 * Gets the name of the customer in this membership.
	 * 
	 * @return name of the customer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the customer in this membership.
	 * 
	 * @param name Name of the customer
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the contact number of the customer in this membership.
	 * 
	 * @return contact number of the customer
	 */
	public int getContact() {
		return contact;
	}
	
	/**
	 * Sets the contact number of the customer in this membership.
	 * 
	 * @param contact Contact number of the customer
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}
}