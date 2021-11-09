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
		MembershipManager members = new MembershipManager(Restaurant.members);
		OrderManager orders = new OrderManager(members, Restaurant.orders, Restaurant.settledOrders);
		ReservationManager reserve = new ReservationManager(tables, orders, Restaurant.reservations, Restaurant.settledReservations);
		
		/*
		FoodMenuUI foodMenuUI = new FoodMenuUI();
		TableUI tableUI = new TableUI();
		OrderUI orderUI = new OrderUI();
		ReservationUI reservationUI = new ReservationUI();
		StaffUI staffUI = new StaffUI();
		MembershipUI membershipUI = new MembershipUI();
		ReportUI reportUI = new ReportUI();
		*/
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
					FoodMenuUI.manageMenuOptions(menu);
					break;
				case 2:
					TableUI.manageTableOptions(tables, reserve);
					break;
				case 3:
					OrderUI.manageOrderOptions(orders, reserve, staff, menu,reports,tables);
					break;
				case 4:
					ReservationUI.manageReservationOptions(reserve,orders,staff,tables);
					break;
				case 5:
					ReportUI.managingReportOptions(reports, menu);
					break;
				case 6:
					StaffUI.manageStaffOptions(staff);
					break;
				case 7:
					MembershipUI.manageMembersOptions(members);
			}
		}while(choice != 0);

		Restaurant.saveRestaurant();
	}






}