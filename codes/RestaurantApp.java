import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RestaurantApp {

	/**
	 * Main Restaurant Application
	 * @param args
	 */
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		StaffManager staff = new StaffManager();
		TableManager tables = new TableManager();
		ReportManager reports = new ReportManager(menu);
		ReservationManager reserve = new ReservationManager(tables);
		MembershipManager members = new MembershipManager();
		OrderManager orders = new OrderManager(reserve,members);
		int choice = 0;

		do {

			choice = UserInput.nextInt("\n\n[Main menu]\n\n" +
					"Which do you wish to access?\n" +
					"1. Menu Manager\n" +
					"2. Table Manager\n" +
					"3. Order Manager\n" +
					"4. Reservation Manager\n" +
					"5. Report Manager\n" +
					"6. Staff Manager\n" +
					"7. Membership Manager\n" +
					"Enter 0 TO QUIT main menu\n",0, 7);


			switch (choice){
				case 1:
					manageMenu(menu);
					break;
				case 2:
					manageTable(tables,reserve);
					break;
				case 3:
					manageOrder(orders, tables, staff, menu,reports);
					break;
				case 4:
					manageReservation(reserve);
					break;
				case 5:
					managingReport(reports, menu);
					break;
				case 6:
					manageStaff(staff);
					break;
				case 7:
					manageMembers(members);
			}
		}while(choice != 0);

	
	}
	

	/**
	 * manage menu
	 * @param menu
	 */
	public static void manageMenu(Menu menu) {
		//default variables
				int choice = 0;
				String name;
				String desc;
				double price;
				int type;
				Food.TYPE foodtypes = null;

				do {
					System.out.println();

					choice = UserInput.nextInt("Select a choice:\n" +
							"1. Print Menu\n" +
							"2. Add Item to Menu\n"+
							"3. Remove Item from Menu\n" +
							"4. Update Item\n" +
							"ENTER 0 to return to main menu\n",0,4);
					System.out.println();
					switch (choice) {
					case 1:
						menu.printMenu(0);
						break;
					case 2:
						int subchoice = UserInput.nextInt("Do you want to \n1. Add Ala Carte Item or \n2. Add Promotional Item\n",0,1);
						switch (subchoice) {
						case 1:
							name = UserInput.getString("\nWhat is the name of your food? ");
							price = UserInput.nextDouble("What is the price of "+ name + "? ");
							desc = UserInput.getString("What is the description of "+ name + "? ");
							type = UserInput.nextInt("What is the type for "+name+"?\n1-APPETIZER, 2-MAIN COURSE, 3-DRINK, 4-DESSERT\n");
							switch (type) {
							case 1:
								foodtypes = Food.TYPE.APPETIZER;
								break;
							
							case 2:
								foodtypes = Food.TYPE.MAINCOURSE;
								break;
							case 3:
								foodtypes = Food.TYPE.DRINK;
								break;
							case 4:
								foodtypes = Food.TYPE.DESSERT;
								break;
							}

							MenuItem item = new AlaCarte(new Food(name, desc, price, foodtypes));
							menu.addToMenu(item);

							System.out.println("\nAdded Ala carte item");
							menu.printMenuItem(item);
							break;
						case 2:
							menu.printMenu(1);
							
							ArrayList<Food> promotion = new ArrayList<Food>();
							int include = 1;
							Food fd;
							
							while (include != 0 && include != -1) {
								include = UserInput.nextInt("Which Menu Item do you want to include?\nKey in 0 TO CONFIRM, -1 TO QUIT\n",-1,menu.getMenuSize(1));
								if (include != 0 && include != -1) {
									fd = menu.getMenuItems().get(include-1).getFood();
									promotion.add(fd);
									System.out.printf("\n%s has been added\n\n", fd.getName());
								}
							}
							if(include == 0){
								name = UserInput.getString("What is the name of your promotion package? ");
								price = UserInput.nextDouble("What is the price of the promotion package? ");
								desc = UserInput.getString("What is the description of the promotion package? ");

								while (price <= 0) {
									System.out.println("Price cannot be 0 or negative.");
									price = UserInput.nextDouble("What is the price of the promotion package? ");
								}
									
								item = new PromotionPackage(promotion, name, desc,price);
								menu.addToMenu(item);

								System.out.println("\nAdded promotion item");
								menu.printMenuItem(item);
								
							}

							break;
						}
						break;
					case 3:
						menu.printMenu(0);
						System.out.println();
						int remove = UserInput.nextInt("\nWhich Menu Item do you want to remove?\nENTER 0 TO QUIT\n",0,menu.getMenuSize(0));
						if(remove != 0) {
							MenuItem removeItem = menu.getMenuItems().get(remove - 1);
							menu.removeFromMenu(removeItem);
							System.out.println("\nRemoved Menu item");
							menu.printMenuItem(removeItem);
						}
						break;
					case 4:
						
						menu.printMenu(0);

						int update = 1;
						System.out.println();
						update = UserInput.nextInt("Which Menu Item do you want to update?\nENTER 0 to QUIT\n",0,menu.getMenuSize(0));

						System.out.println();
						if(update != 0){
							MenuItem item = menu.getMenuItems().get(update-1);
							if (item instanceof AlaCarte) {
								menu.UpdateAlaCarte(item);
							} else if (item instanceof PromotionPackage) {
								menu.UpdatePromoPackage(item);
							}		
						}
						break;
					}

			} while (choice != 0);

	}
	
	/**
	 * manage tables
	 * @param table
	 */
	public static void manageTable(TableManager tables, ReservationManager reserve) {
		
		int choice;
		
		int numOfPax;
		int tableId;
		Table table;

		//release all table with expired reservation
				reserve.releaseTablesWithExpiredReservations();
		do {
			System.out.println();

			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Find available table for group\n"+
					"2. List occupied tables\n" +
					"3. List reserved tables\n" +
					"4. List available tables\n"+
					"5. List all tables\n" +
					"ENTER 0 to return to main menu\n",0,5);
			System.out.println();
			switch (choice) {
				case 1:
					//this function is for checking table availability, no updating of table status
					
					numOfPax = UserInput.nextInt("Enter number of pax (or 0 to cancel) \n(We only have tables for 1 to 10 people.)\n",0,10);
					if (numOfPax == 0) {
						break;
					}
					table = tables.findAvailableTable(numOfPax);
					if (table == null) {
						System.out.println("No tables available");
					} else {
						tableId = table.getTableId();
						System.out.printf("Table %d is available.%n", tableId);
					}
					
					break;
				case 2:
					
					int numOfOccupiedTables = 0;
					for (Table tb : tables.getOccupiedTables()) {
						tb.printTable(1);
						numOfOccupiedTables++;						
					}
					System.out.println("There are "+numOfOccupiedTables+" out of 20 tables that are occupied");
					break;
				case 3:
					int numOfReservedTables = 0;
					for (Table tb : tables.getReservedTables()) {
						tb.printTable(1);
						numOfReservedTables++;						
					}
					System.out.println("There are "+numOfReservedTables+" out of 20 tables that are reserved");
					break;
				case 4:
					int numOfAvailableTables = 0;
					for (Table tb : tables.getAvailableTables()) {
						tb.printTable(1);
						numOfAvailableTables++;						
					}
					System.out.println("There are "+numOfAvailableTables+" out of 20 tables that are available");
				
					break;
				case 5:
					
					for (Table tb : tables.getAllTables()) {
						tb.printTable(0);					
					}
					break;
				
			}
			
		}while (choice != 0);
	}
	/**
	 * 
	 * @param order
	 */
	
	public static void manageOrder(OrderManager orders, TableManager tables, StaffManager staff, Menu menu,ReportManager reports) {
		int i;
		int choice;
		int tableId;
		int staffId = staff.getCurrentStaff().getID();
		String staffName = staff.getCurrentStaff().getName();
		Order order;
		
		do {
			System.out.println();

			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Create an order for table\n"+
					"2. Cancel an order for table\n" +
					"3. Add items to order\n" +
					"4. Remove items from order\n"+
					"5. View order for table\n"+
					"6. Print current invoice for table\n" +
					"ENTER 0 to return to main menu\n",0,6);
			System.out.println();
			switch (choice) {
				case 1:
					//this function is for checking table availability, no updating of table status
					
					tableId = UserInput.nextInt("Enter tableId (or 0 to cancel) \n",0,20);
					if (tableId == 0) {
						break;
					}
					
					if (tables.getStatusByTableId(tableId)!=Table.STATUS.OCCUPIED) {
						System.out.println("Table is not occupied by any customers!");
						break;
					}
					order = orders.getTableIdOrder(tableId);
					if (order!=null) {
						System.out.println("Order has already been created, would you like to add on order instead (Enter choice 3) instead");
						break;
					}
					orders.createOrder(tableId, staffId, staffName,  new ArrayList<MenuItem>());
					order = orders.getTableIdOrder(tableId);
					do {
						System.out.println("Currently, there are "+order.getNumItems()+" items in order.\n");
						menu.printMenu(0);
						i = UserInput.nextInt("Which Menu Item do you want to add?\nENTER 0 to QUIT\n",0,menu.getMenuSize(0));
						if (i==0) {
							break;
						}
						orders.addToOrder(tableId, menu.getMenuItems().get(i-1)); 
						System.out.println("Item has been added to order:");
						menu.printMenuItem(menu.getMenuItems().get(i-1));
					} while (i!=0);
					System.out.println("Items in order:");
					order.printOrder();

					break;
				case 2:
					tableId = UserInput.nextInt("Enter tableId (or 0 to cancel) \n",0,20);
					if (tableId == 0) {
						break;
					}
					orders.clearTableIdOrder(tableId);
					break;
				case 3:

					tableId = UserInput.nextInt("Enter tableId (or 0 to cancel) \n",0,20);
					if (tableId == 0) {
						break;
					}
					
					if (tables.getStatusByTableId(tableId)!=Table.STATUS.OCCUPIED) {
						System.out.println("Table is not occupied by any customers!");
						break;
					}
					order = orders.getTableIdOrder(tableId);
					if (order==null) {
						System.out.println("There is no order under the table ID " + tableId +" !");
						break;
					}
					do {
						System.out.println("Currently, there are "+order.getNumItems()+" items in order.\n");
						menu.printMenu(0);
						i = UserInput.nextInt("Which Menu Item do you want to add?\nENTER 0 to QUIT\n",0,menu.getMenuSize(0));
						if (i==0) {
							break;
						}
						orders.addToOrder(tableId, menu.getMenuItems().get(i-1)); 
						System.out.println("Item has been added to order:");
						menu.printMenuItem(menu.getMenuItems().get(i-1));
					} while (i!=0);
					System.out.println("Items in order:");
					order.printOrder();

					break;
				case 4:
					tableId = UserInput.nextInt("Enter tableId (or 0 to cancel) \n",0,20);
					if (tableId == 0) {
						break;
					}
					
					if (tables.getStatusByTableId(tableId)!=Table.STATUS.OCCUPIED) {
						System.out.println("Table is not occupied by any customers!");
						break;
					}
					order = orders.getTableIdOrder(tableId);
					if (order==null) {
						System.out.println("There is no order under the table ID " + tableId +" !");
						break;
					}
					do {
						System.out.println("Currently, there are "+order.getNumItems()+" items in order.\n");
						order.printOrder();
						i = UserInput.nextInt("Which Menu Item do you want to remove?\nENTER 0 to QUIT\n",0,order.getNumItems());
						if (i==0) {
							break;
						}
						
						orders.removeFromOrder(tableId, order.getOrder().get(i-1)); 
						System.out.println("Item has been removed from order:");
					} while (i!=0);
					System.out.println("Remaining Items in order:");
					order.printOrder();

					break;
				case 5:
					tableId = UserInput.nextInt("Enter tableId (or 0 to cancel) \n",0,20);
					if (tableId == 0) {
						break;
					}
					
					if (tables.getStatusByTableId(tableId)!=Table.STATUS.OCCUPIED) {
						System.out.println("Table is not occupied by any customers!");
						break;
					}
					order = orders.getTableIdOrder(tableId);
					if (order==null) {
						System.out.println("There is no order under the table ID " + tableId +" !");
						break;
					}
			
					System.out.println("Items in order:\n");
					order.printOrder();
					break;
				case 6:
					tableId = UserInput.nextInt("Enter tableId (or 0 to cancel) \n",0,20);
					if (tableId == 0) {
						break;
					}
					if (tables.getStatusByTableId(tableId)!=Table.STATUS.OCCUPIED) {
						System.out.println("Table is not occupied by any customers!");
						break;
					}
					order = orders.getTableIdOrder(tableId);
					Invoice invoice = orders.printInvoice(tableId, menu);
					if (invoice == null) {
						break;
					}
					reports.addOrderToReport(invoice);
					tables.changeTableStatus(tableId, Table.STATUS.AVAILABLE);
					orders.clearTableIdOrder(tableId);
					
					break;
				
			}
			
		}while (choice != 0);
	}
	
	
	/**
	 * manage reservations
	 * @param reserve
	 */

	public static void manageReservation(ReservationManager reserve) {
		// TODO - implement RestaurantApp.manageReservation
		int choice;
		
		//release all table with expired reservation
		reserve.releaseTablesWithExpiredReservations();
		
        do {

        	System.out.println();
            //System.out.println("(1) Show table availability");
            //System.out.println("(3) Accept reservation");
        	choice = UserInput.nextInt("Select a choice:\n" +
					"1. Make reservation\n"+
					"2. Walk-in dining\n" +
					"3. Remove reservation\n" +
					"4. Show reservations\n"+
					"ENTER 0 to return to main menu\n",0,5);

        	System.out.println();
            
            switch (choice) {
                case 1:
					Calendar bookingTime = UserInput.getDateTime("Please enter the time you want to reserve table");
					Reservation reservation = reserve.createReservation(bookingTime,0);
					if (reservation==null){
						System.out.println("Cannot make reservation");
					}
					else {
						System.out.println("Reservation created:");
						reserve.printReservation(reservation.getContact());
					}
                    break;
                case 2:
					Calendar walkInTime = Calendar.getInstance();
					Reservation walkInReservation = reserve.createReservation(walkInTime,1);
					if (walkInReservation==null){
						System.out.println("Cannot make reservation");
					}
					else {
						System.out.println("Walk in Reservation created:");
						reserve.printReservation(walkInReservation.getContact());
					}
						break;
                case 3:
					int removeContact = UserInput.getContact("Enter reservation's contact number to remove: ");
					reserve.removeReservationByContact(removeContact);
                        break;
                case 4:
                	reserve.printAllReservation();
                    break;

            }
        } while (choice != 0);

	}


	/**
	 * manage staff
	 * @param staff
	 */
	public static void manageStaff(StaffManager staff) {
		String name;
		char gender;
		int staffID;
		Staff.JOB jobTitle;
		
		int choice;
		
		do {
			System.out.println();

			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Add Staff\n"+
					"2. Remove Staff\n" +
					"3. Print Staff Details\n" +
					"4. Print All Staff Details\n"+
					"5. Update Staff Details\n" +
					"ENTER 0 to return to main menu\n",0,5);
			System.out.println();
			switch (choice) {
			case 1:
				name = UserInput.getString("Please enter name: ");
				gender = UserInput.getGender("Please enter M / F for gender: ");
				staffID = UserInput.nextInt("Please enter staffID: ");
				jobTitle = UserInput.getJobTitle("Please enter the staff's designation (manager,cashier,part-time,full-time): ");
				
				
				staff.addStaff(name, gender, staffID, jobTitle);
				break;
			case 2:
				staffID = UserInput.nextInt("Please enter staff's ID: ");
				staff.removeStaff(staffID);
				break;
			case 3:
				staffID = UserInput.nextInt("Please enter staff's ID: ");
				staff.printStaffByID(staffID);
				break;
			case 4:
				System.out.println("Details of all staff:\n");
				staff.printAllStaffs();
				break;
			case 5:
				staffID = UserInput.nextInt("Please enter staff's ID: ");
				staff.updateStaffInfo(staffID);
				break;
				
			}
			
		}while (choice != 0);

	}
	
	public static void manageMembers(MembershipManager members) {
		String name;
		int contact;
		Membership member;
		int choice;
		
		do {
			System.out.println();

			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Add Member\n"+
					"2. Remove Member\n" +
					"3. Print Member Details\n" +
					"4. Print All Member Details\n"+
					"5. Update Member Details\n" +
					"ENTER 0 to return to main menu\n",0,5);
			System.out.println();
			switch (choice) {
			case 1:
				name = UserInput.getString("Please enter name: ");
				contact = UserInput.nextInt("Please enter contact number: ");
				
				members.createMembership(name,contact);

				break;
			case 2:
				name = UserInput.getString("Please enter name: ");
				contact = UserInput.nextInt("Please enter contact number: ");
				members.removeMembership(name, contact);
				break;
			case 3:
				name = UserInput.getString("Please enter name: ");
				member = members.getMembershipByName(name);
				if (member == null) 
					break;
				members.printMembership(member);
				
				break;
			case 4:
				System.out.println("Details of all members:\n");
				members.printAllMembers();
				break;
			case 5:
				name = UserInput.getString("Please enter name: ");
				contact = UserInput.nextInt("Please enter contact number: ");
				members.updateMembership(name, contact);
				break;
				
			}
			
		}while (choice != 0);

	}
	
	public static void managingReport(ReportManager reports, Menu menu) {
		int choice = 0;

		Calendar date = Calendar.getInstance();

		do {
			
			
			choice = UserInput.nextInt("[Report management]\n" +
					"Which do you wish to do?\n" +
					"1. Generate Revenue Report For a Day\n" +
					"2. Generate Revenue Report For a period\n" +
					"Enter 0 to return to main menu\n",0, 2);
			System.out.println();
			
			switch (choice) {
			case 1:
				date = UserInput.getDateForReport("Please choose date to display report for");
				reports.printReportByDay(date);
				break;
			case 2:
				date = UserInput.getMonthYearForReport("Please choose month and year to display report for");
				int year = date.get(Calendar.YEAR);
				int month = date.get(Calendar.MONTH)+1;
				reports.printReportByMonth(month,year);
				break;
	
	
			}
		}while (choice != 0);
	}
}
