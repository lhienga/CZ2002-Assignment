import java.util.ArrayList;


public class RestaurantApp {

	/**
	 * Main Restaurant Application
	 * @param args
	 */
	public static void main(String[] args) {

		Menu menu = new Menu();
		StaffManager staff = new StaffManager();

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
					"Enter 0 TO QUIT main menu\n",0, 6);


			switch (choice){
				case 1:
					manageMenu(menu);
					break;
				case 2:
					//manageTable(tableManager);
					break;
				case 3:
					//manageOrder(orderManger, menu, tableManager, staffManager,reservationManager);
					break;
				case 4:
					//manageReservation(reserve);
					break;
				case 5:
					//managingReport(reportManager, menu);
					break;
				case 6:
					manageStaff(staff);
					break;
			}
		}while(choice != 0);

	
	}
	

	/**
	 * 
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
							"ENTER 0 TO QUIT Menu Manager\n",0,4);
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
	 * 
	 * @param order
	 */
	/*
	public void manageOrder(OrderManager order) {
		// TODO - implement RestaurantApp.manageOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	
	/*
	public void manageTable(TableManager table) {
		// TODO - implement RestaurantApp.manageTable
		throw new UnsupportedOperationException();
	}*/

	/**
	 * 
	 * @param reserve
	 */
/*	
public void manageReservation(ReservationManager reserve) {
		// TODO - implement RestaurantApp.manageReservation
		throw new UnsupportedOperationException();
	}*/

	/**
	 * 
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
					"ENTER 0 TO QUIT Staff Manager\n",0,5);
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
}
