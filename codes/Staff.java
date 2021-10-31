public class Staff {

	private String name;
	private char gender;
	private int staffID;
	private String jobTitle;

	/**
	 * Staff constructor 
	 * @param name 
	 * @param gender 'M' or 'F'
	 * @param id
	 * @param title
	 */
	public Staff(String name, char gender, int id, String title) {
		// TODO - implement Staff.Staff
		this.name = name;
		this.gender = gender;
		this. staffID = id;
		this.jobTitle = title;
	}
	/**
	 * get staff name
	 * @return staff name 
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * get staff gender
	 * @return staff gender 'M' or 'F'
	 */
	public char getGender() {
		return this.gender;
	}

	/**
	 * set staff name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get staff ID
	 * @return staff id
	 */
	public int getID() {
		// TODO - implement Staff.getID
		return this.staffID;
	}

	/**
	 * get staff's job title
	 * @return job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * set staff's gender 'M' or 'F'
	 * @param gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * set staff ID
	 * @param id
	 */
	public void setID(int id) {
		// TODO - implement Staff.setID
		this.staffID = id;
	}

	/**
	 * set staff job title
	 * @param title
	 */
	public void setJobTitle(String title) {
		this.jobTitle = title;
	}

}