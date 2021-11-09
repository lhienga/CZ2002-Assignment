import java.util.ArrayList;
import java.util.Calendar;
public class OrderManager {

	private ArrayList<Order> orders;
	public ArrayList<Order> settledOrders;
	private MembershipManager membershipManager;

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
	 * get the order for a table
	 * @param tableId 
	 * @return the order made by customer
	 */
	public Order getOrderByTableId(int tableId) {
		// TODO - implement OrderManager.getTableIdOrder
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
	 * get the order for a table
	 * @param contactNum
	 * @return the order made by customer
	 */
	public Order getOrderByContactNum(int contactNum) {
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
	 * clear the entire order made by table
	 * @param tableId tableId of table
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
	 * add an item to the order made by table
	 * @param tableId tableId of table
	 * @param menuitem menuitem added to order
	 */
	public void addToOrder(int tableId, MenuItem menuitem) {
		// TODO - implement OrderManager.addToOrder
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
	 * remove an item to the order made by table
	 * @param tableId tableId of table
	 * @param menuitem menuitem removed from order
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
	 * print the invoice of the table
	 * @param tableId tableId of the table
	 * @param menu
	 * @return Invoice
	 */
	public Invoice printInvoice(int tableId, Menu menu) {
		
		Order order = getOrderByTableId(tableId);

		
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