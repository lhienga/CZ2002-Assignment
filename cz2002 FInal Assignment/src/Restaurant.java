import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Restaurant class to load and save data after every session.
 */
public class Restaurant {
	
	/**
	 * Path for the restaurant data
	 */
	public static final Path 	DATAPATH 					= Paths.get(System.getProperty("user.dir"), "data");
	
	/**
	 * File name for the restaurant data
	 */
	public static final String 	RESTAURANT_FILE_NAME		= "restaurant.dat";	
	
	/**
	 * Restaurant tables
	 */
	public static ArrayList<Table> 				tables;
	
	/**
	 * Restaurant staffs
	 */
	public static ArrayList<Staff> 				staffs;
	
	/**
	 * Restaurant menu items
	 */
	public static ArrayList<MenuItem> 			foodMenu;
	
	/**
	 * Restaurant invoices till date
	 */
	public static ArrayList<Invoice> 			invoices;
	
	/**
	 * Restaurant active orders
	 */
	public static ArrayList<Order> 				orders;
	
	/**
	 * Restaurant active reservations
	 */
	public static ArrayList<Reservation>		reservations;
	
	/**
	 * Restaurant membership record
	 */
    public static ArrayList<Membership>         members;
    
    /**
     * Restaurant settled orders
     */
	public static ArrayList<Order> 				settledOrders;
	
	/**
	 * Restaurant settled reservations
	 */
	public static ArrayList<Reservation>		settledReservations;

	
	/**
	 * Saves restaurant data.
	 */
	public static void saveRestaurant(){
		
		if(!Files.exists(DATAPATH)){ // if the app is run for the first time, initialize a data folder
			System.out.println("Data folder not found!");
			File dir = new File(DATAPATH.toString());
			if(dir.mkdir()){
				System.out.println("Directory " + DATAPATH + " created...");
			}
		}
		
		Object[] restaurantMember 	= {tables,						//members of restaurants
										staffs, 
										foodMenu, 
										invoices, 
										orders, 
										reservations,
                                        members,
										settledOrders,
										settledReservations};
		
		Path 				saveFileName 	= Paths.get(DATAPATH.toString(), RESTAURANT_FILE_NAME); // find the path and save data
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(restaurantMember); // save restaurant members
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Loads restaurant data from previous session.
	 */
	@SuppressWarnings("unchecked")
	public static void loadRestaurant(){ // load the existing data 
		
		Object[] restaurantMember 	= null;
		Path saveData 				= Paths.get(DATAPATH.toString(), RESTAURANT_FILE_NAME); // find the path where data is stored
		FileInputStream fis 		= null;
		ObjectInputStream ois 		= null;
		
		try { // load all the restaurant members
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			restaurantMember = (Object[]) ois.readObject();			
			if(restaurantMember != null){
				tables = (ArrayList<Table>) restaurantMember[0];
				staffs = (ArrayList<Staff>) restaurantMember[1];
				foodMenu = (ArrayList<MenuItem>) restaurantMember[2];
				invoices = (ArrayList<Invoice>) restaurantMember[3];
				orders = (ArrayList<Order>) restaurantMember[4];
				reservations = (ArrayList<Reservation>) restaurantMember[5];
                members = (ArrayList<Membership>) restaurantMember[6];
				settledOrders = (ArrayList<Order>) restaurantMember[7];
				settledReservations = (ArrayList<Reservation>) restaurantMember[8];
			}
			ois.close();
		} catch (IOException ex) { // if no data file is found, use initialized setting
			System.out.println(RESTAURANT_FILE_NAME + " not found. Loading default setting...");
			initRestaurant();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + RESTAURANT_FILE_NAME + " is corrupted. Loading default setting...");
			initRestaurant();
		}
		
	}
	
	/**
	 * Initializes restaurant static members.
	 */
	public static void initRestaurant(){ 
		initTables();
		initStaff();
		initFoodMenu();
		initInvoices();
		initOrders();
		initReservations();
		initMembers();
		initSettledOrders();
		initSettledReservations();
	}
	
	/**
	 * Initializes restaurant tables.
	 */
	public static void initTables(){
		ArrayList<Table> tables = new ArrayList<Table>();
		
			
			int j=1;
			for (int i=2;i<=10;i+=2) {
				for (int k=1;k<=4;k++) {
					if (k%2==0) {
						tables.add(new Table(j,i,true));
					}
					else {
						tables.add(new Table(j,i,false));
					}
					
					j++;
				}
				
			
		}
		Restaurant.tables = tables;
	}
	
	/**
	 * Initializes restaurant staffs.
	 */
	public static void initStaff(){
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		staffs.add(new Staff("John", 'M', 1, Staff.JOB.CASHIER));
		staffs.add(new Staff("May", 'F',2, Staff.JOB.FULL_TIME));
		staffs.add(new Staff("Nitro",'M',3, Staff.JOB.MANAGER));
		staffs.add(new Staff("Miki", 'F',4, Staff.JOB.PART_TIME));
		Restaurant.staffs = staffs;
	}
	
	/**
	 * Initializes restaurant menu.
	 */
	public static void initFoodMenu(){
		
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
			
			ArrayList<Food>food = new ArrayList<Food>();
			
			food.add(new Food("Miniature crab cakes", "Cakes with crab taste",4, Food.TYPE.APPETIZER));
			food.add(new Food("Mac and Cheese", "Rich in cheese", 2.50, Food.TYPE.APPETIZER));
			food.add(new Food("Caprese salad", "Healthy diet", 2.80, Food.TYPE.APPETIZER));
			food.add(new Food("Chicken Soup", "Simple but delicious and nutritious", 2.00, Food.TYPE.APPETIZER));
			
			food.add(new Food("Beef Burger", "Freshest beef", 5, Food.TYPE.MAINCOURSE));
			food.add(new Food("Classic Cheese Burger", "Old but good", 3, Food.TYPE.MAINCOURSE));
			food.add(new Food("Double-up Cheese Burger", "Double the fun", 5.50, Food.TYPE.MAINCOURSE));
			food.add(new Food("Fish Burger", "Crispy fish", 3.50, Food.TYPE.MAINCOURSE));
			food.add(new Food("Grilled Chicken Burger", "Freshly grilled chicken", 3, Food.TYPE.MAINCOURSE));

			food.add(new Food("Coke", "Multiple sugar level available",1.70, Food.TYPE.DRINK));
			food.add(new Food("Lemon Tea", "Classic but not out-dated", 1.50, Food.TYPE.DRINK));
			food.add(new Food("Mountain Dew", "Super cool", 1.80, Food.TYPE.DRINK));
			food.add(new Food("Water", "Plain and simple", 1.00, Food.TYPE.DRINK));

			food.add(new Food("Ice cream cone", "Two scoops of ice cream on a cone", 2.50, Food.TYPE.DESSERT));
			food.add(new Food("Chocolate Brownie", "Rich in chocolate", 4.50, Food.TYPE.DESSERT));
			food.add(new Food("Banana Split", "Banana with ice cream", 5.50, Food.TYPE.DESSERT));
			food.add(new Food("Cheese Cake", "Taste like Cheese", 4.50, Food.TYPE.DESSERT));

			for(int i=0; i<food.size(); i++)
				menu.add(new AlaCarte(food.get(i)));
		
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
			
			ArrayList<Food>set3 = new ArrayList<Food>();
			set3.add(food.get(4));
			set3.add(food.get(5));
			set3.add(food.get(6));
			set3.add(food.get(7));
			set3.add(food.get(8));

			menu.add(new PromotionPackage(set1, "Chicken Burger set","Easy Combo",9.00 ));
			menu.add(new PromotionPackage(set2, "Cheese buddy set", "Cheese lovers",18.0));
			menu.add(new PromotionPackage(set3, "Burger Party set", "Many burgers in one meal",15.0));
            Restaurant.foodMenu = menu;
	}
	
	/**
	 * Initializes restaurant invoices.
	 */
	public static void initInvoices(){
        Restaurant.invoices = new ArrayList<Invoice>();
	}

	/**
	 * Initializes restaurant active orders.
	 */
	public static void initOrders(){
		Restaurant.orders = new ArrayList<Order>();
	}
	
	/**
	 * Initializes restaurant settled orders.
	 */
	public static void initSettledOrders(){
		Restaurant.settledOrders = new ArrayList<Order>();
	}

	/**
	 * Initializes restaurant reservations.
	 */
	public static void initReservations(){
        Restaurant.reservations = new ArrayList<Reservation>();
	}

	/**
	 * Initializes restaurant settled reservations.
	 */
	public static void initSettledReservations(){
        Restaurant.settledReservations = new ArrayList<Reservation>();
	}

	/**
	 * Initializes restaurant memberships.
	 */
    public static void initMembers(){
        ArrayList<Membership> memberships = new ArrayList<Membership>();
			memberships.add(new Membership("Mary",98733789));
			memberships.add(new Membership("John",81642014));
			memberships.add(new Membership("Peter",91346100));
			memberships.add(new Membership("Jane",91841134));
			memberships.add(new Membership("Matthew",84272922));
			memberships.add(new Membership("James",92541289));
			memberships.add(new Membership("Sue",81417013));
			memberships.add(new Membership("Max",91789534));
			memberships.add(new Membership("Mark",90245824));
			memberships.add(new Membership("Phillip",82357203));
            Restaurant.members = memberships;
	}
}