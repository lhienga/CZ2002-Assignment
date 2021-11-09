public class TableUI {

    	/**
	 * manage tables
	 * @param table
	 */
	public static void manageTableOptions(TableManager tables, ReservationManager reservations) {
		
		int choice;
		//release all table with expired reservation
		reservations.removeExpiredReservations();
		do {
			System.out.println();

			choice = UserInput.nextInt("Select a choice:\n" +
					"1. Find available table for group\n"+
					"2. List occupied tables\n" +
					"3. List reserved tables\n" +
					"4. List available tables\n"+
					"5. List all tables\n" +
					"ENTER 0 to return to main menu\n",0,5);
			System.out.println();
			switch (choice) {
				case 1:
					checkAvailableTable(tables);
					break;
				case 2:
					int numOfOccupiedTables = 0;
					for (Table tb : tables.getOccupiedTables()) {
						tb.printTable(1);
						numOfOccupiedTables++;						
					}
					System.out.println("There are "+numOfOccupiedTables+" out of 20 tables that are occupied");
					break;
				case 3:
					int numOfReservedTables = 0;
					for (Table tb : tables.getReservedTables()) {
						tb.printTable(1);
						numOfReservedTables++;						
					}
					System.out.println("There are "+numOfReservedTables+" out of 20 tables that are reserved");
					break;
				case 4:
					int numOfAvailableTables = 0;
					for (Table tb : tables.getAvailableTables()) {
						tb.printTable(1);
						numOfAvailableTables++;						
					}
					System.out.println("There are "+numOfAvailableTables+" out of 20 tables that are available");
				
					break;
				case 5:
					
					for (Table tb : tables.getAllTables()) {
						tb.printTable(0);					
					}
					break;
				
			}
			
		}while (choice != 0);
	}

    public static void checkAvailableTable(TableManager tables){
        //this function is for checking table availability, no updating of table status
		int numOfPax;
		int tableId;
		Table table;			
					numOfPax = UserInput.nextInt("Enter number of pax (or 0 to cancel) \n(We only have tables for 1 to 10 people.)\n",0,10);
					if (numOfPax == 0) {
						return;
					}
					table = tables.findAvailableTable(numOfPax);
					if (table == null) {
						System.out.println("No tables available");
					} else {
						tableId = table.getTableId();
						System.out.printf("Table %d is available.%n", tableId);
					}
					
    }

}