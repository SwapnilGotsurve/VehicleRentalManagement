package package1;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RentalService service = new RentalService();
        service.loadAllData();
        service.loadSavedBikes();
        service.saveAllBikes();
        System.out.println("Thank you for using the system!");
        
        int nextBikeId = 101;
        int nextCustomerId = 1;
        int nextRentalId = 1001;
        int choice = 0;
        service.saveAllData();

        do {
        	
            System.out.println("\n==================================");
            System.out.println("   RENTAL BIKE MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Petrol Bike");
            System.out.println("2. Add Electric Bike");
            System.out.println("3. View All Bikes");
            System.out.println("4. Register Customer");
            System.out.println("5. View All Customers");
            System.out.println("6. Rent a Bike");
            System.out.println("7. Return a Bike");
            System.out.println("8. View Rental History");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            
            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                    case 2:
                        System.out.print("Enter brand: ");
                        String brand = sc.nextLine().trim();

                        System.out.print("Enter model: ");
                        String model = sc.nextLine().trim();

                        System.out.print("Enter rent per day: ");
                        double rent = Double.parseDouble(
                            sc.nextLine().trim()
                        );

                        if (brand.isBlank() || model.isBlank()
                                || !Double.isFinite(rent)
                                || rent <= 0) {
                            System.out.println(
                                "Enter valid bike details and rent."
                            );
                            break;
                        }

                        Bike bike;

                        if (choice == 1) {
                            bike = new PetrolBike(
                                nextBikeId, brand, model, rent
                            );
                        } else {
                            bike = new ElectricBike(
                                nextBikeId, brand, model, rent
                            );
                        }

                        service.addBike(bike);
                        nextBikeId++;
                        break;

                    case 3:
                        service.viewBikes();
                        break;

                    case 4:
                        System.out.print("Enter customer name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter 10-digit phone: ");
                        String phone = sc.nextLine().trim();

                        if (name.isBlank()
                                || !phone.matches("[0-9]{10}")) {
                            System.out.println(
                                "Enter a valid name and phone number."
                            );
                            break;
                        }

                        Customer customer = new Customer(
                            nextCustomerId, name, phone
                        );

                        service.registerCustomer(customer);
                        nextCustomerId++;
                        break;

                    case 5:
                        service.viewCustomers();
                        break;

                    case 6:
                        System.out.print("Enter customer ID: ");
                        int customerId = Integer.parseInt(
                            sc.nextLine().trim()
                        );

                        System.out.print("Enter bike ID: ");
                        int bikeId = Integer.parseInt(
                            sc.nextLine().trim()
                        );

                        System.out.print("Enter rental days: ");
                        int days = Integer.parseInt(
                            sc.nextLine().trim()
                        );

                        try {
                            service.rentBikeWithValidation(
                                nextRentalId,
                                customerId,
                                bikeId,
                                days
                            );
                            nextRentalId++;
                        } catch (InvalidRentalException e) {
                            System.out.println(
                                "Rental Error: " + e.getMessage()
                            );
                        }
                        break;

                    case 7:
                        System.out.print("Enter rental ID: ");
                        int rentalId = Integer.parseInt(
                            sc.nextLine().trim()
                        );

                        service.returnBike(rentalId);
                        break;

                    case 8:
                        service.viewRentalHistory();
                        break;

                    case 9:
                        service.saveAllData();
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println(
                            "Invalid choice. Select 1 to 9."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input! Please enter a valid number."
                );
            }

        } while (choice != 9);

        sc.close();
    }
}
