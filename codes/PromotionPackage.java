
import java.util.ArrayList;
public class PromotionPackage extends MenuItem {

	private ArrayList<Food> setpackage = new ArrayList<Food>();
	private String package_desc;
	private String package_name;
	private double package_price;


	/**
	 * Default constructor of PromotionPackage
	 * with price given
	 * @param food ArrayList of food items in package
	 * @param package_name Package Name
	 * @param package_desc Package Description
	 * @param package_price Package Price
	 */
	public PromotionPackage(ArrayList<Food> food, String package_name, String package_desc, double package_price) {
		this.setpackage = food;
		this.package_name = package_name;
		this.package_desc = package_desc;
		this.package_price = package_price;
		
	}
	
	/**
	 * getName() method from abstract class MenuItem
	 * get name of promotional set package
	 * @return promotion name
	 */
	public String getName() {
		return this.package_name;
	}
	
	/**
	 * getDesc() method from abstract class MenuItem
	 * get description of promotional set package
	 * @return promotion description
	 */
	public String getDesc() {
		return this.package_desc;
	}

	/**
	 * getPrice() method from abstract class MenuItem
	 * get price of promotional set package
	 * @return price of promotional set package
	 */
	public double getPrice() {
		return this.package_price;
	}

	/**
	 * setName() method from abstract class MenuItem
	 * set name of promotional set package
	 * @param name Promotional set package name
	 */
	public void setName(String name) {
		this.package_name = name;
	}

	/**
	 * setDesc() method from abstract class MenuItem
	 * set description of promotional set package
	 * @param desc Promotional set package description
	 */
	public void setDesc(String desc) {
		this.package_desc = desc;
	}

	/**
	 * setPrice() method from abstract class MenuItem
	 * set price of promotional set package
	 * @param price Promotional set package price
	 */
	public void setPrice(double price) {
		this.package_price = price;
	}

	/**
	 * getPackage() overwrites method from MenuItem
	 * @return ArrayList of food items in package
	 */
	public ArrayList<Food> getPackage() {
		return this.setpackage;
	}
	
	/**
	 * Allows user to add a food item to the promotion package
	 * @param food Food Item to be added to promotion package
	 */
	public void addFood(Food food) {
		setpackage.add(food);
		System.out.println(food.getName()+" successfully added to promotion package.");
	}
	
	
	/**
	 * Allows user to remove a food item from the promotion package
	 * @param food Food Item to be removed from promotion package
	 */
	public void removeFood(Food food) {
		for (int j = 0; j< setpackage.size();j++) {
			if (food == getPackage().get(j)) {
				setpackage.remove(j);
				System.out.println(food.getName()+" successfully removed from promotion package.");
			}
		}
		
	}

}
