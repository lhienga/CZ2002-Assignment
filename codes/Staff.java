import java.io.Serializable;
public class Staff implements Serializable{

	private String name;
	private char gender;
	private int staffID;
	private JOB jobTitle;
	public enum JOB {MANAGER,CASHIER,PART_TIME,FULL_TIME;}

	/**
	 * Staff constructor 
	 * @param name Staff's name
	 * @param gender 'M' for Male or 'F' for Female
	 * @param id Staff's ID
	 * @param title Staff's Title
	 */
	public Staff(String name, char gender, int id, JOB title) {
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
	 * @param name staff's name
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
	public JOB getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * set staff's gender 'M' or 'F'
	 * @param gender staff's gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * set staff ID
	 * @param id staff's id
	 */
	public void setID(int id) {
		// TODO - implement Staff.setID
		this.staffID = id;
	}

	/**
	 * set staff job title
	 * @param title staff's title
	 */
	public void setJobTitle(JOB title) {
		this.jobTitle = title;
	}

}