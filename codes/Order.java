import java.util.ArrayList; // import the ArrayList class
import java.util.Date;
public class Order {

	private int tableNum;
	private Date timeStamp;
	private int staffID;
	private String staffName;
	private ArrayList<MenuItem> menuItem;
	private boolean membership;

	/**
	 * constructor for Order
	 * @param tableNum
	 * @param timeStamp
	 * @param staffID
	 * @param staffName
	 * @param menuItem
	 * @param membership
	 */
	public Order(int tableNum, Date timeStamp, int staffID, String staffName, ArrayList<MenuItem> menuItem, boolean membership) {
		// TODO - implement Order.Order
		this.tableNum = tableNum;
		this.timeStamp = timeStamp;
		this.staffID = staffID;
		this.staffName = staffName;
		this.menuItem = menuItem;
		this.membership = membership;
	}

	/**
	 * add menu item to the order
	 * @param item MenuItem to be added to the order
	 */
	public void addToOrder(MenuItem item) {
		// TODO - implement Order.addToOrder
		this.menuItem.add(item);
		System.out.println("Item successfully added to order!");
	}

	/**
	 * remove a menu item from the order
	 * @param item MenuItem to be removed from order
	 */
	public void removeFromOrder(MenuItem item) {
		// TODO - implement Order.removeFromOrder
		for (int i = 0; i< getNumItems; i++) {
			if (item == this.menuItem.get(i)) {
				this.menuItem.remove(i);
				return;
			}
		}
		
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
	 * @return the price of an item in the order
	 */
	public double getItemPrice() {
		// TODO - implement Order.getItemPrice
		for (int i = 0; i< getMenuSize; i++) {
			if (item == this.menuItem.get(i)) {
				this.menuItem.remove(i);
				return;
			}
		}
	}

	/**
	 * get total price
	 * @return the total price of the order
	 */
	public double getTotalPrice() {
		// TODO - implement Order.getTotalPrice
		
	}

	/**
	 * get table number
	 * @return the table number making the order
	 */
	public int getTableNum() {
		return this.tableNum;
	}

	/**
	 * get order time
	 * @return time and date the order is made
	 */
	public Date getOrderTime() {
		// TODO - implement Order.getOrderTime
		return this.timeStamp;
	}

	/**
	 * get staff's ID
	 * @return the ID of staff taking the order
	 */
	public int getStaffID() {
		return this.staffID;
	}

	/**
	 * get staff's name
	 * @return the name of staff taking the order
	 */
	public String getStaffName() {
		return this.staffName;
	}

	/**
	 * get all items in the order
	 * @return a list of menu items in the order
	 */
	public ArrayList<MenuItem> getOrder() {
		// TODO - implement Order.getOrder
		return this.menuItem;
	}


}
