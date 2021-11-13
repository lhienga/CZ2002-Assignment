import java.util.Calendar;
import java.io.Serializable;

/**
 * Represents an invoice generated from a particular paid order.
 */
public class Invoice implements Serializable{
	/**
	 * The restaurant order of this invoice
	 */
	private Order paidOrder;
	
	/**
	 * The ID of this invoice
	 */
	private int invoiceID;
	
	/**
	 * The table number of this invoice
	 */
	private int tableID;
	
	/**
	 * Sum of prices of menu items in the restaurant order of this invoice
	 */
	private double subtotal;
	
	/**
	 * Total payable amount after GST, service charge, and any membership discount
	 */
	private double totalPrice;
	
	/**
	 * Amount of 7% GST
	 */
	private double gst;
	
	/**
	 * Amount of 10% service charge
	 */
	private double serviceCharge;
	
	/**
	 * Amount of 10% membership discount, wherever applicable
	 */
	private double membershipDiscount=0;
	
	/**
	 * The date and time this invoice is generated
	 */
	private Calendar dateCreated;

	/**
	 * Constructor specifying the restaurant order, the date and time of payment, 
	 * the membership status of the customer, and the subtotal 
	 * for this invoice that is to be created.
	 * 
	 * @param order Restaurant order
	 * @param paymentDate Date and time of payment
	 * @param isMember Membership status of customer
	 * @param subtotal Sum of prices of menu items in the order
	 */
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
	
	/**
	 * Gets the date and time this invoice was generated.
	 * 
	 * @return date and time this invoice was generated
	 */
	public Calendar getDateCreated() {
		return dateCreated;
	}
	
	/**
	 * Gets the restaurant order from which this invoice was generated.
	 * 
	 * @return restaurant order from which this invoice was generated
	 */
	public Order getPaidOrder() {
		return paidOrder;
	}
	
	/**
	 * Gets the total payable amount in this invoice.
	 * 
	 * @return total payable amount in this invoice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Gets the subtotal in this invoice.
	 * 
	 * @return subtotal in this invoice
	 */
	public double getSubtotal(){
		return subtotal;
	}

	/**
	 * Gets the ID of this invoice.
	 * 
	 * @return ID of this invoice
	 */
	public double getInvoiceID(){
		return invoiceID;
	}
	
	/**
	 * Print this invoice.
	 */
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