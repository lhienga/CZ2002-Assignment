import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReportManager {

	private ArrayList<Invoice> report;
	private Menu menu;
	
	public ReportManager(Menu menu, ArrayList<Invoice> invoices) {
		this.report = invoices;
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
	 * print Report for specified month and year
	 * @param report
	 */
	public void printReportByDay(Calendar specifiedDate) {
		
		double totalRevenue = getTotalRevenueByDay(specifiedDate);
		int[] numOfProductSold = getIndividualSaleItemsByDay(specifiedDate);
		
		
		System.out.println("\nSales Revenue Report for " + specifiedDate.getTime());
		System.out.printf("\n\nTotal Sales Revenue: $%.2f\n\n", totalRevenue);
		
		
		System.out.println("Total Number of Food Products sold: \n");
		for (MenuItem m: menu.getMenuItems()){
			if (m instanceof AlaCarte) {
				System.out.println("Ala Carte :");
				System.out.println(m.getFood().getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
			else {
				System.out.println("Promotion Package :");
				System.out.println(m.getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
		
			System.out.println();
		}

	}
	/**
	 * 
	 * @param date
	 */
	public double getTotalRevenueByDay(Calendar date) {
		// TODO - implement ReportManager.getReportByDay
		Date specifiedDate = date.getTime();  
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		String strDate =  dateFormat.format(specifiedDate);
		double total = 0;
		for (Invoice iv: report) {
			if ((dateFormat.format(iv.getDateCreated().getTime()).equals(strDate)))
				total += iv.getTotalPrice();
		}
		return total;
	}
	
	private int[] getIndividualSaleItemsByDay(Calendar date){
		
		int[] numOfProductSold = new int[menu.getMenuSize(0)];

		Date specifiedDate = date.getTime();  
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		String strDate =  dateFormat.format(specifiedDate);
		for (Invoice iv: report) {
			if ((dateFormat.format(iv.getDateCreated().getTime()).equals(strDate)))
				for (MenuItem m: iv.getPaidOrder().getOrder()){
					numOfProductSold[menu.getMenuItems().indexOf(m)] += 1;
				}
			
		}
		
		return numOfProductSold;
		
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
		System.out.println("-----------------------------------------------------");
		System.out.println("--------Total Number of Food Products sold:----------\n");
		for (MenuItem m: menu.getMenuItems()){
			if (m instanceof AlaCarte) {
		System.out.println("Ala Carte :");
		System.out.println(m.getFood().getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
			else {
		System.out.println("Promotion Package :");
				System.out.println(m.getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
		
			System.out.println();
		}
		System.out.println("-----------------------------------------------------");
		System.out.printf("\n-------------Total Sales Revenue: $%.2f-------------\n\n", totalRevenue);
		System.out.println("-----------------------------------------------------");

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
	
	public void printAllInvoices() {
		for (Invoice iv: report) {
			iv.printInvoice();
			System.out.println();
		}
	}

}
