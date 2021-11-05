import java.util.ArrayList;
//import java.lang.Math;


public class TableManager {
	
	private ArrayList<Table> tables;
	private int TOTALTABLES = 20;
	
	/**
	 * Default Constructor to create 20 tables
	 */
	public TableManager() {
		if (this.tables == null) {
			this.tables = new ArrayList<Table>();
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
		}
	}
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
	 * get list of available tables
	 * @return ArrayList of available tables
	 */
	public ArrayList<Table> getAvailableTables() {
		ArrayList<Table> availableTables = new ArrayList<Table>();
		for (Table tb : this.getAllTables()) {
			if (tb.getStatus() == Table.STATUS.AVAILABLE)
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
			if (tb.getStatus() == Table.STATUS.OCCUPIED)
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
			if (tb.getStatus() == Table.STATUS.RESERVED)
				reservedTables.add(tb);
		}
		return reservedTables;
	}
	
	/**
	 * get list of occupied tables
	 * @return ArrayList of unoccupied tables
	 */
	public ArrayList<Table> getUnoccupiedTables() {
		ArrayList<Table> unOccupiedTables = new ArrayList<Table>();
		for (Table tb : this.getAllTables()) {
			if (tb.getStatus() != Table.STATUS.OCCUPIED)
				unOccupiedTables.add(tb);
		}
		return unOccupiedTables;
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
	 * change Table Status
	 * @param tableid Table ID
	 * @param status new Status
	 */
	public void changeTableStatus(int tableid, Table.STATUS status) {
		Table curTable = getTableByID(tableid);
		if (curTable==null){
			System.out.println("Table ID does not exist!");
			return;
		}
		Table.STATUS prevStatus = curTable.getStatus();
		curTable.setStatus(status);
		System.out.println("Table Status of "+ tableid +" has been changed from "+prevStatus+" to "+status);
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


	/**
	 * 
	 * @param reservation
	 * @return 1 if reserved successfully
	 */
	public int reserveTable(Reservation reservation) {
		int id = reservation.getTableID();
		changeTableStatus(id, Table.STATUS.RESERVED);
		return 1;
	}

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
}
