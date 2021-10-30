public class Staff {

	private String name;
	private char gender;
	private int staffID;
	private String jobTitle;

	/**
	 * 
	 * @param name
	 * @param gender
	 * @param id
	 * @param title
	 */
	public Staff(String name, char gender, int id, String title) {
		// TODO - implement Staff.Staff
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	public char getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		// TODO - implement Staff.getID
		throw new UnsupportedOperationException();
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @param id
	 */
	public void setID(int id) {
		// TODO - implement Staff.setID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param title
	 */
	public void setJobTitle(String title) {
		this.jobTitle = title;
	}

}