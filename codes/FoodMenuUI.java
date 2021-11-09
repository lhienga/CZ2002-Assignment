import java.util.ArrayList;
public class FoodMenuUI {

    	/**
	 * manage menu
	 * @param menu
	 */
	public static void manageMenuOptions(Menu menu) {
		//default variables
				int choice = 0;
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
						addItemToMenu(menu);
						break;
					case 3:
						removeMenuItem(menu);
						break;
					case 4:
						updateMenuItem(menu);
						break;
					}

			} while (choice != 0);

	}
	public static void addItemToMenu(Menu menu){
		Food.TYPE foodtypes = null;
		int subchoice = UserInput.nextInt("Do you want to \n1. Add Ala Carte Item or \n2. Add Promotional Item\n (Enter -1 to exit) ",1,2);
		switch (subchoice) {
			case 1:
				String name = UserInput.getString("\nWhat is the name of your food? (Enter -1 to exit) ");
				if (name.compareTo("-1")==0) break;
				Double price = UserInput.nextDouble("What is the price of "+ name + "? (Enter -1 to exit) $");
				if (price == -1) break;
				String desc = UserInput.getString("What is the description of "+ name + "? (Enter -1 to exit) ");
				if (desc.compareTo("-1")==0) break;
				int type = UserInput.nextInt("What is the type for "+name+"?\n1-APPETIZER, 2-MAIN COURSE, 3-DRINK, 4-DESSERT\n");
				if (type==-1) break;
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
								name = UserInput.getString("What is the name of your promotion package? (Enter -1 to exit) ");
								if (name.compareTo("-1")==0) break;
								price = UserInput.nextDouble("What is the price of the promotion package? (Enter -1 to exit) $");
								if (price == -1) break;
								while (price <= 0) {
									System.out.println("Price cannot be 0 or negative.");
									price = UserInput.nextDouble("What is the price of the promotion package? ");
								}
								desc = UserInput.getString("What is the description of the promotion package? ");
								item = new PromotionPackage(promotion, name, desc,price);
								menu.addToMenu(item);

								System.out.println("\nAdded promotion item");
								menu.printMenuItem(item);
								
							}

							break;
						}
						
	}
	public static void removeMenuItem(Menu menu){
		menu.printMenu(0);
		System.out.println();
		int remove = UserInput.nextInt("\nWhich Menu Item do you want to remove?\nENTER 0 TO QUIT\n",0,menu.getMenuSize(0));
			if(remove != 0) {
				MenuItem removeItem = menu.getMenuItems().get(remove - 1);
				menu.removeFromMenu(removeItem);
				System.out.println("\nRemoved Menu item");
				menu.printMenuItem(removeItem);
		}
	}
	public static void updateMenuItem(Menu menu){
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
	}

}