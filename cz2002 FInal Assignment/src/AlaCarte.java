/**
 * Represents an a la carte entry in a menu.
 * An a la carte entry consists of 1 food item.
 */
public class AlaCarte extends MenuItem {

	/**
	 * The food item that becomes this a la carte entry in the menu.
	 */
	private Food food;

	/**
	 * Constructor specifying the food item that becomes this a la carte menu entry.
	 * 
	 * @param food Food item that becomes this a la carte menu entry
	 */
	public AlaCarte(Food food) {
		this.food = food;
	}
	
	/**
	 * Gets the name of the food item that becomes this a la carte menu entry.
	 * 
	 * @return name of the food item
	 */
	public String getName() {
		return food.getName();
	}

	/**
	 * Gets the description of the food item that becomes this a ala carte menu entry.
	 * 
	 * @return description of the food item
	 */
	public String getDesc() {
		return food.getDesc();
	}
	
	/**
	 * Gets the price of the food item that becomes this a la carte menu entry.
	 * 
	 * @return price of the food item
	 */
	public double getPrice() {
		return food.getPrice();
	}

	/**
	 * Gets the type of the food item that becomes this a a la carte menu entry.
	 * The type can be either APPETIZER, MAINCOURSE, DESSERT, or DRINK.
	 * 
	 * @return type the food item
	 */
	public Food.TYPE getType() {
		return food.getType();
	}

	/**
	 * Sets the name for the food item that becomes this a la carte menu entry.
	 * 
	 * @param name Food name
	 */
	public void setName(String name) {
		food.setName(name);
	}

	/**
	 * Sets the description for the food item that becomes this a la carte menu entry.
	 * 
	 * @param desc Food description
	 */
	public void setDesc(String desc) {
		food.setDesc(desc);
	}

	/**
	 * Sets the price for the food item that becomes this a la carte menu entry.
	 * 
	 * @param price Food price
	 */
	public void setPrice(double price) {
		food.setPrice(price);
	}

	/**
	 * Sets the type for the food item that becomes this a la carte menu entry.
	 * The type can be either APPETIZER, MAINCOURSE, DESSERT, or DRINK.
	 * 
	 * @param type Food type
	 */
	public void setType(Food.TYPE type) {
		food.setType(type);
	}
	
	/**
	 * Gets the food item that become this a la carte menu entry.
	 * 
	 * @return the food item
	 */
	public Food getFood() {
		return this.food;
	}

}