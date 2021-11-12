import java.util.ArrayList;
public class OrderUI {


    	/**
	 * 
	 * @param order
	 */
	
	public static void manageOrderOptions(OrderManager orders, ReservationManager reservations, StaffManager staff, Menu menu,ReportManager reports,TableManager tables) {
		
		//release all table with expired reservation
		reservations.removeExpiredReservations();
		int choice;
		//contactNum = UserInput.getContact("Enter contact number of customer: (-1 to cancel)");
		
		do {
			System.out.println();
			// order choices
			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Create an order\n"+
					"2. Remove an order and clear table\n" + //if customer need to leave urgently without making payment and food haven't arrived
					"3. Add items to order\n" +
					"4. Remove items from order\n"+
					"5. View an order\n"+
					"6. Print invoice/receipt for order\n" + //customers make payment
					"ENTER 0 to return to main menu\n",0,6);
			System.out.println();
			switch (choice) {
				case 1:
					createOrder(orders, reservations, staff, menu, reports, tables);
					break;
				case 2:
					cancelOrder(orders,reservations,tables);
					break;
				case 3:
                    addItem(orders,  menu);
					break;
				case 4:
					removeItem(orders, menu);
					break;
				case 5:
                    viewOrder(orders);
					break;
				case 6:
					printInvoice(orders, reports, reservations, tables, menu);

					break;
			
                }		
		}while (choice != 0);
	
	}

    public static void cancelOrder(OrderManager orders,ReservationManager reservations,TableManager tables){
    	int tableId;
        tableId = UserInput.nextInt("Enter ID of table to remove order for: (Enter -1 to exit) ",1,20);
		if (tableId==-1) return;
		int contactNum;
		contactNum = orders.getOrderByTableId(tableId).getContactNum();
		orders.clearOrder(tableId);
		tables.changeTableOccupiedStatus(tableId, false);
		//int contactNum = UserInput.getContact("Enter contact number of customer removing order and leaving restaurant: (Enter -1 to exit) ");
		//if (contactNum==-1) return;
		reservations.removeReservationByContact(contactNum);
    }
    public static void createOrder(OrderManager orders, ReservationManager reservations,StaffManager staff, Menu menu,ReportManager reports,TableManager tables){
        //int i;
        int contactNum;
		int tableId;
		int staffId;
		Staff currentStaff;
		Order order;
		Reservation reservation;
		int subchoice;
        tableId = UserInput.nextInt("Enter ID of table to create order for (Enter -1 to exit): ",1,20);
					if (tableId == -1) return;
					//check if table already has an order means walk-in is occupying table now so no need create an order for it
					
					order = orders.getOrderByTableId(tableId);
					if (order!=null) {
						System.out.println("Order for table "+tableId+" has already been created, would you like to add items instead (Enter choice 3) instead");
						return;
					}
					
					// if table has no order means, reserved customer to table can now occupy table so create a table for customer
					//check if contactNum has already have a reservation under table Id
					contactNum = UserInput.getContact("Currently it is unoccupied, check if customer has made a reservation.\nEnter contact number of customer making order: (Enter -1 to exit) ");
					if (contactNum==-1) return;
					reservation = reservations.getReservationByContact(contactNum);
					if (reservation == null) {
						System.out.println("Reservation not found\n Please create a reservation or walk in under customer with contact number "+contactNum + " first!");
						return;
					}
					if (reservation.getTableID() != tableId) {
						System.out.println("Wrong table id for reserved customer, should create order for table "+reservation.getTableID()+ " instead");
						subchoice = UserInput.nextInt("Enter 1 to continue creating order under table "+reservation.getTableID()+" or Enter 0 to cancel creating order",0,1);
						if (subchoice == 0) {
							System.out.println("Create Order Operation Cancelled");
				            System.out.println();
				            return;
						}
					}
					
					// get correct tableId for creating order
					tableId = reservation.getTableID();
					do {
						staffId = UserInput.nextInt("Enter Staff ID to create the order in (-1 to cancel): ");
						
						currentStaff = staff.getStaffByID(staffId);
						if (currentStaff == null) {
							System.out.println("Invalid staff ID!");
						} else {
							break;
						}
					} while (staffId != -1);
					
					if (staffId == -1) {
						System.out.println("Create Order Operation Cancelled");
			            System.out.println();
			            return;
					} 
					
					//Reservation is moved to settled reservations since reserved customer has arrived
					//table status changed to occupied since reserved customer has arrived
					//reservations.moveReservationToSettledReservations(contactNum);
					tables.changeTableOccupiedStatus(tableId, true);
					

					orders.createOrder(tableId, contactNum, currentStaff, new ArrayList<MenuItem>());
					
					System.out.println("Order for table "+tableId+" has been created successfully, choose (choice = 3) to add items to order!");
					
    }
    public static void addItem(OrderManager orders, Menu menu){
        int tableId;
        int i;
		Order order;


        //contactNum = UserInput.getContact("Enter contact number of customer adding items to order: ");
					tableId = UserInput.nextInt("Enter ID of table to add items for: (Enter -1 to exit) ",1,20);
					if (tableId == -1) return;
					order = orders.getOrderByTableId(tableId);
					if (order == null) {
						System.out.println("There is no order for table " + tableId +" !");
						System.out.println();
						return;
					}
					System.out.println("Currently, there are "+order.getNumItems()+" items in order.\n");
					menu.printMenu(0);
					do {
						i = UserInput.nextInt("Which Menu Item do you want to add?\nENTER 0 to QUIT\n",0,menu.getMenuSize(0));
						if (i==0) {
							break;
						}
						orders.addToOrder(tableId, menu.getMenuItems().get(i-1));  
						System.out.println("Item has been added to order:");
						menu.printMenuItem(menu.getMenuItems().get(i-1));
					} while (i!=0);
					System.out.println("\nItems in order:");
					order.printOrder();
    }
    public static void removeItem(OrderManager orders, Menu menu){
        int tableId;
        int i;
		Order order;
        tableId = UserInput.nextInt("Enter ID of table to remove items for: (Enter -1 to exit) ",1,20);
					if (tableId == -1) return;
					order = orders.getOrderByTableId(tableId);
					if (order == null) {
						System.out.println("There is no order for table " + tableId +" !");
						System.out.println();
						return;
					}
					
					
					do {
						System.out.println("Currently, there are "+order.getNumItems()+" items in order.\n");
						order.printOrder();
						i = UserInput.nextInt("Which Menu Item do you want to remove?\nENTER 0 to QUIT\n",0,order.getNumItems());
						if (i==0) {
							return;
						}
						
						orders.removeFromOrder(tableId, order.getOrder().get(i-1)); 
						System.out.println("Item has been removed from order:");
					} while (i!=0);
					System.out.println("\nRemaining Items in order:");
					order.printOrder();

    }
    public static void viewOrder(OrderManager orders){
        int tableId;
		Order order;
        tableId = UserInput.nextInt("Enter ID of table to view order for: (Enter -1 to exit) ",1,20);
        if (tableId == -1) return;
        order = orders.getOrderByTableId(tableId);
        if (order == null) {
            System.out.println("There is no order for table " + tableId +" !");
            System.out.println();
            return;
        }
        System.out.println("Currently, there are "+order.getNumItems()+" items in order.\n");
        System.out.println("\nTotal Items in order arranged from earliest to latest added:\n");
        order.printOrder();
    }

    public static void printInvoice(OrderManager orders, ReportManager reports, ReservationManager reservations, TableManager tables, Menu menu){
        int tableId;
		Order order;
        tableId = UserInput.nextInt("Enter ID of table to print invoice for: (Enter -1 to exit) ",1,20);
        if (tableId == -1) return;
        order = orders.getOrderByTableId(tableId);
        if (order == null) {
            System.out.println("There is no order for table " + tableId +" !");
            System.out.println();
            return;
        }
        Invoice invoice = orders.printInvoice(tableId, menu);
        //move order to settled orders
        orders.moveOrderToSettledOrders(tableId);
        //change table to unoccupied
        tables.changeTableOccupiedStatus(tableId, false);
        //reservations.removeReservationByContact(contactNum);
        //orders.clearOrder(contactNum);
        reports.addOrderToReport(invoice);
        //ReservationManager reserve = new ReservationManager(tableManager, reservations, settledReservations);
        if (reservations.getReservationByContact(order.getContactNum()) != null) {
        	reservations.moveReservationToSettledReservations(order.getContactNum());
        }
        
        
    }

}