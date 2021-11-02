public class AlaCarte extends MenuItem {

	private Food food;

	/**
	 * AlaCarte Constructor
	 * stores food into Food food
	 * @param food Food Item
	 */
	public AlaCarte(Food food) {
		this.food = food;
	}
	
	/**
	 * getName() method from abstract class MenuItem
	 * return name of food
	 * @return food name
	 */
	public String getName() {
		return food.getName();
	}

	/**
	 * getDesc() method from abstract class MenuItem
	 * return description of food
	 * @return food description
	 */
	public String getDesc() {
		return food.getDesc();
	}
	
	/**
	 * getPrice() method from abstract class MenuItem
	 * return price of food
	 * @return food price
	 */
	public double getPrice() {
		return food.getPrice();
	}

	/**
	 * getType() method from abstract class MenuItem
	 * return type of food
	 * @return food type
	 */
	public Food.TYPE getType() {
		return food.getType();
	}

	/**
	 * setName() method from abstract class MenuItem
	 * set name of food
	 * @param name food name
	 */
	public void setName(String name) {
		food.setName(name);
	}

	/**
	 * setDesc() method from abstract class MenuItem
	 * set description of food
	 * @param desc food description
	 */
	public void setDesc(String desc) {
		food.setDesc(desc);
	}

	/**
	 * setPrice() method from abstract class MenuItem
	 * set price of food
	 * @param price food price
	 */
	public void setPrice(double price) {
		food.setPrice(price);
	}

	/**
	 * setType() method from abstract class MenuItem
	 * set type of food
	 * @param type food type
	 */
	public void setType(Food.TYPE type) {
		food.setType(type);
	}
	
	/**
	 * getFood() overwrites getFood() method from MenuItem class
	 * @return food
	 */
	public Food getFood() {
		return this.food;
	}

}
