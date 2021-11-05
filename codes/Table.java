import java.io.Serializable;
public class Table implements Serializable {

	private int tableid;
	private int capacity;
	private boolean smokingOrNot;
	private boolean occupied;
	private boolean reserved;
	

	/**
	 * Table constructor
	 * @param id Table ID
	 * @param capacity Table Capacity:2/4/6/8/10
	 * @param status Table's status
	 * @param smoking If Table is a smoking table or not
	 */
	public Table(int id, int capacity, boolean smoking) {
		this.tableid = id;
		this.capacity = capacity;
		this.smokingOrNot = smoking;
		occupied = false;
		reserved = false;
	}

	/**
	 * get table ID
	 * @return Table ID
	 */
	public int getTableId() {
		return tableid;
	}

	/**
	 * get table's capacity
	 * @return table's capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}


	/**
	 * set table ID
	 * @param id table ID
	 */
	public void setTableId(int id) {
		// TODO - implement Table.setTableId
		throw new UnsupportedOperationException();
	}

	/**
	 * set table's capacity
	 * @param capacity table capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	/**
	 * get result of table being smoking or not
	 * @return true if smoking table and false if non-smoking table
	 */
	public boolean getSmoking() {
		return smokingOrNot;
	}

	/**
	 * set table as smoking or non-smoking table
	 * @param smoking true if smoking table and false if non-smoking table
	 */
	public void setSmoking(boolean smoking) {
		smokingOrNot = smoking;
	}
	
	public boolean getOccupied() {
		return occupied;
	}


	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public boolean getReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	/**
	 * print out details of table
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
