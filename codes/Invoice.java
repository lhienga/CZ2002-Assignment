import java.util.Calendar;
import java.io.Serializable;
public class Invoice implements Serializable{
	private Order paidOrder;
	private int invoiceID;
	private int tableID;
	private double subtotal;
	private double totalPrice;
	private double gst;
	private double serviceCharge;
	private double membershipDiscount=0;
	private Calendar dateCreated;

	
	public Invoice(Order order, Calendar paymentDate, boolean isMember,double subtotal) {
		this.paidOrder = order;
		this.tableID = order.getTableId();
		this.dateCreated = paymentDate;
		this.invoiceID = Calendar.getInstance().hashCode();
		this.subtotal = subtotal;
		this.gst = 0.07*subtotal;
		this.serviceCharge = 0.1*subtotal;
		if (isMember) {
			membershipDiscount = 0.1*subtotal;
		}
		this.totalPrice = subtotal + gst + serviceCharge +membershipDiscount;
		
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

	public double getSubtotal(){
		return subtotal;
	}

	public double getInvoiceID(){
		return invoiceID;
	}
	
	
	public void printInvoice(){
		System.out.println("Date & Time: " + dateCreated.getTime());
		System.out.println("Invoice Number: " + this.invoiceID);
		System.out.println("Table Number: " + this.tableID);
		System.out.printf("Subtotal: $%.2f\n",subtotal);
		if (membershipDiscount != 0) {
			System.out.printf("Membership Discount: $%.2f\n",membershipDiscount);
		}
		System.out.printf("GST:  $%.2f\n", gst);
		System.out.printf("Service Charge: $%.2f\n", serviceCharge);
		System.out.printf("Total Payable: $%.2f\n",totalPrice);

	}
}