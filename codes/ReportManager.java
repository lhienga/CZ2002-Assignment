import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class ReportManager {

	private ArrayList<Invoice> report;
	private Menu menu;
	

	/**
	 * constructor for report (paid orders)
	 */
	public ReportManager(Menu menu) {
		if (report == null) {
			report = new ArrayList<Invoice>();
		}
		this.menu = menu;
	}
	
	/**
	 * 
	 * @param report
	 */
	public void addOrderToReport(Invoice invoice) {
		report.add(invoice);
	}

	/**
	 * 
	 * @param date
	 */
	public ArrayList<Order> getReportByDay(Date date) {
		// TODO - implement ReportManager.getReportByDay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 */
	public ArrayList<Order> getTotalReportByWeek(Date date) {
		// TODO - implement ReportManager.getTotalReportByWeek
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 */
	public ArrayList<Order> getTotalReportByMonth(Date date) {
		// TODO - implement ReportManager.getTotalReportByMonth
		throw new UnsupportedOperationException();
	}

	/**
	 * print Report for specified month and year
	 * @param report
	 */
	public void printReportByMonth(int month, int year) {
		String monthName = new DateFormatSymbols().getMonths()[month-1];
		double totalRevenue = getTotalRevenueByMonth(month, year);
		int[] numOfProductSold = getIndividualSaleItemsByMonth(month, year);
		
		
		System.out.println("\nSales Revenue Report for " + monthName + " of Year " + year);
		System.out.printf("Total Sales Revenue: %. 2f", totalRevenue);
		
		System.out.println("Total Number of Food Products sold: ");
		for (MenuItem m: menu.getMenuItems()){
			if (m instanceof AlaCarte)
				System.out.println("Ala Carte :");
			else {
				System.out.println("Promotion Package :");
			}
			System.out.println(m.getFood().getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			System.out.println();
		}

	}
	
	/**
	 * get Total Revenue for specified month and year
	 * @param month Month
	 * @param year Year
	 * @return Total Revenue
	 */
	private double getTotalRevenueByMonth(int month, int year){
		double total = 0;
		for (Invoice iv: report) {
			if ((iv.getDateCreated().get(Calendar.YEAR) == year) && (iv.getDateCreated().get(Calendar.MONTH) == month - 1))
				total += iv.getTotalPrice();
		}
		return total;
	}
	
	/**
	 * get the quantity sold for each menu item for specified month and year
	 * @param month Month
	 * @param year Year
	 * @return Array that contain number of individual menu items sold
	 */
	private int[] getIndividualSaleItemsByMonth(int month, int year){
		
		int[] numOfProductSold = new int[menu.getMenuSize(0)];

		for (Invoice iv: report) {
			if ((iv.getDateCreated().get(Calendar.YEAR) == year) && (iv.getDateCreated().get(Calendar.MONTH) == month - 1))
				for (MenuItem m: iv.getPaidOrder().getOrder()){
					numOfProductSold[menu.getMenuItems().indexOf(m)] += 1;
				}
			
			
		}
		
		return numOfProductSold;
		
	}

}
