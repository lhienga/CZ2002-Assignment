import java.util.ArrayList;

public class Menu {
	
	private ArrayList<MenuItem> menu;

	/**
	 * Default Constructor for Menu
	 */
	public Menu() {
		if (this.menu == null) {
			this.menu = new ArrayList<MenuItem>();
			
			ArrayList<Food>food = new ArrayList<Food>();
			
			food.add(new Food("Miniature crab cakes", "Cakes that taste like crab",2, Food.TYPE.APPETIZER));
			food.add(new Food("Mac and Cheese", "Chessy macaroni", 1.50, Food.TYPE.APPETIZER));
			food.add(new Food("Caprese salad", "Extremely healthy but yummy", 1.80, Food.TYPE.APPETIZER));
			food.add(new Food("Chicken Soup", "Old Fashion and simple soup", 1.00, Food.TYPE.APPETIZER));
			
			food.add(new Food("Beef Burger", "Freshly grilled beef", 4, Food.TYPE.MAINCOURSE));
			food.add(new Food("Classic Cheese Burger", "The usual", 2, Food.TYPE.MAINCOURSE));
			food.add(new Food("Double-up Cheese Burger", "Double the trouble, double the satisfaction", 3.50, Food.TYPE.MAINCOURSE));
			food.add(new Food("Fish Burger", "Deep fried fish with tartar sauce", 2.50, Food.TYPE.MAINCOURSE));
			food.add(new Food("Grilled Chicken Burger", "Freshly grilled chicken", 3, Food.TYPE.MAINCOURSE));

			food.add(new Food("Coke", "Just Coke",1.70, Food.TYPE.DRINK));
			food.add(new Food("Lemon Tea", "A classic favourite tea", 1.50, Food.TYPE.DRINK));
			food.add(new Food("Mountain Dew", "Love it", 1.80, Food.TYPE.DRINK));
			food.add(new Food("Water", "H2O", 1.00, Food.TYPE.DRINK));

			food.add(new Food("Ice cream cone", "Two scoops of ice cream on a cone", 2.50, Food.TYPE.DESSERT));
			food.add(new Food("Chocolate Brownie", "Mouth watering chocolate", 2.50, Food.TYPE.DESSERT));
			food.add(new Food("Banana Split", "Banana with ice cream", 4.50, Food.TYPE.DESSERT));
			food.add(new Food("Cheese Cake", "Just Cheese", 3.50, Food.TYPE.DESSERT));

			for(int i=0; i<food.size(); i++)
				this.menu.add(new AlaCarte(food.get(i)));
		
			ArrayList<Food>set1 = new ArrayList<Food>();
			set1.add(food.get(0));
			set1.add(food.get(8));
			set1.add(food.get(12));
			set1.add(food.get(14));

			ArrayList<Food>set2 = new ArrayList<Food>();
			set2.add(food.get(5));
			set2.add(food.get(6));
			set2.add(food.get(10));
			set2.add(food.get(11));
			set2.add(food.get(14));
			set2.add(food.get(15));

			this.menu.add(new PromotionPackage(set1, "Chicken Burger set","Easy Combo",7.0 ));
			this.menu.add(new PromotionPackage(set2, "Cheese buddy set", "Easy Combo",14.0));

		}
		
	}

	public Menu(ArrayList<MenuItem> menu){
		this.menu = menu;
	}
	/**
	 * Print the Menu
	 * @param choice 0 to print full Menu, 1 to print only AlaCarte items, 2 to print only Promotion Package items
	 */
	public void printMenu(int choice) {
		System.out.println("Here is your menu:");
		
		int j=0;
		
		for (MenuItem item : this.menu){

			if(item instanceof AlaCarte && (choice == 1 || choice == 0)) {
				System.out.printf("%d. %-20s %s\nPrice: $%.2f \nDescription: %s\n\n",
						j+1, item.getName(), "(AlaCarte - "+item.getType()+")", item.getPrice(), item.getDesc());
			}
			else if(item instanceof PromotionPackage && (choice == 2 || choice == 0)){
				System.out.printf("%d. %-20s %s\n",
						j+1, item.getName(), "(Promotion Set)", item.getPrice(), item.getDesc());
				for (Food food : item.getPackage())
					System.out.printf("%s: %s\n",
							food.getType(), food.getName());
				System.out.printf("Price: $%.2f\nDescription:%s\n\n",
						item.getPrice(), item.getDesc());
			}

			j++;
		}
		
	}
	
	/**
	 * Print one menu item
	 * @param item MenuItem
	 */
	public void printMenuItem(MenuItem item) {
		int j=1;
		if(item instanceof AlaCarte) {
			System.out.printf("%-20s %s\nPrice: $%.2f \nDescription: %s\n\n",
					item.getName(), "(AlaCarte - "+item.getType()+")", item.getPrice(), item.getDesc());
		} else if(item instanceof PromotionPackage){
			System.out.printf("%-20s %s\n",
					item.getName(), "(Promotion Set)", item.getPrice(), item.getDesc());
			for (Food food : item.getPackage()) {
				System.out.printf("%d: %s: %s\n", j,
						food.getType(), food.getName());
				j++;
			}
				
			System.out.printf("Price: $%.2f\nDescription:%s\n\n",
					item.getPrice(), item.getDesc());
		}

			
	}
	
	/**
	 * Add Item to menu. If AlaCarte, add it to front of menu, If Promotional Item, add it to back of menu.
	 * @param item MenuItem to be added
	 */
	public void addToMenu(MenuItem item) {
		if (item instanceof AlaCarte) {
			this.menu.add(0,item);
		} else {
			this.menu.add(item);
		}
	}

	/**
	 * Remove Item from menu
	 * @param item MenuItem to be removed from menu
	 */
	public void removeFromMenu(MenuItem item) {
		for (int j = 0; j< getMenuSize(0);j++) {
			if (item == this.menu.get(j)) {
				this.menu.remove(j);
			}
		}
	}


	/**
	 * get number of Items in Menu
	 * @param choice Choice = 0 to get number of items in Menu , Choice = 1 to get number of AlaCarte Items in Menu
	 * @return
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
	 * get method to get menu
	 * @return menuItems ArrayList
	 */
	public ArrayList<MenuItem> getMenuItems() {
		return menu;
	}
	
	/**
	 * Update AlaCarte Food Item in menu
	 * @param Item AlarCarte Food Item
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
		      		}catch(IndexOutOfBoundsException e){
		      			System.out.println("Update menu item in food menu failed! (Invalid Type)");
		      		}
	              break;
	          case 5:
	        }
	      
	      } while (choice != 5);
	    

	}
	
	/**
	 * Update Promotion Package in menu
	 * @param Item Promotion Package Item
	 */
	public void UpdatePromoPackage(MenuItem Item) {
		
		if (!(Item instanceof PromotionPackage)) {
			System.out.println("Menu Item is not a Promotion Package\n");
			return;
		}
		
		int choice;
		
		System.out.printf("%-20s %s\n", Item.getName(), "(Promotion Set)", Item.getPrice(), Item.getDesc());
		for (Food food : Item.getPackage())
			System.out.printf("%s: %s\n",
					food.getType(), food.getName());
		System.out.printf("Price: $%.2f\nDescription:%s\n\n",
				Item.getPrice(), Item.getDesc());

      
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
	      						j = UserInput.nextInt("What food would you like to remove from the Promotion Package?");
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
