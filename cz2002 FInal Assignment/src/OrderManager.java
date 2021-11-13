import java.util.ArrayList;
import java.util.Calendar;

/**
 * Manager class that provides the functionalities of: 
 * creating an order, 
 * retrieving an order, 
 * deleting an order, 
 * adding an item to an order, 
 * remove an item from an order, 
 * moving an order from active to settled order, 
 * and printing an invoice of an order.
 *
 */
public class OrderManager {

	/**
	 * All active restaurant orders
	 */
	private ArrayList<Order> orders;
	
	/**
	 * All settled and paid restaurant orders
	 */
	public ArrayList<Order> settledOrders;
	
	/**
	 * The manager for memberships
	 */
	private MembershipManager membershipManager;

	/**
	 * Constructor specifying the membership manager, list of all orders, 
	 * and list of all settled orders of this order manager that is to be created.
	 * 
	 * @param membershipManager Membership manager for this order manager
	 * @param orders List of all orders for this order manager
	 * @param settledOrders List of all settled orders for this order manager
	 */
	public OrderManager(MembershipManager membershipManager, ArrayList<Order> orders, ArrayList<Order> settledOrders) {
		this.orders = orders;
		this.membershipManager = membershipManager;
		this.settledOrders = settledOrders;
	}
	
	/**
	 * Creates a new order.
	 * 
	 * @param tableId Table number making this order
	 * @param contactNum Contact number of customer making this order
	 * @param staff Staff serving this order
	 * @param menuItem Menu items in this order
	 */
	public void createOrder(int tableId, int contactNum, Staff staff, ArrayList<MenuItem> menuItem) {
		Order order = new Order(tableId,contactNum,staff,menuItem);
		orders.add(order);
		System.out.println("New Order "+"for customer with contact number "+contactNum+ " has been successfully created");
	}

	/**
	 * Retrieve an order by specifying its table number.
	 * 
	 * @param tableId Table number making this order
	 * @return the requested order
	 */
	public Order getOrderByTableId(int tableId) {
		for (int i=0; i<orders.size(); i++) {
			Order order = orders.get(i);
			if (tableId == order.getTableId()) {
				return order;
			}
		}
		System.out.println();
		return null;
	}
	
	/**
	 * Retrieves an order by specifying its customer's contact number.
	 * 
	 * @param contactNum Contact number of the customer making this order
	 * @return the requested order
	 */
	public Order getOrderByContactNum(int contactNum) {
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
	 * Deletes an entire order.
	 * 
	 * @param tableId Table number making this order
	 */
	public void clearOrder(int tableId) {
		Order order = getOrderByTableId(tableId);
		if (order == null) {
			System.out.println("There is no order for table " + tableId +" !");
			System.out.println();
			return;
		}
		orders.remove(order);
		System.out.println("Order for table "+tableId+ " has been successfully removed");
		System.out.println();
		
		return;
	}

	/**
	 * Adds an item to an order.
	 * 
	 * @param tableId Table number making this order
	 * @param menuitem Menu item to be added to this order
	 */
	public void addToOrder(int tableId, MenuItem menuitem) {
		Order order = getOrderByTableId(tableId);
		if (order == null) {
			System.out.println("There is no order for table " + tableId +" !");
			System.out.println();
			return;
		}
		order.addToOrder(menuitem);

		System.out.println();
	}

	/**
	 * Removes an item from an order
	 * 
	 * @param tableId Table number making this order
	 * @param menuitem Menu item to be removed from this order
	 */
	public void removeFromOrder(int tableId, MenuItem menuitem) {
		Order order = getOrderByTableId(tableId);
		if (order == null) {
			System.out.println("There is no order for table " + tableId +" !");
			System.out.println();
			return;
		}
		order.removeFromOrder(menuitem);

		System.out.println();
	}
	
	/**
	 * Moves an order from the list of active orders to the list of settled orders.
	 * 
	 * @param tableId Table number making this order
	 */
	public void moveOrderToSettledOrders(int tableId) {
		Order order = getOrderByTableId(tableId);
		if (order == null) {
			System.out.println("There is no order under table "+tableId);
			return;
		}
		settledOrders.add(order);
		orders.remove(order);
	}

	/**
	 * Prints the invoice of an order
	 * 
	 * @param tableId Table number making this order
	 * @param menu Menu of the restaurant
	 * @return the invoice of this order
	 */
	public Invoice printInvoice(int tableId, Menu menu) {
		
		Order order = getOrderByTableId(tableId);

		
		// print invoice
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
		
		System.out.print("\nSubtotal: \t\t\t$");
	    System.out.printf("%.2f\n", totalPrice);
		System.out.println("-------------------------------------------------------------------------");
	    
	    // print membership status and discount amount
		if (membershipManager.getMembershipByContact(order.getContactNum()) != null) {
			System.out.print("Membership: ");
			System.out.println("Yes");
			totalPriceMember *= 0.9;
			System.out.print("Membership 10% discount: \t$");
			System.out.printf("%.2f\n", totalPrice*0.1);
		}
		System.out.print("GST 7%: \t\t\t$");
	    System.out.printf("%.2f\n", totalPrice*0.07);
	    System.out.print("Service charge 10%: \t\t$");
	    System.out.printf("%.2f\n", totalPrice*0.1);
		System.out.print("TOTAL PAYABLE: \t\t\t$");
		System.out.printf("%.2f\n", totalPriceMember*1.1*1.07);
		System.out.println();
		
		
		return new Invoice(order,paymentDate,isMember,totalPrice);
			
	}
	

}