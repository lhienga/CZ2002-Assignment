package cz2002GroupAssignment;

import java.util.ArrayList;
public class OrderManager {

	private ArrayList<Order> orders;
	private MembershipManager membershipManager;
	private ReservationManager reservationManager;

	/**
	 * get the order made by the table of that ID
	 * @param tableid of the table
	 * Each table ID has 1 order
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
		System.out.println("There is no order under the table ID " + tableid +" !");
		System.out.println();
		return null;
	}

	/**
	 * clear the entire order made by the table of that iD
	 * Each table ID has 1 order
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
		System.out.println("There is no order under the table ID " + tableid +" !");
		System.out.println();
		return;
	}

	/**
	 * add an item to the order made by the table
	 * Each table ID has 1 order
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
		System.out.println("The table " + tableid + " has not created any order!\nCreate an order first!");
		System.out.println();
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
		System.out.println("The table" + tableid + "has not created any order!\nCreate an order first!");
		System.out.println();
		return;
	}

	/**
	 * print the invoice of the table
	 * @param tableid of the table
	 */
	public void printInvoice(int tableid, Menu menu) {
		// TODO - implement OrderManager.printInvoice
		ArrayList<Reservation> reservations = (ArrayList<Reservation>)reservationManager.getAllReservations().clone();
		int contactNo = -1;
		Boolean memberBool = false;
		// get the contact number of the customer on that table
		for (int i=0; i<reservations.size(); i++) {
			Reservation reservation = reservations.get(i);
			if (tableid == reservation.getTableID()) {
				contactNo = reservation.getContact();
			}
		}
		// if the table is currently not reserved
		if (contactNo == -1) {
			System.out.println("The table " + tableid + " is not reserved! Fail to print invoice!");
			System.out.println();
			return;
		}
		// print invoice
		for (int j=0; j<orders.size(); j++) {
			Order order = orders.get(j);
			if (tableid == order.getTableNum()) {
				ArrayList<MenuItem> orderitems = (ArrayList<MenuItem>)order.getOrder().clone();
				double totalPrice = order.getTotalPrice(menu);
				double totalPriceMember = totalPrice;
				// print order information
				System.out.println(
						"Table number: " + tableid + "\n" +
						"Date and time: " + order.getOrderTime() + "\n" +
						"Staff name, ID: " + order.getStaffName() + ", " + order.getStaffID() + "\n" +
						"Item\t\t\tPrice"
						);
				// print all order items and each price
				for (int k=0; k<order.getNumItems(); k++) {
					MenuItem orderitem = orderitems.get(k);
					System.out.format("%s\t%.2f%n", orderitem.getName(), orderitem.getPrice());
				}
				// print membership status and discount amount
				System.out.print("Membership: ");
				if (membershipManager.getMembershipByContact(contactNo) != null) {
					System.out.println("Yes");
					memberBool = true;
					totalPriceMember *= 0.9;
					System.out.println("Membership discount 10%\t" + totalPrice*0.1);
				}
				System.out.println("GST 7%\t" + totalPrice*0.07);
				System.out.println("Service charge 10%\t" + totalPrice*0.07);
				System.out.println("TOTAL PAYABLE\t" + totalPriceMember*1.1*1.07);
				System.out.println();
				return;
			}
		}
		System.out.println("The table" + tableid + "has not created an order!\nFail to print invoice!");
		System.out.println();
		return;
	}

}
