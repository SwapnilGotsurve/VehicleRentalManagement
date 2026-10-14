package package1;
import java.time.LocalDate;

public class Rental {
	private int rentalId;
	private Customer customer;
	private Bike bike;
	private int rentalDays;
	private double totalAmount;
	 private boolean returned;
	 private LocalDate rentalDate;
	 private LocalDate returnDate;
	
	//constructor
	public Rental(int rentalId,Customer customer,Bike bike, int rentalDays) {
		this.rentalId = rentalId;
		this.customer = customer;
		this.bike = bike;
		this.rentalDays = rentalDays;
		this.totalAmount = bike.calculateDailyRent() * rentalDays;
		this.returned = false;
        this.rentalDate = LocalDate.now();
	}
		//getter
		public int getRentalId() {
			return rentalId;
		}
		public Customer getCustomer() {
			return customer;
		}
		public int getRentalDays() {
			return rentalDays;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		 public boolean isReturned() {
		        return returned;
		    }

		    public void markReturned() {
		        returned = true;
		        returnDate = LocalDate.now();
		    }
		
		// Display rental details
	    public void displayRental() {
	        System.out.println("Rental ID: " + rentalId);
	        System.out.println("Customer: " + customer.getName());
	        System.out.println("Bike: " + bike.getModel());
	        System.out.println("Rental Days: " + rentalDays);
	        System.out.println("Total Amount: Rs. " + totalAmount+"\n");
	        System.out.println("Status: " + (returned ? "Returned" : "Rented"));

	        if (returnDate != null) {
	            System.out.println("Return Date: " + returnDate);
	        }
	    }
		public Bike getBike() {
			return bike;
		}
}
