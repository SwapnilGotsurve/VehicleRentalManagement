package package1;
import java.util.ArrayList;

public class RentalService {
	
	private ArrayList<Bike>  bikes = new ArrayList<>();
	private ArrayList<Customer>  customers = new ArrayList<>();
	private ArrayList<Rental>  rentals = new ArrayList<>();
	
	//add a bike
	public void addBike(Bike bike) {
		bikes.add(bike);
		System.out.println("Bike added successfully!");
	}
	
	//Register a customer
	public void registerCustomer(Customer customer) {
		customers.add(customer);
		System.out.println("Customer registered successfully!");
	}
	//display all bikes
	public void viewBikes() {
		if(bikes.isEmpty()) {
			System.out.println("No bikes available");
			return ;
		}
		for(Bike bike: bikes) {
			bike.displayBike();
			System.out.println("------------------------");
		}
	}
	//display all customers
	public void viewCustomers() {
		if(customers.isEmpty()) {
			System.out.println("No customers available");
			return;
		}
		for(Customer customer: customers) {
			customer.displayCustomer();
			System.out.println("------------------------");
		}
	}
	
	//Rent a bike
	public void rentBike(int rentalId,int customerId, int bikedId, int days) {
		Bike selectedBike = null;
		Customer selectedCustomer = null;
		
		for (Customer customer : customers) {
			if(customer.getCustomerId() == customerId) {
				selectedCustomer = customer ;
				break;
			}
		}
		
		for(Bike bike: bikes) {
			if(bike.getBikeId() == bikedId ) {
				selectedBike = bike;
				break;
			}
		}
		
		if(selectedCustomer == null) {
			System.out.println("Customer not found ");
			return ;
		}
		if(selectedBike == null) {
			System.out.println("Bike not found");
			return;
		}
		
		if(!selectedBike.isAvailable()) {
			System.out.println("Bike is already rented.");
			return;
		}
		
		if(days <= 0) {
			System.out.println("Rental days must be positive.");
			return ;
		}
		
		Rental rental = new Rental(rentalId, selectedCustomer, selectedBike, days);
		rentals.add(rental);
		selectedBike.setAvailable(false);
		
		System.out.println("Bike  rented successfully!");
		rental.displayRental();
	}
	
	//display rental history 
	
	public void viewRentalHistory() {
		if(rentals.isEmpty()) {
			System.out.println("no rental records found.");
			return ;
		}
		
		for(Rental rental : rentals) {
			rental.displayRental();
			System.out.println("-----------------------");
		}
	}
	
	//return bike 
	public void returnBike(int rentalId) {
		for(Rental rental: rentals) {
			if(rental.getRentalId() == rentalId) {
				if(rental.isReturned()) {
					System.out.println("This bike is already returned.");
					return;
				}
				rental.markReturned();
				rental.getBike().setAvailable(true);
				System.out.println("Bike returned successfully!");
	            System.out.println("Final Rental Bill:");
	            rental.displayRental();

	            return;

			
			}
		}
		   System.out.println("Rental ID not found.");
	}
	
	//load and save all bikes

public void loadSavedBikes() {
    bikes.clear();
    bikes.addAll(FileManager.loadBikes());
}

public void saveAllBikes() {
    FileManager.saveBikes(bikes);
}

public void saveAllData() {
    FileManager.saveBikes(bikes);
    FileManager.saveCustomers(customers);
    FileManager.saveRentals(rentals);
}

public void loadAllData() {
    bikes.clear();
    customers.clear();
    rentals.clear();

    bikes.addAll(FileManager.loadBikes());
    customers.addAll(FileManager.loadCustomers());

    rentals.addAll(
        FileManager.loadRentals(bikes, customers)
    );
}

	//adding exceptions

public void rentBikeWithValidation(
        int rentalId, int customerId, int bikeId, int days)
        throws InvalidRentalException {

    if (days <= 0) {
        throw new InvalidRentalException(
            "Rental days must be greater than zero."
        );
    }

    Customer selectedCustomer = null;
    Bike selectedBike = null;

    for (Customer customer : customers) {
        if (customer.getCustomerId() == customerId) {
            selectedCustomer = customer;
            break;
        }
    }

    for (Bike bike : bikes) {
        if (bike.getBikeId() == bikeId) {
            selectedBike = bike;
            break;
        }
    }

    if (selectedCustomer == null) {
        throw new InvalidRentalException("Customer not found.");
    }

    if (selectedBike == null) {
        throw new InvalidRentalException("Bike not found.");
    }

    if (!selectedBike.isAvailable()) {
        throw new InvalidRentalException(
            "Bike is already rented."
        );
    }

    Rental rental = new Rental(
        rentalId, selectedCustomer, selectedBike, days
    );

    rentals.add(rental);
    selectedBike.setAvailable(false);

    System.out.println("Bike rented successfully!");
    rental.displayRental();
}




}
