import java.util.ArrayList; // import the ArrayList class
import java.io.Serializable;
public class Order implements Serializable{

	private int tableId;
	private int contactNum;
	//private Calendar timeStampOfSettledPayment;
	private Staff staff;
	private ArrayList<MenuItem> menuItem;


	/**
	 * constructor for Order
	 * @param tableId
	 * @param contactNum
	 * @param staff
	 * @param menuItem
	 */
	public Order(int tableId, int contactNum, Staff staff, ArrayList<MenuItem> menuItem) {
		// TODO - implement Order.Order
		this.tableId = tableId;
		this.contactNum = contactNum;
		this.staff = staff;
		this.menuItem = menuItem;
	}


	/**
	 * add menu item to the order
	 * @param item MenuItem to be added to the order
	 */
	public void addToOrder(MenuItem item) {
		// TODO - implement Order.addToOrder
		this.menuItem.add(item);
	}

	/**
	 * remove a menu item from the order
	 * @param item MenuItem to be removed from the order
	 */
	public void removeFromOrder(MenuItem item) {
		// TODO - implement Order.removeFromOrder
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
	 * get number of items in the order
	 * @return the number of items in the order
	 */
	public int getNumItems() {
		// TODO - implement Order.getNumItems
		return this.menuItem.size();
	}

	/**
	 * get item price
	 * @param item MenuItem in the order
	 * @param menu Menu of the restaurant
	 * @return the price of an item in the order
	 */
	public double getItemPrice(MenuItem item, Menu menu) {
		// TODO - implement Order.getItemPrice
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
	 * get total price
	 * @param menu Menu of the restaurant
	 * @return the total price of the order
	 */
	public double getTotalPrice(Menu menu) {
		// TODO - implement Order.getTotalPrice
		double totalPrice = 0;
		ArrayList<MenuItem> orderItems = getOrder();
		for (int i=0; i<getNumItems(); i++) {
			totalPrice += getItemPrice(orderItems.get(i), menu);
		}
		return totalPrice;
	}

	/**
	 * get table number
	 * @return the table number making the order
	 */
	public int getTableId() {
		return this.tableId;
	}
	
	/**
	 * get contact number
	 * @return contact number of customer making the order
	 */
	public int getContactNum() {
		return this.contactNum;
	}

	
	/**
	 * get staff who created order
	 * @return Staff
	 */
	public Staff getStaff() {
		return staff;
	}
	/**
	 * get all items in the order
	 * @return a list of menu items in the order
	 */
	public ArrayList<MenuItem> getOrder() {
		// TODO - implement Order.getOrder
		return this.menuItem;
	}
	
	/**
	 * get the different items ordered by customer
	 * @return a list of menu items
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
	 * print order
	 */
	public void printOrder() {
		int j=1;
		int k=1;
		for (MenuItem item : menuItem) {
			j = 1;
			if(item instanceof AlaCarte) {
				System.out.printf("%d. %-20s %s\nPrice: $%.2f \nDescription: %s\n\n",k,
						item.getName(), "(AlaCarte - "+item.getType()+")", item.getPrice(), item.getDesc());
			} else if(item instanceof PromotionPackage){
				System.out.printf("%d. %-20s %s\n",k,
						item.getName(), "(Promotion Set)", item.getPrice(), item.getDesc());
				for (Food food : item.getPackage()) {
					System.out.printf("%d: %s: %s\n", j,
							food.getType(), food.getName());
					j++;
				}
					
				System.out.printf("Price: $%.2f\nDescription:%s\n\n",
						item.getPrice(), item.getDesc());
			}
			k++;

		}
	}

}
