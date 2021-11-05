
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
 * Static restaurant database class keeping track of
 * objects in the restaurant
 * @author soh jun jie
 * @version 1.0
 * @since 2016-11-09
 */
public class Restaurant {
	
	public static final Path 	DATAPATH 					= Paths.get(System.getProperty("user.dir"), "data");
	public static final String 	RESTAURANT_FILE_NAME		= "restaurant.dat";	
	
	public static ArrayList<Table> 				tables;
	public static ArrayList<Staff> 				staffs;
	public static ArrayList<MenuItem> 			foodMenu;	
	public static ArrayList<Invoice> 			invoices;	
	public static ArrayList<Order> 				orders;
	public static ArrayList<Reservation>		reservations;
    public static ArrayList<Membership>         members;
	public static ArrayList<Order> 				settledOrders;
	public static ArrayList<Reservation>		settledReservations;

	
	/**
	 * Save restaurant current state
	 */
	public static void saveRestaurant(){
		
		if(!Files.exists(DATAPATH)){
			System.out.println("Data folder not found!");
			File dir = new File(DATAPATH.toString());
			if(dir.mkdir()){
				System.out.println("Directory " + DATAPATH + " created...");
			}
		}
		
		Object[] restaurantMember 	= {tables,
										staffs, 
										foodMenu, 
										invoices, 
										orders, 
										reservations,
                                        members,
										settledOrders,
										settledReservations};
		
		Path 				saveFileName 	= Paths.get(DATAPATH.toString(), RESTAURANT_FILE_NAME);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(restaurantMember);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Load restaurant previous save state.
	 */
	@SuppressWarnings("unchecked")
	public static void loadRestaurant(){
		
		Object[] restaurantMember 	= null;
		Path saveData 				= Paths.get(DATAPATH.toString(), RESTAURANT_FILE_NAME);
		FileInputStream fis 		= null;
		ObjectInputStream ois 		= null;
		
		try {
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
		} catch (IOException ex) {
			System.out.println(RESTAURANT_FILE_NAME + " not found or does not exists. Default settings will be loaded.");
			initRestaurant();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + RESTAURANT_FILE_NAME + " is corrupted. Default settings will be loaded instead.");
			initRestaurant();
		}
		
	}
	
	/**
	 * Initialise restaurant static members
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
	 * Initialise restaurant tables
	 */
	public static void initTables(){
		ArrayList<Table> tables = new ArrayList<Table>();
		
			
			int j=1;
			for (int i=2;i<=10;i+=2) {
				for (int k=1;k<=4;k++) {
					if (k%2==0) {
						tables.add(new Table(j,i,Table.STATUS.AVAILABLE,true));
					}
					else {
						tables.add(new Table(j,i,Table.STATUS.AVAILABLE,false));
					}
					
					j++;
				}
				
			
		}
		Restaurant.tables = tables;
	}
	
	/**
	 * Initialise restaurant staff
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
	 * Initialise restaurant food menu
	 */
	public static void initFoodMenu(){
		
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
			
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

			menu.add(new PromotionPackage(set1, "Chicken Burger set","Easy Combo",7.0 ));
			menu.add(new PromotionPackage(set2, "Cheese buddy set", "Easy Combo",14.0));
            Restaurant.foodMenu = menu;
	}
	
	/**
	 * Initialise restaurant invoices
	 */
	public static void initInvoices(){
        Restaurant.invoices = new ArrayList<Invoice>();
	}

	/**
	 * Initialise restaurant orders
	 */
	public static void initOrders(){
		Restaurant.orders = new ArrayList<Order>();
	}
	public static void initSettledOrders(){
		Restaurant.settledOrders = new ArrayList<Order>();
	}

	/**
	 * Initialise restaurant reservations
	 */
	public static void initReservations(){
        Restaurant.reservations = new ArrayList<Reservation>();
	}

	public static void initSettledReservations(){
        Restaurant.settledReservations = new ArrayList<Reservation>();
	}

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
