import java.util.ArrayList;
import java.util.Calendar;
public class OrderManager {

	private ArrayList<Order> orders;
	public ArrayList<Order> settledOrders;
	private MembershipManager membershipManager;

	public OrderManager(MembershipManager membershipManager) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		this.membershipManager = membershipManager;
	}
	public OrderManager(MembershipManager membershipManager, ArrayList<Order> orders, ArrayList<Order> settledOrders) {
		this.orders = orders;
		this.membershipManager = membershipManager;
		this.settledOrders = settledOrders;

	}
	/**
	 * create a new Order
	 * @param tableId
	 * @param staffID
	 * @param staffName
	 * @param menuItem
	 */
	public void createOrder(int tableId, int contactNum, Staff staff, ArrayList<MenuItem> menuItem) {
		Order order = new Order(tableId,contactNum,staff,menuItem);
		orders.add(order);
		System.out.println("New Order "+"for customer with contact number "+contactNum+ " has been successfully created");
	}

	/**
	 * get the order made by customer of that contactNum
	 * @param contactNum contact number of customer
	 * @return the order made by customer
	 */
	public Order getOrderByContact(int contactNum) {
		// TODO - implement OrderManager.getTableIdOrder
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (contactNum == order.getContactNum()) {
				return order;
			}
		}
		System.out.println();
		return null;
	}

	/**
	 * clear the entire order made by customer of that contact Number
	 * @param contact Number of customer
	 */
	public void clearOrder(int contactNum) {
		
		Order order = getOrderByContact(contactNum);
		if (order == null) {
			System.out.println("There is no order made by customer with contact number " + contactNum +" !");
			System.out.println();
			return;
		}
		orders.remove(order);
		System.out.println("Order for customer with contact number "+contactNum+ " has been successfully removed");

		
		return;
	}

	/**
	 * add an item to the order made by customer
	 * @param contact number of the customer
	 * @param menuitem to be added to the order
	 */
	public void addToOrder(int contactNum, MenuItem menuitem) {
		// TODO - implement OrderManager.addToOrder
		Order order = getOrderByContact(contactNum);
		if (order == null) {
			System.out.println("There is no order made by customer with contact number " + contactNum +" !");
			System.out.println();
			return;
		}
		order.addToOrder(menuitem);

		System.out.println();

	}

	/**
	 * remove an item from the order made by customer
	 * @param contact number of the customer
	 * @param menuitem to be removed from the order
	 */
	public void removeFromOrder(int contactNum, MenuItem menuitem) {
		Order order = getOrderByContact(contactNum);
		if (order == null) {
			System.out.println("There is no order made by customer with contact number " + contactNum +" !");
			System.out.println();
			return;
		}
		order.removeFromOrder(menuitem);

		System.out.println();

	}

	/**
	 * print the invoice of the table
	 * @param tableid of the table
	 */
	public Invoice printInvoice(int contactNum, Menu menu) {
		
		Order order = getOrderByContact(contactNum);

		
		// print invoice
		//ArrayList<MenuItem> orderitems = order.getOrder();
		double totalPrice = order.getTotalPrice(menu);
		double totalPriceMember = totalPrice;
		boolean isMember = false;
		
		Calendar paymentDate = Calendar.getInstance();
		// print order information
		System.out.println();
		System.out.println(
				"Staff name, ID: " + order.getStaff().getName() + ", " + order.getStaff().getID() + "\n" +
				"Date and time: " + paymentDate.getTime() + "\n" +
				"Table number: " + order.getTableId() + "\n\n" +
				
				"Item Name\t\t\tType\t\t\t\tPrice\n"+
				"-------------------------------------------------------------------------\n"
				);
		// print all order items and each price


		int[] numOfProductSold = new int[menu.getMenuSize(0)];
		
		order.getOrder();
		for (MenuItem m: order.getOrder()){
			numOfProductSold[menu.getMenuItems().indexOf(m)] += 1;
		}

		for (MenuItem item: order.getOrderLineItems()){
			int n = numOfProductSold[menu.getMenuItems().indexOf(item)];
			if(item instanceof AlaCarte) {
				System.out.printf("%d X %-28s %s %10s%.2f\n",n,
						item.getName(), "(AlaCarte - "+item.getType()+")","$", n*item.getPrice());
			}  else if(item instanceof PromotionPackage){
				System.out.printf("%d X %-28s %s %18s%.2f\n",n,
						item.getName(), "(Promotion Set)", "$",n*item.getPrice());
			}
		}
		
		System.out.print("\nSubtotal: \t");
	    System.out.printf("%.2f\n", totalPrice);
	    
	    // print membership status and discount amount
		if (membershipManager.getMembershipByContact(contactNum) != null) {
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
		
		return new Invoice(order,paymentDate,isMember,totalPrice);
			
	}
	

}
