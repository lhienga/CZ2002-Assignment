import java.util.ArrayList; // import the ArrayList class
import java.util.Calendar;

public class Order {

	private int tableNum;
	private Calendar timeStampOfSettledPayment;
	private int staffID;
	private String staffName;
	private ArrayList<MenuItem> menuItem;

	/**
	 * constructor for Order
	 * @param tableNum
	 * @param timeStamp
	 * @param staffID
	 * @param staffName
	 * @param menuItem
	 */
	public Order(int tableNum, int staffID, String staffName, ArrayList<MenuItem> menuItem) {
		// TODO - implement Order.Order
		this.tableNum = tableNum;
		this.staffID = staffID;
		this.staffName = staffName;
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
		for (int i = 0; i< menu.getMenuSize(1); i++) {
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
	public int getTableNum() {
		return this.tableNum;
	}

	/**
	 * get time of settled payment
	 * @return time and date the order is paid
	 */
	public Calendar getPaymentTime() {
		// TODO - implement Order.getOrderTime
		return this.timeStampOfSettledPayment;
	}

	/**
	 * set  time of settled payment
	 * @param settledPaymenttime
	 * @return
	 */
	public void setPaymentTime(Calendar settledPaymenttime) {
		this.timeStampOfSettledPayment = settledPaymenttime;
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
