import java.util.ArrayList;
//import java.lang.Math;


public class TableManager {
	
	private ArrayList<Table> tables;
	private int TOTALTABLES = 20;
	
	public TableManager(ArrayList<Table> tables) {
		this.tables = tables;
	}

	/**
	 * get list of all tables
	 * @return ArrayList of tables
	 */
	public ArrayList<Table> getAllTables() {
		return tables;
	}

	/**
	 * get list of available/unoccupied tables
	 * @return ArrayList of available tables
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
	 * get list of occupied tables
	 * @return ArrayList of occupied tables
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
	 * get list of reserved tables
	 * @return ArrayList of reserved tables
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
	 * find table by id
	 * @param id Id of table we want to find
	 * @return table or null
	 */
	public Table getTableByID(int id) {
		// TODO - implement StaffManager.getStaffByID
		for (int i=0; i<tables.size(); i++){
			if (tables.get(i).getTableId()==id){
				return tables.get(i);
			}
		}
		return null;
	}


	/**
	 * find an available table according to capacity
	 * @param numofpax capacity
	 * @return available table or null if there are no available tables
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
	 * get status of a table by id
	 * @param tableid
	 * @return status of table or null if table ID does not exist
	 */
	/*
	public Table.STATUS getStatusByTableId(int tableid) {
		Table curTable = getTableByID(tableid);
		if (curTable==null){
			System.out.println("Table ID does not exist!");
			return null;
		}
		for (int i =0; i<tables.size(); i++){
			if (tables.get(i).getTableId()==tableid){
				return tables.get(i).getStatus();
			}
		}
		return null;
	}
*/

	/**
	 * 
	 * @param reservation
	 * @return 1 if reserved successfully
	 */
	
	/*
	public int reserveTable(Reservation reservation) {
		int id = reservation.getTableID();
		changeTableStatus(id, Table.STATUS.RESERVED);
		return 1;
	}
*/
	/**
	 * 
	 * @param numOfPax
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
	
	public void changeTableOccupiedStatus(int tableid, boolean occupied) {
		Table curTable = getTableByID(tableid);
		if (curTable==null){
			System.out.println("Table ID does not exist!");
			return;
		}
		boolean prevStatus = curTable.getOccupied();
		curTable.setOccupied(occupied);
		//System.out.println("Occupied Status of "+ tableid +" has been changed from "+prevStatus+" to "+occupied);
	}
	
	public void changeTableReservedStatus(int tableid, boolean reserved) {
		Table curTable = getTableByID(tableid);
		if (curTable==null){
			System.out.println("Table ID does not exist!");
			return;
		}
		boolean prevStatus = curTable.getReserved();
		curTable.setReserved(reserved);
		//System.out.println("Reserved Status of "+ tableid +" has been changed from "+prevStatus+" to "+reserved);
	}
}
