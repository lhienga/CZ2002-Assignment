import java.util.ArrayList;
import java.io.Serializable;

/**
 * Represents a restaurant order.
 */
public class Order implements Serializable{

	/**
	 * Table number making this order
	 */
	private int tableId;
	
	/**
	 * Contact number of customer making this order
	 */
	private int contactNum;
	
	/**
	 * Staff serving this order
	 */
	private Staff staff;
	
	/**
	 * The menu item(s) in this order
	 */
	private ArrayList<MenuItem> menuItem;


	/**
	 * Constructor specifying the table number, customer's contact number, 
	 * ID of staff serving, and menu items in this order to be created.
	 * 
	 * @param tableId Table number making this order
	 * @param contactNum Contact number of customer making this order
	 * @param staff Staff serving this order
	 * @param menuItem Menu items(s) in this order
	 */
	public Order(int tableId, int contactNum, Staff staff, ArrayList<MenuItem> menuItem) {
		this.tableId = tableId;
		this.contactNum = contactNum;
		this.staff = staff;
		this.menuItem = menuItem;
	}


	/**
	 * Adds an item to this order.
	 * 
	 * @param item Menu item to be added to this order
	 */
	public void addToOrder(MenuItem item) {
		this.menuItem.add(item);
	}

	/**
	 * Removes an item from this order.
	 * 
	 * @param item Menu item to be removed from this order
	 */
	public void removeFromOrder(MenuItem item) {
		for (int i = 0; i< getNumItems(); i++) {
			if (item == this.menuItem.get(i)) {
				this.menuItem.remove(i);
				return;
			}
		}
		// If the item to be removed was not in the order
		System.out.println("The item was not in the order!");
		System.out.println();
		return;
		
	}

	/**
	 * Gets the number of items in this order.
	 * 
	 * @return total number of items in this order
	 */
	public int getNumItems() {
		return this.menuItem.size();
	}

	/**
	 * Gets the price of an item in this order.
	 * 
	 * @param item Menu item to be looked up the price
	 * @param menu Menu of the restaurant
	 * @return price of the specified menu item
	 */
	public double getItemPrice(MenuItem item, Menu menu) {
		ArrayList<MenuItem> menuItems = menu.getMenuItems();
		for (int i = 0; i< menu.getMenuSize(0); i++) {
			MenuItem currItem = menuItems.get(i);
			if (item == currItem) {
				return currItem.getPrice();
			}
		}
		return 0;
	}

	/**
	 * Gets the sum of the prices of all items in this order.
	 * 
	 * @param menu Menu of the restaurant
	 * @return total price of the items in this order
	 */
	public double getTotalPrice(Menu menu) {
		double totalPrice = 0;
		ArrayList<MenuItem> orderItems = getOrder();
		for (int i=0; i<getNumItems(); i++) {
			totalPrice += getItemPrice(orderItems.get(i), menu);
		}
		return totalPrice;
	}

	/**
	 * Gets the table number of this order.
	 * 
	 * @return table number of this order
	 */
	public int getTableId() {
		return this.tableId;
	}
	
	/**
	 * Gets the contact number of the customer making this order.
	 * 
	 * @return contact number of the customer making this order
	 */
	public int getContactNum() {
		return this.contactNum;
	}
	
	/**
	 * Gets the staff serving this order.
	 * 
	 * @return staff serving this order
	 */
	public Staff getStaff() {
		return staff;
	}
	
	/**
	 * Gets all menu items in this order.
	 * 
	 * @return all menu items in this order
	 */
	public ArrayList<MenuItem> getOrder() {
		return this.menuItem;
	}
	
	/**
	 * Gets the unique menu items in this order.
	 * 
	 * @return unique menu items in this order
	 */
	public ArrayList<MenuItem> getOrderLineItems() {
		ArrayList<MenuItem> OrderLineItems = new ArrayList<MenuItem>();
		for (MenuItem item: getOrder()) {
			if (!OrderLineItems.contains(item)) {
				OrderLineItems.add(item);
			}
		}
		return OrderLineItems;
	}
	
	/**
	 * Prints the details of items in this order.
	 */
	public void printOrder() {
		
		int k=1;
		for (MenuItem item : menuItem) {
			
			if(item instanceof AlaCarte) { // if it is an AlaCarte
				System.out.printf("%d. %-20s %s\nPrice: $%.2f \nDescription: %s\n\n",k,
						item.getName(), "(AlaCarte - "+item.getType()+")", item.getPrice(), item.getDesc());
			} else if(item instanceof PromotionPackage){ // if it is a Promotion Package
				System.out.printf("%d. %-20s %s\n",k,
						item.getName(), "(Promotion Set)", item.getPrice(), item.getDesc());
				for (Food food : item.getPackage()) { // get all the food in the package
					System.out.printf("%s: %s\n",
							food.getType(), food.getName());

				}
					
				System.out.printf("Price: $%.2f\nDescription:%s\n\n",
						item.getPrice(), item.getDesc());
			}
			k++;

		}
	}

}