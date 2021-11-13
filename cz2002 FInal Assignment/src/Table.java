import java.io.Serializable;

/**
 * Represents a table in the restaurant.
 * There are a total of 20 tables, 4 tables for each table capacity of 2,4,6,8, and 10.
 * For each table capacity, there are 2 smoking and 2 non&#8208;smoking tables.
 */
public class Table implements Serializable {

	/**
	 * Table number of this staff
	 */
	private int tableid;
	
	/**
	 * Capacity (in number of people) of this table
	 */
	private int capacity;
	
	/**
	 * Smoking option (true or false) of this table
	 */
	private boolean smokingOrNot;
	
	/**
	 * Occupied status (true or false) of this table
	 */
	private boolean occupied;
	
	/**
	 * Reserved status (true or false) of this table
	 */
	private boolean reserved;
	

	/**
	 * Constructor specifying the table number, capacity and smoking option 
	 * for this table that is to be created.
	 * 
	 * @param id Table number for this table
	 * @param capacity Capacity (2, 4, 6, 8, or 10) for this table
	 * @param smoking Smoking option (true or false) for this table
	 */
	public Table(int id, int capacity, boolean smoking) {
		this.tableid = id;
		this.capacity = capacity;
		this.smokingOrNot = smoking;
		occupied = false;
		reserved = false;
	}

	/**
	 * Gets the table number of this table.
	 * 
	 * @return table number of this table
	 */
	public int getTableId() {
		return tableid;
	}

	/**
	 * Gets the capacity of this table.
	 * 
	 * @return capacity (2, 4, 6, 8, or 10) of this table
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Sets the table number for this table.
	 * 
	 * @param id Table number for this table
	 */
	public void setTableId(int id) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets the capacity for this table.
	 * 
	 * @param capacity Capacity (2, 4, 6, 8, or 10) for this table
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	/**
	 * Gets the smoking option of this table.
	 * 
	 * @return smoking option (true or false) of this table
	 */
	public boolean getSmoking() {
		return smokingOrNot;
	}

	/**
	 * Sets the smoking option for this table.
	 * 
	 * @param smoking Smoking option (true or false) for this table
	 */
	public void setSmoking(boolean smoking) {
		smokingOrNot = smoking;
	}
	
	/**
	 * Gets the occupied status of this table.
	 * 
	 * @return occupied status (true or false) of this table
	 */
	public boolean getOccupied() {
		return occupied;
	}

	/**
	 * Sets the occupied status for this table.
	 * 
	 * @param occupied Occupied status (true or false) for this table
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	/**
	 * Gets the reserved status of this table.
	 * 
	 * @return reserved status (true or false) of this table
	 */
	public boolean getReserved() {
		return reserved;
	}

	/**
	 * Sets the reserved status for this table.
	 * 
	 * @param reserved Reserved status (true or false) for this table
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	/**
	 * Prints out all tables and their details.
	 * 
	 * @param choice 0 to also print reserved and occupied status
	 */
	public void printTable(int choice) {
		String o = "occupied";
		String r = "reserved";
		if (!occupied) {
			o = "not occupied";
		} 
		if (!reserved) {
			r = "not reserved";
		}
		
		if (choice == 0) {
			System.out.printf("Table[id=%d, %s, %s, capacity=%d]\n",
					this.getTableId(),
					o,r,
					this.getCapacity());
		} else {
			System.out.printf("Table[id=%d, capacity=%d]\n",
					this.getTableId(),
					this.getCapacity());
		}
		
	}

}