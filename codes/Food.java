public class Food {

	private String name;
	private String description;
	private double price;
	private TYPE type;
	public enum TYPE {APPETIZER, MAINCOURSE, DESSERT, DRINK;}

	/**
	 * Default constructor for Food
	 * @param name food name
	 * @param desc food description
	 * @param price food price
	 * @param type food type
	 */
	public Food(String name, String desc, double price, TYPE type) {
		
		this.name = name;
		this.description = desc;
        this.price = price;
        this.type = type;

	}
	/**
	 * get name of Food
     * @return name food name
	 */
	
	public String getName() {
        return name;
    }
	
	/**
	 * get price of Food
	 * @return food price
	 */
    public double getPrice() {
        return price;
    }

    /**
     * get description of Food
     * @return food description
     */
    public String getDesc() {
        return description;
    }

    /**
     * get type of Food
     * @return food type
     */
    public TYPE getType() {
        return type;
    }

    /**
     * set name of Food
     * @param name food name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set description of Food
     * @param description food description
     */
    public void setDesc(String description) {
        this.description = description;
    }

    /**
     * set price of Food
     * @param price food price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * set type of Food
     * @param type food type
     */
    public void setType(TYPE type) {
        this.type = type;
    }


}
