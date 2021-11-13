import java.util.ArrayList;

/**
 * Manager class for staffs.
 */
public class StaffManager {

	/**
	 * List of all staffs working for the restaurant. The staff record.
	 */
	private ArrayList<Staff> staffs;
	
	/**
	 * Constructor specifying the list of all staffs.
	 * 
	 * @param staffs List of all staffs working for the restaurant
	 */
	public StaffManager(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}

	/**
	 * Adds a new staff to the staff record.
	 * 
	 * @param name Name of this staff
	 * @param gender gender of this staff. 
	 * 'M' for male and 'F' for female
	 * @param id Staff ID of this staff
	 * @param title Job title of this staff
	 */
	public void addStaff(String name, char gender, int id, Staff.JOB title) {
		Staff curStaff = this.getStaffByID(id);
		if (curStaff!=null){
			System.out.println("This staff ID existed!");
			return;
		}
		Staff newStaff = new Staff(name, gender, id, title);
		staffs.add(newStaff);
		System.out.println("New staff added!");
	}

	/**
	 * Removes a staff from the staff record.
	 * 
	 * @param id Staff ID of this staff
	 */
	public void removeStaff(int id) {
		Staff curStaff = this.getStaffByID(id);
		if (curStaff==null){
			System.out.println("This Staff ID does not exist!");
			return;
		}
		staffs.remove(curStaff);
		System.out.println("Staff removed!");
	}

	/**
	 * Gets the details of a staff.
	 * 
	 * @param id Staff ID of this staff
	 * @return the requested staff
	 */
	public Staff getStaffByID(int id) {
		for (int i =0; i<staffs.size(); i++){
			if (staffs.get(i).getID()==id){
				return staffs.get(i);
			}
		}
		return null;
	}

	/**
	 * Prints details of a staff.
	 * 
	 * @param id Staff ID of this staff
	 */
	public void printStaffByID(int id) {
		Staff curStaff = getStaffByID(id);
		if (curStaff==null){
			System.out.println("Staff ID does not exist!");
			return;
		}
		System.out.println("Staff ID: "+curStaff.getID()+"\n"+
						   "Staff name: "+curStaff.getName()+"\n"+
						   "Gender: "+curStaff.getGender()+"\n"+						   
						   "Title: "+curStaff.getJobTitle());
	}
	
	/**
	 * Prints all staffs and their details.
	 */
	public void printAllStaffs() {
		for (int i = 0; i<staffs.size(); i++){
			this.printStaffByID(staffs.get(i).getID());
			System.out.println();
		}
	}

	/**
	 * Updates details of a staff.
	 * 
	 * @param id Staff ID of this staff
	 */
	public void updateStaffInfo(int id) {
		Staff curStaff = getStaffByID(id);
		if (curStaff==null){
			System.out.println("Staff ID does not exist!");
			return;
		}
		System.out.println("\nChoose staff information to update: ");
		System.out.println("(1) Staff name");
		System.out.println("(2) Staff gender");
		System.out.println("(3) Staff title");
		int choice = UserInput.nextInt("\nEnter your choice ", 1, 3);
		switch(choice){
			case 1:
			String newName = UserInput.getString("\nEnter staff's new name: ");
			curStaff.setName(newName);
			break;
			case 2:
			char gender = UserInput.getGender("\nEnter staff's new gender: ");
			curStaff.setGender(gender);
			break;
			case 3: 
			Staff.JOB newTitle = UserInput.getJobTitle("\nEnter staff's new title: ");
			curStaff.setJobTitle(newTitle);
			break;
		}
	}

}