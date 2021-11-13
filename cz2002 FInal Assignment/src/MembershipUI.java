/**
 * Provides a UI when the 'Membership Manager' option 
 * is selected from the 'Main Menu'.
 */
public class MembershipUI {
	
	/**
	 * Displays the options under the 'Membership Manager' option, 
	 * namely to create a new membership entry, 
     * remove a membership entry, 
     * print a membership entry, 
     * print the entire membership record,  
     * and update details of a membership entry.
	 * 
	 * @param members Membership record of the restaurant
	 */
	public static void manageMembersOptions(MembershipManager members) {
		String name;
		int contact;
		Membership member;
		int choice;
		
		do {
			System.out.println();

			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Add Member\n"+
					"2. Remove Member\n" +
					"3. Print Member Details\n" +
					"4. Print All Member Details\n"+
					"5. Update Member Details\n" +
					"ENTER 0 to return to main menu\n",0,5);
			System.out.println();
			switch (choice) {
			case 1:
				name = UserInput.getString("Please enter name: (Enter -1 to exit) ");
				if (name.compareTo("-1")==0) break;
				contact = UserInput.nextInt("Please enter contact number: (Enter -1 to exit) ");
				if (contact == -1) break;
				members.createMembership(name,contact);

				break;
			case 2:
				name = UserInput.getString("Please enter name: (Enter -1 to exit) ");
				if (name.compareTo("-1")==0) break;
				contact = UserInput.nextInt("Please enter contact number: (Enter -1 to exit) ");
				if (contact == -1) break;
				members.removeMembership(name, contact);
				break;
			case 3:
				name = UserInput.getString("Please enter name: (Enter -1 to exit) ");
				if (name.compareTo("-1")==0) break;
				member = members.getMembershipByName(name);
				if (member == null) 
					break;
				members.printMembership(member);
				
				break;
			case 4:
				System.out.println("Details of all members:\n");
				members.printAllMembers();
				break;
			case 5:
				name = UserInput.getString("Please enter name: (Enter -1 to exit) ");
				if (name.compareTo("-1")==0) break;
				contact = UserInput.nextInt("Please enter contact number: (Enter -1 to exit) ");
				if (contact == -1) break;
				members.updateMembership(name, contact);
				break;
				
			}
			
		}while (choice != 0);

	}
}