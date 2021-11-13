import java.util.ArrayList;

/**
 * Manager class that provides the functionalities of: 
 * creating a new membership entry, 
 * checking whether a customer has a membership,
 * retrieving membership of a customer, 
 * updating details in a membership entry,
 * removing a membership entry, 
 * and printing entry(ies) in the membership record.
 */
public class MembershipManager {
	
	/**
	 * The membership record of the restaurant.
	 * An array list of all membership entries in the membership record.
	 */
	private ArrayList<Membership> memberships;
	
	/**
	 * Constructor specifying the membership record for the restaurant.
	 * 
	 * @param members An array list of all membership entries in the membership record
	 */
	public MembershipManager(ArrayList<Membership> members) {
		this.memberships = members;
	}
	
	/**
	 * Creates and adds a new membership entry into the membership record.
	 * 
	 * @param name Name of the customer
	 * @param contact Contact number of the customer
	 */
	public void createMembership(String name, int contact) {
		this.memberships.add(new Membership(name,contact));
		System.out.println("New membership has been created!");
	}

	/**
	 * Retrieves a membership entry from the membership record 
	 * by providing the name and contact number of the customer.
	 * 
	 * @param name Name of the customer
	 * @param contact Contact number of the customer
	 * @return the requested membership entry
	 */
	public Membership getMembership(String name, int contact) {
		for (int i = 0; i<memberships.size(); i++){
			if (contact == memberships.get(i).getContact() && name.equals(memberships.get(i).getName())){
				return memberships.get(i);
			}
		}
		System.out.println("Membership does not exist.");
		return null;
	}
	
	/**
	 * Retrieves a membership entry from the membership record 
	 * by providing the contact number of the customer.
	 * 
	 * @param contact Contact number of the customer
	 * @return the requested membership entry
	 */
	public Membership getMembershipByContact(int contact) {
		for (int i = 0; i<memberships.size(); i++){
			if (contact == memberships.get(i).getContact()){
				return memberships.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retrieves a membership entry from the membership record 
	 * by providing the name of the customer.
	 * 
	 * @param name Name of the customer
	 * @return the requested membership entry
	 */
	public Membership getMembershipByName(String name) {
		for (int i = 0; i<memberships.size(); i++){
			if (name.equals(memberships.get(i).getName())){
				return memberships.get(i);
			}
		}
		System.out.println("Membership does not exist.");
		return null;
	}
	
	/**
	 * Checks whether there exists a membership entry under 
	 * a specified contact number.
	 * 
	 * @param contact Contact number of the customer
	 * @return a boolean whether the customer with the specified contact number has a membership
	 */
	public boolean isMember(int contact) {
		Membership membership = getMembershipByContact(contact);
		if (membership == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Allows user to update details of a membership entry 
	 * under the specified name and contact number.
	 * 
	 * @param name Name of the customer
	 * @param contact Contact number of the customer
	 */
	public void updateMembership(String name, int contact) {
		Membership membership = getMembership(name,contact);
		if (membership == null) {
			return;
		}
		System.out.println();
		
		int choice = UserInput.nextInt("Would you like to:\n" +
				"1. update Name\n" +
				"2. update Contact Number\n"+
				"3. update both Name and Contact Number\n",1,3);
		
		switch (choice) {
		case 3:
		case 1:
			String newName = UserInput.getString("Enter Updated Name : ");
  		  	String prevName = membership.getName();
  		  	membership.setName(newName);
			System.out.println("Member's name : " + prevName + " has been updated to " + newName);
			if (choice == 1) {
				break;
			}
		case 2:
			int newContact = UserInput.getContact("Enter Updated Contact Number : ");
  		    int prevContactPrice = membership.getContact();
  		    membership.setContact(newContact);
  		    System.out.println("Member's contact : " + prevContactPrice + " has been updated to " + newContact);
	  		if (choice == 1) {
					break;
			}
			
		}
		
	}
	

	/**
	 * Removes an entry in the membership record 
	 * by specifying the name and contact number in that membership entry.
	 * 
	 * @param name Name of the customer
	 * @param contact Contact number of the customer
	 */
	public void removeMembership(String name, int contact){
		Membership membership = getMembership(name,contact);
		if (membership == null) {
			System.out.println("There is no membership under this name "+ name+" and contact "+contact);
		}
		memberships.remove(membership);
		System.out.println("Membership has been removed!");
		
	}
	
	/**
	 * Prints an entry in the membership record.
	 * 
	 * @param membership Membership entry to be printed
	 */
	public void printMembership(Membership membership) {
		System.out.println("Membership:\nName: "+membership.getName()+"\nContact number: "+membership.getContact());
	}
	
	/**
	 * Prints all entries in the membership record.
	 */
	public void printAllMembers() {
		for (int i = 0; i<memberships.size(); i++){
			this.printMembership(memberships.get(i));
			System.out.println();
		}
	}
}