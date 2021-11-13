import java.util.ArrayList;

/**
 * Represents a promotional package menu item in the menu of the restaurant.
 */
public class PromotionPackage extends MenuItem{

	/**
	 * Food items included in this promotional package
	 */
	private ArrayList<Food> setpackage = new ArrayList<Food>();
	
	/**
	 * Description of this promotional package
	 */
	private String package_desc;
	
	/**
	 * Name of this promotional package
	 */
	private String package_name;
	
	/**
	 * Price of this promotional package
	 */
	private double package_price;


	/**
	 * Constructor specifying the food items included in this package, 
	 * name, description, and price of this promotional package.
	 * 
	 * @param food The list of food items included in this package that is to be created
	 * @param package_name Name for this package that is to be created
	 * @param package_desc Description for this package that is to be created
	 * @param package_price Price for this package that is to be created
	 */
	public PromotionPackage(ArrayList<Food> food, String package_name, String package_desc, double package_price) {
		this.setpackage = food;
		this.package_name = package_name;
		this.package_desc = package_desc;
		this.package_price = package_price;
	}
	
	/**
	 * Gets the name of this package.
	 * 
	 * @return name of this package
	 */
	public String getName() {
		return this.package_name;
	}
	
	/**
	 * Gets the description of this package.
	 * 
	 * @return description of this package
	 */
	public String getDesc() {
		return this.package_desc;
	}

	/**
	 * Gets the price of this package.
	 * 
	 * @return price of this package
	 */
	public double getPrice() {
		return this.package_price;
	}

	/**
	 * Sets the name for this package.
	 * 
	 * @param name Name for this package
	 */
	public void setName(String name) {
		this.package_name = name;
	}

	/**
	 * Sets the description for this package.
	 * 
	 * @param desc Description for this package
	 */
	public void setDesc(String desc) {
		this.package_desc = desc;
	}

	/**
	 * Sets the price for this package.
	 * 
	 * @param price Price for this package
	 */
	public void setPrice(double price) {
		this.package_price = price;
	}

	/**
	 * Gets the list of food items included in this package.
	 * 
	 * @return list food items included in this package
	 */
	public ArrayList<Food> getPackage() {
		return this.setpackage;
	}
	
	/**
	 * Adds a food item into this package.
	 * 
	 * @param food Food item to be added into this package
	 */
	public void addFood(Food food) {
		setpackage.add(food);
		System.out.println(food.getName()+" successfully added to promotion package.");
	}
	
	
	/**
	 * Removes a food item from this package.
	 * 
	 * @param food Food item to be removed from this package
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