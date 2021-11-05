import java.io.Serializable;
public class Table implements Serializable {

	private int tableid;
	private int capacity;
	private STATUS status;
	private boolean smokingOrNot;
	
	public enum STATUS {
		AVAILABLE,
		OCCUPIED,
		RESERVED
	}

	/**
	 * Table constructor
	 * @param id Table ID
	 * @param capacity Table Capacity:2/4/6/8/10
	 * @param status Table's status
	 * @param smoking If Table is a smoking table or not
	 */
	public Table(int id, int capacity, STATUS status, boolean smoking) {
		this.tableid = id;
		this.capacity = capacity;
		this.status = status;
		this.smokingOrNot = smoking;
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
	 * get table's status
	 * @return
	 */
	public STATUS getStatus() {
		return this.status;
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
	 * set table's status
	 * @param status table status
	 */
	public void setStatus(STATUS status) {
		this.status = status;
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
	
	/**
	 * print out details of table
	 */
	public void printTable(int choice) {
		
		if (choice == 0) {
			System.out.printf("Table[id=%d, status=%s, capacity=%d]\n",
					this.getTableId(),
					this.getStatus(),
					this.getCapacity());
		} else {
			System.out.printf("Table[id=%d, capacity=%d]\n",
					this.getTableId(),
					this.getCapacity());
		}
		
	}

}
