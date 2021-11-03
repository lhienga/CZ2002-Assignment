import java.util.ArrayList;

public class MembershipManager {
	
	private ArrayList<Membership> memberships;
	
	/**
	 * Default constructor for memberships
	 */
	public MembershipManager() {
		if (memberships == null) {
			memberships = new ArrayList<Membership>();
			memberships.add(new Membership("Mary",98733789));
			memberships.add(new Membership("John",81642014));
			memberships.add(new Membership("Peter",91346100));
			memberships.add(new Membership("Jane",91841134));
			memberships.add(new Membership("Matthew",84272922));
			memberships.add(new Membership("James",92541289));
			memberships.add(new Membership("Sue",81417013));
			memberships.add(new Membership("Max",91789534));
			memberships.add(new Membership("Mark",90245824));
			memberships.add(new Membership("Phillip",82357203));
		}
	}
	
	/**
	 * Create a membership
	 * @param name Member's name
	 * @param contact Member's contact
	 */
	public void createMembership(String name, int contact) {
		this.memberships.add(new Membership(name,contact));
		System.out.println("New membership has been created!");
	}

	/**
	 * Get a membership by name and contact
	 * @param name Member's name
	 * @param contact Member's contact
	 * @return membership
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
	 * Get a membership by contact
	 * @param contact Member's contact
	 * @return membership
	 */
	public Membership getMembershipByContact(int contact) {
		for (int i = 0; i<memberships.size(); i++){
			if (contact == memberships.get(i).getContact()){
				return memberships.get(i);
			}
		}
		System.out.println("Membership does not exist.");
		return null;
	}
	
	/**
	 * Get a membership by name
	 * @param contact Member's name
	 * @return membership
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
	 * Check if contact has a membership
	 * @param contact
	 * @return true if is a member, false if not a member
	 */
	public boolean isMember(int contact) {
		Membership membership = getMembershipByContact(contact);
		if (membership == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * update membership
	 * @param name original name
	 * @param contact original contact
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
	 * remove membership
	 * @param name Member name
	 * @param contact Member contact
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
	 * print member's details
	 */
	public void printMembership(Membership membership) {
		System.out.println("Membership:\nName: "+membership.getName()+"\nContact number: "+membership.getContact());
	}
	
	/**
	 * print all members
	 */
	public void printAllMembers() {
		// TODO - implement StaffManager.printAllStaffs
		for (int i = 0; i<memberships.size(); i++){
			this.printMembership(memberships.get(i));
			System.out.println();
		}
	}
}
