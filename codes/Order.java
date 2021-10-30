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
	 * 
	 * @param tableNum
	 * @param timeStamp
	 * @param staffID
	 * @param staffName
	 * @param menuItem
	 * @param membership
	 */
	public Order(int tableNum, Date timeStamp, int staffID, String staffName, ArrayList<MenuItem> menuItem, boolean membership) {
		// TODO - implement Order.Order
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void addToOrder(MenuItem item) {
		// TODO - implement Order.addToOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void removeFromOrder(MenuItem item) {
		// TODO - implement Order.removeFromOrder
		throw new UnsupportedOperationException();
	}

	public int getNumItems() {
		// TODO - implement Order.getNumItems
		throw new UnsupportedOperationException();
	}

	public double getItemPrice() {
		// TODO - implement Order.getItemPrice
		throw new UnsupportedOperationException();
	}

	public double getTotalPrice() {
		// TODO - implement Order.getTotalPrice
		throw new UnsupportedOperationException();
	}

	public int getTableNum() {
		return this.tableNum;
	}

	public Date getOrderTime() {
		// TODO - implement Order.getOrderTime
		throw new UnsupportedOperationException();
	}

	public int getStaffID() {
		return this.staffID;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public ArrayList<MenuItem> getOrder() {
		// TODO - implement Order.getOrder
		throw new UnsupportedOperationException();
	}

	public boolean getMember() {
		// TODO - implement Order.getMember
		throw new UnsupportedOperationException();
	}

}