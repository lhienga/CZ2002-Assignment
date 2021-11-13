import java.io.Serializable;

/**
 * Represents a food item that can be included in the menu
 * either as an a la carte entry
 * or added as part of a promotional package entry in the menu.
 */
public class Food implements Serializable{

	/**
	 * Name for this food item
	 */
	private String name;
	
	/**
	 * Description for this food item
	 */
	private String description;
	
	/**
	 * Price for this food item
	 */
	private double price;
	
	/**
	 * Type for this food item
	 */
	private TYPE type;
	
	/**
	 * Type for this food item as either an appetizer, a main course, 
	 * a dessert or a drink
	 */
	public enum TYPE {
		/**
		 * Appetizer type
		 */
		APPETIZER, 
		
		/**
		 * Main course type
		 */
		MAINCOURSE, 
		
		/**
		 * Dessert type
		 */
		DESSERT, 
		
		/**
		 * Drink type
		 */
		DRINK;}

	/**
	 * Constructor specifying the name, description, price and type for this food item to be created.
	 * 
	 * @param name Food name
	 * @param desc Food description
	 * @param price Food price
	 * @param type Food type
	 */
	public Food(String name, String desc, double price, TYPE type) {
		
		this.name = name;
		this.description = desc;
        this.price = price;
        this.type = type;

	}
	
	/**
	 * Gets the name of this food item.
	 * 
	 * @return name of this food item
	 */
	public String getName() {
        return name;
    }
	
	/**
	 * Gets the price of this food item.
	 * 
	 * @return price of this food item
	 */
    public double getPrice() {
        return price;
    }

    /**
	 * Gets the description of this food item.
	 * 
	 * @return description of this food item
	 */
    public String getDesc() {
        return description;
    }

    /**
	 * Gets the type of this food item.
	 * 
	 * @return type of this food item
	 */
    public TYPE getType() {
        return type;
    }

    /**
	 * Sets the name for this food item.
	 * 
	 * @param name Food name
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * Sets the description for this food item.
	 * 
	 * @param description Food description
	 */
    public void setDesc(String description) {
        this.description = description;
    }

    /**
	 * Sets the price for this food item.
	 * 
	 * @param price Food price
	 */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
	 * Sets the type for this food item.
	 * 
	 * @param type Food type
	 */
    public void setType(TYPE type) {
        this.type = type;
    }


}