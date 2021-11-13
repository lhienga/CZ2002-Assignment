import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Manager class that provides the functionalities of: 
 * adding an invoice to the sale revenue report, 
 * printing sale revenue report of a day or a particular month, 
 * getting total revenue in a day or particular month, 
 * getting individual items sales in a day or particular month, 
 * printing all invoices.
 */
public class ReportManager {

	/**
	 * The list of all invoices reflected in this report
	 */
	private ArrayList<Invoice> report;
	
	/**
	 * Menu of the restaurant
	 */
	private Menu menu;
	
	/**
	 * Constructor specifying the menu and the list of all invoices of the restaurant.
	 * 
	 * @param menu Menu of the restaurant
	 * @param invoices List of all invoices reflected in this sale revenue report
	 */
	public ReportManager(Menu menu, ArrayList<Invoice> invoices) {
		this.report = invoices;
		this.menu = menu;
	}
	
	/**
	 * Adds an order invoice to this sale revenue report.
	 * 
	 * @param invoice Invoice to be added to this report
	 */
	public void addOrderToReport(Invoice invoice) {
		report.add(invoice);
	}

	/**
	 * Prints sale revenue report for a specified day.
	 * 
	 * @param specifiedDate Specified day for which the report is to be printed
	 */
	public void printReportByDay(Calendar specifiedDate) {
		
		double totalRevenue = getTotalRevenueByDay(specifiedDate);
		int[] numOfProductSold = getIndividualSaleItemsByDay(specifiedDate);
		System.out.println("\nSales Revenue Report for " + specifiedDate.getTime());

		System.out.println("----------------------------------------------------------\n");
		System.out.println("Number of Individual Food Products sold:\n");
		for (MenuItem m: menu.getMenuItems()){
			if (m instanceof AlaCarte) {
				if (numOfProductSold[menu.getMenuItems().indexOf(m)]==0) {continue;}
			System.out.print("Ala Carte - ");
			System.out.println(m.getFood().getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
			else {
				if (numOfProductSold[menu.getMenuItems().indexOf(m)]==0) {continue;}
				System.out.print("Promotion Package - ");
				System.out.println(m.getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
		
			System.out.println();
		}
		System.out.println("-----------------------------------------------------\n");
		System.out.printf("Total Sales Revenue: $%.2f\n\n", totalRevenue);
		System.out.println("-----------------------------------------------------\n");
	}
	
	/**
	 * Gets the total revenue generated on a specified day.
	 * 
	 * @param date Specified day
	 * @return total revenue for the specified day
	 */
	public double getTotalRevenueByDay(Calendar date) {
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
	
	/**
	 * Gets the sales of individual items on a specified day.
	 * 
	 * @param date Specified day
	 * @return list of sales of individual items on the specified day
	 */
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
	 * Prints sale revenue report for a specified month and year.
	 * 
	 * @param month Specified month
	 * @param year Specified year
	 */
	public void printReportByMonth(int month, int year) {
		String monthName = new DateFormatSymbols().getMonths()[month-1];
		double totalRevenue = getTotalRevenueByMonth(month, year);
		int[] numOfProductSold = getIndividualSaleItemsByMonth(month, year);
		
		
		System.out.println("\nSales Revenue Report for " + monthName + " of Year " + year);
		System.out.println("-----------------------------------------------------\n");
		System.out.println("Number of Individual Food Products sold:\n");
		for (MenuItem m: menu.getMenuItems()){
			if (m instanceof AlaCarte) {
				if (numOfProductSold[menu.getMenuItems().indexOf(m)]==0) {continue;}
			System.out.print("Ala Carte - ");
			System.out.println(m.getFood().getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
			else {
				if (numOfProductSold[menu.getMenuItems().indexOf(m)]==0) {continue;}
				System.out.print("Promotion Package - ");
				System.out.println(m.getName() + ": " + numOfProductSold[menu.getMenuItems().indexOf(m)]);
			}
		
			System.out.println();
		}
		System.out.println("-----------------------------------------------------\n");
		System.out.printf("Total Sales Revenue: $%.2f\n\n", totalRevenue);
		System.out.println("-----------------------------------------------------\n");

	}
	
	/**
	 * Gets the total revenue generated on a specified month and year.
	 * 
	 * @param month Specified month
	 * @param year specified year
	 * @return total revenue for the specified month and year
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
	 * Gets the sales of individual items on a specified month and year.
	 * 
	 * @param month Specified month
	 * @param year Specified year
	 * @return list of sales of individual items on the specified month and year
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
	
	/**
	 * Prints all invoices till date.
	 */
	public void printAllInvoices() {
		for (Invoice iv: report) {
			iv.printInvoice();
			System.out.println();
		}
	}

}