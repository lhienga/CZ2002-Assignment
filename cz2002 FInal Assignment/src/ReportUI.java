import java.util.Calendar;

/**
 * Provides a UI when the 'Report Manager' option 
 * is selected from the 'Main Menu'.
 */
public class ReportUI {
    
	/**
	 * 
	 * Displays the options under the 'Report Manager' option, 
	 * namely to generate a sale revenue report of a day, 
	 * generate sale revenue report of a particular month, 
	 * and print all invoices till date.
	 * 
	 * @param reports Report manager
	 * @param menu Menu of the restaurant
	 */
	public static void managingReportOptions(ReportManager reports, Menu menu) {
		int choice = 0;

		Calendar date = Calendar.getInstance();

		do {
			
			
			choice = UserInput.nextInt("[Report management]\n" +
					"Select a choice:\n" +
					"1. Generate Revenue Report For a Day\n" +
					"2. Generate Revenue Report For a period\n" +
					"3. Print all invoices\n"+
					"Enter 0 to return to main menu\n",0, 3);
			System.out.println();
			
			switch (choice) {
			case 1:
				date = UserInput.getDateForReport("Please choose date to display report for (Enter -1 to exit) ");
				if (date == null) break;
				reports.printReportByDay(date);
				break;
			case 2:
				date = UserInput.getMonthYearForReport("Please choose month and year to display report for (Enter -1 to exit) ");
				if (date == null) break;
				int year = date.get(Calendar.YEAR);
				int month = date.get(Calendar.MONTH)+1;
				reports.printReportByMonth(month,year);
				break;
	
			case 3:
				reports.printAllInvoices();
				break;
	
	
			}
		}while (choice != 0);
	}
}