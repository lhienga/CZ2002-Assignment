public class StaffUI {
    
	/**
	 * manage staff options
	 * @param staff staff manager
	 */
	public static void manageStaffOptions(StaffManager staff) {
		String name;
		char gender;
		int staffID;
		Staff.JOB jobTitle;	
		int choice;
		
		do {
			System.out.println();
			//manage staff choices
			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Add Staff\n"+
					"2. Remove Staff\n" +
					"3. Print Staff Details\n" +
					"4. Print All Staff Details\n"+
					"5. Update Staff Details\n" +
					"ENTER 0 to return to main menu\n",0,5);
			System.out.println();
			switch (choice) {
			case 1:
				name = UserInput.getString("Please enter name: (Enter -1 to exit) ");
				if (name.compareTo("-1")==0) break;
				gender = UserInput.getGender("Please enter M / F for gender: (Enter # to exit) ");
				if (gender == '#') break;
				staffID = UserInput.nextInt("Please enter staffID: (Enter -1 to exit) ");
				if (staffID == -1) break;
				jobTitle = UserInput.getJobTitle("Please enter the staff's designation (manager,cashier,part-time,full-time): (Enter -1 to exit) ");
				if (jobTitle == null) break;
				
				staff.addStaff(name, gender, staffID, jobTitle);
				break;
			case 2:
				staffID = UserInput.nextInt("Please enter staff's ID: (Enter -1 to exit) ");
				if (staffID == -1) break;
				staff.removeStaff(staffID);
				break;
			case 3:
				staffID = UserInput.nextInt("Please enter staff's ID: (Enter -1 to exit) ");
				if (staffID == -1) break;
				staff.printStaffByID(staffID);
				break;
			case 4:
				System.out.println("Details of all staff:\n");
				staff.printAllStaffs();
				break;
			case 5:
				staffID = UserInput.nextInt("Please enter staff's ID: (Enter -1 to exit) ");
				if (staffID == -1) break;
				staff.updateStaffInfo(staffID);
				break;
				
			}
			
		}while (choice != 0);

	}
    
}