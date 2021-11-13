import java.util.ArrayList;
import java.io.Serializable;

/**
 * An abstract class for a menu item.
 */
public abstract class MenuItem implements Serializable{

	/**
	 * Default constructor for abstract class MenuItem.
	 */
	public MenuItem() {}
	
	/**
	 * abstract getName method
	 * 
	 * @return name of the menu item
	 */
	public abstract String getName();
	
	/**
	 * abstract getPrice method
	 * 
	 * @return price of the menu item
	 */
	public abstract double getPrice();
	
	/**
	 * abstract getDesc method
	 * 
	 * @return description of the menu item
	 */
	public abstract String getDesc();
	
	/**
	 * getPackage method
	 * to be overwritten by other classes
	 * 
	 * @return null
	 */
	public ArrayList<Food> getPackage() {
		return null;
	}

	/**
	 * getFood method
	 * to be overwritten by other classes
	 * 
	 * @return null
	 */
	public Food getFood() {
		return null;
	}
	
	/**
	 * getType method
	 * to be overwritten by other classes
	 * 
	 * @return null
	 */
	public Food.TYPE getType() {
		return null;
	}

	/**
	 * abstract setName method
	 * 
	 * @param name Name of the menu item
	 */
	public abstract void setName(String name);

	/**
	 * abstract setPrice method
	 * 
	 * @param price Price of the menu item
	 */
	public abstract void setPrice(double price);
	
	/**
	 * abstract setDesc method
	 * 
	 * @param desc Description of the menu item
	 */
	public abstract void setDesc(String desc);

	/**
	 * setType method
	 * to be overwritten by other classes
	 * 
	 * @param type Type of the menu item
	 */
	public void setType(Food.TYPE type) {

	}

}