import java.util.ArrayList;

/**
 * Represents the menu of the restaurant.
 * The menu consists of both a la carte and promotional package items.
 */
public class Menu {
	
	/**
	 * The menu of the restaurant.
	 */
	private ArrayList<MenuItem> menu;

	/**
	 * Constructor specifying the menu of the restaurant.
	 * 
	 * @param menu Menu of the restaurant
	 */
	public Menu(ArrayList<MenuItem> menu){
		this.menu = menu;
	}
	
	/**
	 * Prints the menu of the restaurant.
	 * 
	 * @param choice 0 to print full menu, 1 to print a la carte items only, 2 to print promotional package items only
	 */
	public void printMenu(int choice) {
		System.out.println("Here is your menu:");
		
		int j=0;
		
		for (MenuItem item : this.menu){

			if(item instanceof AlaCarte && (choice == 1 || choice == 0)) { //if it is an AlaCarte
				System.out.printf("%d. %-20s %s\nPrice: $%.2f \nDescription: %s\n\n",
						j+1, item.getName(), "(AlaCarte - "+item.getType()+")", item.getPrice(), item.getDesc());
			}
			else if(item instanceof PromotionPackage && (choice == 2 || choice == 0)){ //if it is a Promotion Package
				System.out.printf("%d. %-20s %s\n",
						j+1, item.getName(), "(Promotion Set)", item.getPrice(), item.getDesc());
				for (Food food : item.getPackage()) // print all items in the package
					System.out.printf("%s: %s\n",
							food.getType(), food.getName());
				System.out.printf("Price: $%.2f\nDescription:%s\n\n",
						item.getPrice(), item.getDesc());
			}

			j++;
		}
		
	}
	
	/**
	 * Prints the details of a menu item in the menu.
	 * 
	 * @param item Menu item to be printed
	 */
	public void printMenuItem(MenuItem item) {
		int j=1;
		if(item instanceof AlaCarte) { // if it is an AlaCarte item
			System.out.printf("%-20s %s\nPrice: $%.2f \nDescription: %s\n\n",
					item.getName(), "(AlaCarte - "+item.getType()+")", item.getPrice(), item.getDesc());
		} else if(item instanceof PromotionPackage){ // if it is a Promotion Package
			System.out.printf("%-20s %s\n",
					item.getName(), "(Promotion Set)", item.getPrice(), item.getDesc());
			for (Food food : item.getPackage()) { // get all the items in the package
				System.out.printf("%d: %s: %s\n",j,
						food.getType(), food.getName());
				j++;
			
			}
				
			System.out.printf("Price: $%.2f\nDescription:%s\n\n",
					item.getPrice(), item.getDesc());
		}

			
	}
	
	/**
	 * Adds a menu item to the menu.
	 * If a la carte, item is added to top of menu.
	 * If promotional package, item is added to bottom of menu.
	 * 
	 * @param item Menu item to be added to the menu
	 */
	public void addToMenu(MenuItem item) {
		if (item instanceof AlaCarte) {
			this.menu.add(0,item);
		} else {
			this.menu.add(item);
		}
	}

	/**
	 * Removes a menu item from the menu.
	 * 
	 * @param item Menu item to be removed from the menu
	 */
	public void removeFromMenu(MenuItem item) {
		for (int j = 0; j< getMenuSize(0);j++) {
			if (item == this.menu.get(j)) {
				this.menu.remove(j);
			}
		}
	}


	/**
	 * Gets the number of menu items in the menu.
	 * 
	 * @param choice 0 to get the number of all items (a la carte and promotional package) in the menu,
	 * 1 to get the number of a la carte items in the menu
	 * @return the number of menu items
	 */
	public int getMenuSize(int choice) {
		int i = 0;
		if (choice == 0) 
			return menu.size();
		for (MenuItem item : this.menu){

			if(item instanceof AlaCarte) {
				i++;
			}
		}
		return i;
	}
	
	/**
	 * Gets the menu of the restaurant.
	 * 
	 * @return the menu of the restaurant
	 */
	public ArrayList<MenuItem> getMenuItems() {
		return menu;
	}
	
	/**
	 * Update the details of an a la carte menu item in the menu.
	 * 
	 * @param Item The a la carte menu item to be updated
	 */
	public void UpdateAlaCarte(MenuItem Item) {
		
		if (!(Item instanceof AlaCarte)) {
			return;
		}
		printMenuItem(Item);
		 int choice;

	      do {
	        System.out.println("\nWhat would you like to update?");
	        System.out.println("(1) Food Name");
	        System.out.println("(2) Food Description");
	        System.out.println("(3) Food Price");
	        System.out.println("(4) Food Course Type");
	        System.out.println("(5) Back"); 
	        choice = UserInput.nextInt("\nEnter the number of your choice: ", 1, 5);
	      
	        switch (choice) {
	          case 1:

        		  String foodName = UserInput.getString("Enter Updated Food Name : ");
        		  String prevName = Item.getName();
				  Item.setName(foodName);
      			  System.out.println("Food name : " + prevName + " has been updated to " + foodName);
	      	
	              break;
	          case 2:
	        		
        		  String foodDesc = UserInput.getString("Enter Updated Food Description : ");
        		  String prevDesc = Item.getDesc();
				  Item.setDesc(foodDesc);
      			  System.out.println("Food Description : " + prevDesc + " has been updated to " + foodDesc);
	              break;
	          case 3: 
	        	 
        		  double foodPrice = UserInput.nextDouble("Enter Updated Food Price : ");
        		  double prevPrice = Item.getPrice();
				  Item.setPrice(foodPrice);
      			  System.out.println("Food Price : " + prevPrice + " has been updated to " + foodPrice);
	              break;
	          case 4:
	        	  try {
	        		  Food.TYPE foodType = UserInput.getType("Enter Updated Food Type : ");
	        		  Food.TYPE prevType = Item.getType();
					  Item.setType(foodType);
	      			  System.out.println("Food Description : " + prevType + " has been updated to " + foodType);
		      		}catch(IndexOutOfBoundsException e){ // check for invalid input
		      			System.out.println("Update menu item in food menu failed! (Invalid Type)");
		      		}
	              break;
	          case 5:
	        }
	      
	      } while (choice != 5);
	    

	}
	
	/**
	 * Update the details of a promotional package menu item in the menu.
	 * 
	 * @param Item The promotional package menu item to be updated
	 */
	public void UpdatePromoPackage(MenuItem Item) {
		
		if (!(Item instanceof PromotionPackage)) { //check if input item is a Promotion Package
			System.out.println("Menu Item is not a Promotion Package\n");
			return;
		}
		
		int choice;
		
		System.out.printf("%-20s %s\n", Item.getName(), "(Promotion Set)", Item.getPrice(), Item.getDesc()); //print the package
		for (Food food : Item.getPackage())
			System.out.printf("%s: %s\n",
					food.getType(), food.getName());
		System.out.printf("Price: $%.2f\nDescription:%s\n\n",
				Item.getPrice(), Item.getDesc());

      //update choices
      do {
        System.out.println("\nWhat would you like to update?");
        System.out.println("(1) Promotion Package Name");
        System.out.println("(2) Promotion Package Description");
        System.out.println("(3) Promotion Package Price");
        System.out.println("(4) Promotion Package Food");
        System.out.println("(5) Back"); 
        choice = UserInput.nextInt("\nEnter the number of your choice: ", 1, 5);
      
        switch (choice) {
          case 1:

    		  String foodName = UserInput.getString("Enter Updated Promotion Package Name : ");
    		  String prevName = Item.getName();
			  Item.setName(foodName);
  			  System.out.println("Package name : " + prevName + " has been updated to " + foodName);
      	
              break;
          case 2:
        		
    		  String foodDesc = UserInput.getString("Enter Updated Promotion Package Description: ");
    		  String prevDesc = Item.getDesc();
			  Item.setDesc(foodDesc);
  			  System.out.println("Package Description : " + prevDesc + " has been updated to " + foodDesc);
              break;
          case 3: 
        	 
    		  double foodPrice = UserInput.nextDouble("Enter Updated Promotion Package Price : ");
    		  double prevPrice = Item.getPrice();
			  Item.setPrice(foodPrice);
  			  System.out.println("Promotion Package Price : " + prevPrice + " has been updated to " + foodPrice);
              break;
          case 4:
        	  int i;
        	  
        	  PromotionPackage pp = (PromotionPackage) Item;
	      		do{
	      			System.out.println();
	      			
					//update individual item choices
	      			System.out.println("\nWould you like to add or delete food from Promotion Package?");
	      			System.out.println("(1) Add food");
	      			System.out.println("(2) Remove food");
	      			System.out.println("(3) Back"); 
	      			i = UserInput.nextInt("\n    Enter the number of your choice: ", 1, 3);
	      			
	      			switch (i){
		      			
	      				case 1:
	      						System.out.println();
	      						printMenu(1);
	      						int j = UserInput.nextInt("What food would you like to add to the Promotion Package?",1,getMenuSize(1));
	      						pp.addFood(getMenuItems().get(j-1).getFood());

	      						break;
	      				case 2:
	      						System.out.println();
	      						System.out.println("");
	      						
	      						printMenuItem(pp);
	      						j = UserInput.nextInt("What food would you like to remove from the Promotion Package?",1,pp.getPackage().size());
	      						pp.removeFood(pp.getPackage().get(j-1));
	      						break;
	      				case 3:
	      			}
	      		} while (i !=  3);
              break;
          case 5:
        }
      
      } while (choice != 5);
    

	}

}