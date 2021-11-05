import java.io.Serializable;
public class Membership implements Serializable{
	
	private String name;
	private int contact;
	
	/**
	 * Constructor for membership
	 * @param name
	 * @param contact
	 */
	public Membership(String name, int contact) {
		this.name = name;
		this.contact = contact;
	}
	
	/**
	 * get name of member
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set name of member
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get contact of member
	 * @return contact
	 */
	public int getContact() {
		return contact;
	}
	
	/**
	 * set contact of member
	 * @param contact new contact
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}
}
