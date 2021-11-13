import java.util.ArrayList;

/**
 * Manager class for tables.
 */
public class TableManager {
	
	/**
	 * The list of all tables in the restaurant
	 */
	private ArrayList<Table> tables;
	
	/**
	 * Fixed total number of tables: 20
	 */
	private int TOTALTABLES = 20;
	
	/**
	 * Constructor specifying the list of all tables in the restaurant.
	 * 
	 * @param tables List of all tables in the restaurant
	 */
	public TableManager(ArrayList<Table> tables) {
		this.tables = tables;
	}

	/**
	 * Gets the list of all tables.
	 * 
	 * @return list of all tables
	 */
	public ArrayList<Table> getAllTables() {
		return tables;
	}

	/**
	 * Gets the list of available (in other words, unoccupied) tables.
	 * 
	 * @return list of unoccupied tables
	 */
	public ArrayList<Table> getAvailableTables() {
		ArrayList<Table> availableTables = new ArrayList<Table>();
		for (Table tb : this.getAllTables()) {
			if (!tb.getOccupied())
				availableTables.add(tb);
		}
		return availableTables;
	}

	/**
	 * Gets the list of occupied tables.
	 * 
	 * @return list of occupied tables
	 */
	public ArrayList<Table> getOccupiedTables() {
		ArrayList<Table> occupiedTables = new ArrayList<Table>();
		for (Table tb : this.getAllTables()) {
			if (tb.getOccupied())
				occupiedTables.add(tb);
		}
		return occupiedTables;
	}

	/**
	 * Gets the list of tables with reserved status true.
	 * 
	 * @return list of reserved tables
	 */
	public ArrayList<Table> getReservedTables() {
		ArrayList<Table> reservedTables = new ArrayList<Table>();
		for (Table tb : this.getAllTables()) {
			if (tb.getReserved())
				reservedTables.add(tb);
		}
		return reservedTables;
	}
	

	/**
	 * Gets a table by its table number.
	 * 
	 * @param id Table number of this table
	 * @return the requested table
	 */
	public Table getTableByID(int id) {
		for (int i=0; i<tables.size(); i++){
			if (tables.get(i).getTableId()==id){
				return tables.get(i);
			}
		}
		return null;
	}


	/**
	 * Finds a table for a specified number of people of a group.
	 * 
	 * @param numOfPax Number of people in this group
	 * @return an available table
	 */
	public Table findAvailableTable(int numOfPax) {
		ArrayList<Table> availableTables = getAvailableTables();
		for (Table tb : availableTables) {
			if (tb.getCapacity() == calculateTableCapacity(numOfPax))
				return tb;
		}
		return null;
	}

	/**
	 * Calculates the suitable table capacity for a specified number of people of a group.
	 * 
	 * @param numOfPax Number of people in this group
	 * @return suitable table capacity (2, 4, 6, 8, or 10) for this group
	 */
	public int calculateTableCapacity(int numOfPax) {
		if (numOfPax >10){
			return 0;
		}
		if (numOfPax%2!=0){
			return numOfPax +1;
		}
		return numOfPax;
	}
	
	/**
	 * Changes the occupied status of a table.
	 * 
	 * @param tableid Table number of this table
	 * @param occupied The new occupied status (true or false) for this table.
	 * true for occupied and false for not occupied
	 */
	public void changeTableOccupiedStatus(int tableid, boolean occupied) {
		Table curTable = getTableByID(tableid);
		if (curTable==null){
			System.out.println("Table ID does not exist!");
			return;
		}
		boolean prevStatus = curTable.getOccupied();
		curTable.setOccupied(occupied);
	}
	
	/**
	 * Changes the reserved status of a table.
	 * 
	 * @param tableid Table number of this table
	 * @param reserved The new reserved status (true or false) of this table.
	 * true for reserved and false for not reserved
	 */
	public void changeTableReservedStatus(int tableid, boolean reserved) {
		Table curTable = getTableByID(tableid);
		if (curTable==null){
			System.out.println("Table ID does not exist!");
			return;
		}
		boolean prevStatus = curTable.getReserved();
		curTable.setReserved(reserved);
	}
}