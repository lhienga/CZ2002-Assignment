import java.util.Calendar;

public class Invoice {
	private Order paidOrder;
	private int invoiceID;
	private double subtotal;
	private double totalPrice;
	private double gst;
	private double serviceCharge;
	private double membershipDiscount=0;
	private Calendar dateCreated;
	private boolean isMember;
	
	public Invoice(Order order,boolean isMember,double subtotal) {
		this.paidOrder = order;
		this.dateCreated = order.getPaymentTime();
		this.invoiceID = Calendar.getInstance().hashCode();
		this.subtotal = totalPrice;
		this.gst = 0.07*totalPrice;
		this.serviceCharge = 0.1*totalPrice;
		if (isMember) {
			membershipDiscount = 0.1*totalPrice;
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
	
	//printInvoice use ORDERMANAGER ONE!!!
	public void printInvoice(){
		System.out.println("Date & Time: " + this.paidOrder.getPaymentTime());
		System.out.println("Invoice Number: " + this.invoiceID);
		paidOrder.printOrder();
		System.out.println("Subtotal: " + this.paidOrder);
		System.out.println("Membership Discount: " + membershipDiscount);
		System.out.println("Service Charge : " + serviceCharge);
		System.out.println("Total Payable: " + totalPrice);

	}
}
