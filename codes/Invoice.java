import java.util.Calendar;

public class Invoice {
	private Order paidOrder;
	private int invoiceID;
	private double totalPrice;
	private Calendar dateCreated;
	
	public Invoice(Order order, double totalPrice){
		this.paidOrder = order;
		this.invoiceID = Calendar.getInstance().hashCode();
		this.totalPrice = totalPrice;
		this.dateCreated = Calendar.getInstance();
	}
	
	public Calendar getDateCreated() {
		return dateCreated;
	}
	
	public Order getPaidOrder() {
		return paidOrder;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	//printInvoice use ORDERMANAGER ONE!!!
	public void printInvoice(){
		System.out.println("Date & Time: " + this.paidOrder.getOrderTime());
		System.out.println("Invoice Number: " + this.invoiceID);
		System.out.println("Total: " + totalPrice);
	}
}
