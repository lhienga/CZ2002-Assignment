import java.util.ArrayList;
public class OrderManager {

	private ArrayList<Order> orders;
	private Membership membership;

	/**
	 * get the order made by the table of that ID
	 * @param tableid of the table
	 * @return the order from the table
	 */
	public Order getTableIdOrder(int tableid) {
		// TODO - implement OrderManager.getTableIdOrder
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (tableid == order.getTableNum()) {
				return order;
			}
		}
		return null;
	}

	/**
	 * clear the order made by the table of that iD
	 * @param tableid of the table
	 */
	public void clearTableIdOrder(int tableid) {
		// TODO - implement OrderManager.clearTableIdOrder
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (tableid == order.getTableNum()) {
				orders.remove(order);
				return;
			}
		}
		return;
	}

	/**
	 * add an item to the order made by the table
	 * @param tableid of the table
	 * @param menuitem to be added to the order of the table
	 */
	public void addToOrder(int tableid, MenuItem menuitem) {
		// TODO - implement OrderManager.addToOrder
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (tableid == order.getTableNum()) {
				order.addToOrder(menuitem);
				return;
			}
		}
		// if there is no order under that table ID
		return;
	}

	/**
	 * remove an item from the order made by the table
	 * @param tableid of the table
	 * @param menuitem to be removed from the order of the table
	 */
	public void removeFromOrder(int tableid, MenuItem menuitem) {
		// TODO - implement OrderManager.removeFromOrder
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (tableid == order.getTableNum()) {
				order.removeFromOrder(menuitem);
				return;
			}
		}
		// if there is no order under that table ID
		System.out.println("The table" + tableid + "has not made an order!");
		System.out.println();
		return;
	}

	/**
	 * print the invoice of the table
	 * @param tableid of the table
	 */
	public void printInvoice(int tableid) {
		// TODO - implement OrderManager.printInvoice
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (tableid == order.getTableNum()) {
				ArrayList<MenuItem> orderitems = (ArrayList<MenuItem>)order.getOrder().clone();
				System.out.println(
						"Table number: " + tableid + "\n" +
						"Date and time: " + order.getOrderTime() + "\n" +
						"Staff name, ID: " + order.getStaffName() + ", " + order.getStaffID() + "\n" +
						"Item\tPrice"
						);
				for (int j=0; j<order.getNumItems(); j++) {
					MenuItem orderitem = orderitems.get(j);
					System.out.format("%s\t%.2f%n", orderitem.getName(), orderitem.getPrice());
				}
				System.out.println("Membership: ");
				System.out.println();
				return;
			}
		}
		System.out.println("The table" + tableid + "does not exist!");
		System.out.println();
		return;
	}

}
