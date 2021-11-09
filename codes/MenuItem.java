import java.util.ArrayList;

import java.io.Serializable;
public abstract class MenuItem implements Serializable{

	/**
	 * default constructor for abstract class MenuItem
	 */
	public MenuItem() {}
	
	/**
	 * abstract getName method
	 * @return name of MenuItem
	 */
	public abstract String getName();
	
	/**
	 * abstract getPrice method
	 * @return price of MenuItem
	 */
	public abstract double getPrice();
	
	/**
	 * abstract getDesc method
	 * @return description of MenuItem
	 */
	public abstract String getDesc();
	
	/**
	 * getPackage method
	 * to be overwritten by other classes
	 * @return null
	 */
	public ArrayList<Food> getPackage() {
		return null;
	}

	/**
	 * getFood method
	 * to be overwritten by other classes
	 * @return null
	 */
	public Food getFood() {
		return null;
	}
	
	/**
	 * getType method
	 * to be overwritten by other classes
	 * @return null
	 */

	public Food.TYPE getType() {
		return null;
	}

	/**
	 * abstract setName method
	 * @param name name of MenuItem
	 */
	public abstract void setName(String name);

	/**
	 * abstract setPrice method
	 * @param price price of MenuItem
	 */
	public abstract void setPrice(double price);
	
	/**
	 * abstract setDesc method
	 * @param desc description of MenuItem
	 */
	public abstract void setDesc(String desc);

	/**
	 * setType method
	 * to be overwritten by other classes
	 * @param type type of MenuItem
	 */
	public void setType(Food.TYPE type) {

	}

}