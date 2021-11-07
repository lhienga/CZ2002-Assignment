import java.util.ArrayList;
public class RestaurantApp {

	/**
	 * Main Restaurant Application
	 * @param args
	 */
	public static void main(String[] args) {
		
		Restaurant.loadRestaurant();



		Menu menu = new Menu(Restaurant.foodMenu);
		StaffManager staff = new StaffManager(Restaurant.staffs);
		TableManager tables = new TableManager(Restaurant.tables);
		ReportManager reports = new ReportManager(menu, Restaurant.invoices);
		ReservationManager reserve = new ReservationManager(tables, Restaurant.reservations, Restaurant.settledReservations);
		MembershipManager members = new MembershipManager(Restaurant.members);
		OrderManager orders = new OrderManager(members, Restaurant.orders, Restaurant.settledOrders);
		FoodMenuUI foodMenuUI = new FoodMenuUI();
		TableUI tableUI = new TableUI();
		OrderUI orderUI = new OrderUI();
		ReservationUI reservationUI = new ReservationUI();
		StaffUI staffUI = new StaffUI();
		MembershipUI membershipUI = new MembershipUI();
		ReportUI reportUI = new ReportUI();

		ArrayList<Reservation> expiredReservations = reserve.getExpiredReservations();
		for (Reservation exReservation: expiredReservations){
			int contact = exReservation.getContact();
			Order exOrder = orders.getOrderbyContact(contact);
			if (exOrder!=null){
				int tableid = exOrder.getTableId();
				tables.changeTableOccupiedStatus(tableid, false);
				orders.removerOrder(contact);
			}
		}
		reserve.removeExpiredReservations();

		
		int choice = 0;

		do {

			choice = UserInput.nextInt("\n\n[Main menu]\n\n" +
					"Which do you wish to access?\n" +
					"1. Menu Manager\n" +
					"2. Table Manager\n" +
					"3. Order Manager\n" +
					"4. Reservation Manager\n" +
					"5. Report Manager\n" +
					"6. Staff Manager\n" +
					"7. Membership Manager\n" +
					"Enter 0 TO QUIT main menu\n",0, 7);


			switch (choice){
				case 1:
					foodMenuUI.manageMenuOptions(menu);
					break;
				case 2:
					tableUI.manageTableOptions(tables, reserve);
					break;
				case 3:
					orderUI.manageOrderOptions(orders, reserve, staff, menu,reports,tables);
					break;
				case 4:
					reservationUI.manageReservationOptions(reserve,orders,staff,tables);
					break;
				case 5:
					reportUI.managingReportOptions(reports, menu);
					break;
				case 6:
					staffUI.manageStaffOptions(staff);
					break;
				case 7:
					membershipUI.manageMembersOptions(members);
			}
		}while(choice != 0);

		Restaurant.saveRestaurant();
	}






}
