import java.io.Serializable;

/**
 * Represents a staff working for the restaurant.
 */
public class Staff implements Serializable{

	/**
	 * Name of this staff
	 */
	private String name;
	
	/**
	 * Gender of this staff: 'M' for male and 'F' for female
	 */
	private char gender;
	
	/**
	 * ID of this staff
	 */
	private int staffID;
	
	/**
	 * Job title of this staff
	 */
	private JOB jobTitle;
	
	/**
	 * Possible job title for this staff
	 */
	public enum JOB {
		
		/**
		 * Manager job title
		 */
		MANAGER,
		
		/**
		 * Cashier job title
		 */
		CASHIER,
		
		/**
		 * Part&#8208;timer job title
		 */
		PART_TIME,
		
		/**
		 * Full&#8208;timer job title
		 */
		FULL_TIME;}

	/**
	 * Constructor specifying the name, gender, staff ID, and job title for this staff that is to be created.
	 * 
	 * @param name Name for this staff
	 * @param gender Gender for this staff. 
	 * 'M' for male and 'F' for female
	 * @param id Staff ID for this staff
	 * @param title Job title for this staff
	 */
	public Staff(String name, char gender, int id, JOB title) {
		this.name = name;
		this.gender = gender;
		this. staffID = id;
		this.jobTitle = title;
	}
	
	/**
	 * Gets the name of this staff.
	 * 
	 * @return name of this staff
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the gender of this staff.
	 * 
	 * @return gender of this staff. 'M' for male and 'F' for female
	 */
	public char getGender() {
		return this.gender;
	}

	/**
	 * Sets the name for this staff.
	 * 
	 * @param name Name for this staff
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the staff ID of this staff.
	 * 
	 * @return staff ID of this staff
	 */
	public int getID() {
		return this.staffID;
	}

	/**
	 * Gets the job title of this staff.
	 * 
	 * @return job title of this staff
	 */
	public JOB getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * Sets the gender for this staff.
	 * 
	 * @param gender Gender for this staff.
	 * 'M' for male and 'F' for female
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Sets the staff ID for this staff.
	 * 
	 * @param id Staff ID for this staff
	 */
	public void setID(int id) {
		this.staffID = id;
	}

	/**
	 * Sets the job title for this staff.
	 * 
	 * @param title Job title for this staff
	 */
	public void setJobTitle(JOB title) {
		this.jobTitle = title;
	}

}