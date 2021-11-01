import java.util.ArrayList;

public class StaffManager {

	private ArrayList<Staff> staffs;
	/**
	 * construct staff manager
	 */
	public StaffManager() {
		// TODO - implement StaffManager.StaffManager
		staffs = new ArrayList<Staff>();
	}

	/**
	 * add new staff
	 * @param name staff's name
	 * @param gender staff's gender
	 * @param id staff's ID
	 * @param title staff's title
	 */
	public void addStaff(String name, char gender, int id, String title) {
		// TODO - implement StaffManager.addStaff
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
	 * remove staff
	 * @param id staff's ID
	 */
	public void removeStaff(int id) {
		// TODO - implement StaffManager.removeStaff
		Staff curStaff = this.getStaffByID(id);
		if (curStaff==null){
			System.out.println("This Staff ID does not exist!");
			return;
		}
		staffs.remove(curStaff);
		System.out.println("Staff removed!");
	}

	/**
	 * find staff by id
	 * @param id ID of staff we want to find
	 * @return staff or null
	 */
	public Staff getStaffByID(int id) {
		// TODO - implement StaffManager.getStaffByID
		for (int i =0; i<staffs.size(); i++){
			if (staffs.get(i).getID()==id){
				return staffs.get(i);
			}
		}
		return null;
	}
	/**
	 * get all staff
	 * @return list of staffs
	 */
	public ArrayList<Staff> getAllStaff() {
		// TODO - implement StaffManager.getAllStaff
		return this.staffs;
	}

	/**
	 * print staff by ID
	 * @param id ID of staff whose info we want to print
	 */
	public void printStaffByID(int id) {
		// TODO - implement StaffManager.printStaffByID
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
	 * print all staff
	 */
	public void printAllStaffs() {
		// TODO - implement StaffManager.printAllStaffs
		for (int i = 0; i<staffs.size(); i++){
			this.printStaffByID(staffs.get(i).getID());
		}
	}

	/**
	 * update staff information
	 * @param id ID of staff whose info we want to update
	 */
	public void updateStaffInfo(int id) {
		// TODO - implement StaffManager.updateStaffInfo
		Staff curStaff = getStaffByID(id);
		if (curStaff==null){
			System.out.println("Staff ID does not exist!");
			return;
		}
		System.out.println("Choose staff information to update: ");
		System.out.println("(1) Staff name");
		System.out.println("(2) Staff gender");
		System.out.println("(3) Staff title");
		int choice = UserInput.nextInt("Enter your choice", 1, 3);
		switch(choice){
			case 1:
			String newName = UserInput.getString("Enter staff's new name:");
			curStaff.setName(newName);
			break;
			case 2:
			char gender = UserInput.getGender("Enter staff's new gender:");
			curStaff.setGender(gender);
			break;
			case 3: 
			String newTitle = UserInput.getString("Enter staff's new title");
			curStaff.setJobTitle(newTitle);
			break;
		}
	}

}