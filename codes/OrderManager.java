import java.util.ArrayList;
import java.util.Calendar;
public class OrderManager {

	private ArrayList<Order> orders;
	private MembershipManager membershipManager;
	private ReservationManager reservationManager;

	public OrderManager(ReservationManager reservationManager,MembershipManager membershipManager) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		this.reservationManager = reservationManager;
		this.membershipManager = membershipManager;
	}
	
	/**
	 * create a new Order
	 * @param tableId
	 * @param staffID
	 * @param staffName
	 * @param menuItem
	 */
	public void createOrder(int tableId, int staffID, String staffName, ArrayList<MenuItem> menuItem) {
		Order order = new Order(tableId,staffID,staffName,menuItem);
		orders.add(order);
		System.out.println("New Order "+"for table "+tableId+ "successfully created");
	}
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
		//System.out.println("There is no order under the table ID " + tableid +" !");
		System.out.println();
		return null;
	}

	/**
	 * clear the entire order made by the table of that iD
	 * Each table ID has 1 order
	 * @param tableid of the table
	 */
	public void clearTableIdOrder(int tableid) {
		
		Order order = getTableIdOrder(tableid);
		if (order == null) {
			System.out.println("There is no order under the table ID " + tableid +" !");
			System.out.println();
			return;
		}
		orders.remove(order);

		
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
	public Invoice printInvoice(int tableid, Menu menu) {
		
		Order order = getTableIdOrder(tableid);
		if (order == null) {
			System.out.println("The table " + tableid + " has not created an order!\nFail to print invoice!");
			System.out.println();
			return null;
		}
		ArrayList<Reservation> reservations = reservationManager.getAllReservations();
		int contactNo = -1;

		// get the contact number of the customer on that table
		for (int i=0; i<reservations.size(); i++) {
			Reservation reservation = reservations.get(i);
			if (tableid == reservation.getTableID()) {
				contactNo = reservation.getContact();
			}
		}
	
		/*
		// if the table is currently not reserved
		if (contactNo == -1) {
			System.out.println("The table " + tableid + " is not reserved! Fail to print invoice!");
			System.out.println();
			return 0;
		}*/
		
		// print invoice
		ArrayList<MenuItem> orderitems = order.getOrder();
		double totalPrice = order.getTotalPrice(menu);
		double totalPriceMember = totalPrice;
		boolean isMember = false;
		
		order.setPaymentTime(Calendar.getInstance());
		// print order information
		System.out.println(
				"Table number: " + tableid + "\n" +
				"Date and time: " + order.getPaymentTime().getTime() + "\n" +
				"Staff name, ID: " + order.getStaffName() + ", " + order.getStaffID() + "\n" +
				"Item Name\t\t\tType\t\t\t\tPrice"
				);
		// print all order items and each price

		int k=1;
		for (MenuItem item : order.getOrder()) {
			if(item instanceof AlaCarte) {
				System.out.printf("%d. %-28s %s $%.2f\n",k,
						item.getName(), "(AlaCarte - "+item.getType()+")\t\t", item.getPrice());
			} else if(item instanceof PromotionPackage){
				System.out.printf("%d. %-28s %s $%.2f\n",k,
						item.getName(), "(Promotion Set)\t\t\t", item.getPrice());
	
			}
			k++;
		}
		System.out.print("\nSubtotal: \t");
	    System.out.printf("%.2f\n", totalPrice);
	    
	    // print membership status and discount amount
		if (membershipManager.getMembershipByContact(contactNo) != null) {
			System.out.print("Membership: ");
			System.out.println("Yes");
			totalPriceMember *= 0.9;
			System.out.printf("Membership discount: 10%\t%.2f%n", totalPrice*0.1);
		}
		System.out.print("GST 7% \t");
	    System.out.printf("%.2f\n", totalPrice*0.07);
	    System.out.print("Service charge 10% \t");
	    System.out.printf("%.2f\n", totalPrice*0.1);
		System.out.print("TOTAL PAYABLE: \t");
		System.out.printf("%.2f\n", totalPriceMember*1.1*1.07);
		System.out.println();
		
		return new Invoice(order,isMember,totalPrice);
			
	}

}
