package package1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String BIKE_FILE = "bikes.txt";

    // Save bikes to file
    public static void saveBikes(List<Bike> bikes) {
        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(BIKE_FILE))) {

            for (Bike bike : bikes) {
                String type = bike instanceof ElectricBike
                        ? "ELECTRIC" : "PETROL";

                writer.write(
                    bike.getBikeId() + "," +
                    bike.getBrand() + "," +
                    bike.getModel() + "," +
                    bike.getRentPerDay() + "," +
                    bike.isAvailable() + "," +
                    type
                );

                writer.newLine();
            }

            System.out.println("Bikes saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving bikes: " + e.getMessage());
        }
    }

    // Load bikes from file
    public static List<Bike> loadBikes() {
        List<Bike> bikes = new ArrayList<>();

        File file = new File(BIKE_FILE);

        if (!file.exists()) {
            return bikes;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length != 6) {
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                String brand = data[1];
                String model = data[2];
                double rent = Double.parseDouble(data[3]);
                boolean available = Boolean.parseBoolean(data[4]);
                String type = data[5];

                Bike bike;

                if (type.equals("ELECTRIC")) {
                    bike = new ElectricBike(id, brand, model, rent);
                } else if (type.equals("PETROL")) {
                    bike = new PetrolBike(id, brand, model, rent);
                } else {
                    continue;
                }

                bike.setAvailable(available);
                bikes.add(bike);
            }

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading bikes: " + e.getMessage());
        }

        return bikes;
    }
    

    public static List<Rental> loadRentals(
            List<Bike> bikes, List<Customer> customers) {

        List<Rental> rentals = new ArrayList<>();
        File file = new File(RENTAL_FILE);

        if (!file.exists()) {
            return rentals;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length != 6) {
                    continue;
                }

                int rentalId = Integer.parseInt(data[0]);
                int customerId = Integer.parseInt(data[1]);
                int bikeId = Integer.parseInt(data[2]);
                int days = Integer.parseInt(data[3]);
                boolean returned = Boolean.parseBoolean(data[5]);

                Customer customer = null;
                Bike bike = null;

                for (Customer c : customers) {
                    if (c.getCustomerId() == customerId) {
                        customer = c;
                        break;
                    }
                }

                for (Bike b : bikes) {
                    if (b.getBikeId() == bikeId) {
                        bike = b;
                        break;
                    }
                }

                if (customer == null || bike == null || days <= 0) {
                    continue;
                }

                Rental rental = new Rental(
                    rentalId, customer, bike, days
                );

                if (returned) {
                    rental.markReturned();
                } else {
                    bike.setAvailable(false);
                }

                rentals.add(rental);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Rental load error: "
                    + e.getMessage());
        }

        return rentals;
    }


    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String RENTAL_FILE = "rentals.txt";

    // Save customers
    public static void saveCustomers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(CUSTOMER_FILE))) {

            for (Customer c : customers) {
                writer.write(c.getCustomerId() + ","
                        + c.getName() + ","
                        + c.getPhone());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Customer save error: "
                    + e.getMessage());
        }
    }

    // Load customers
    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(CUSTOMER_FILE);

        if (!file.exists()) {
            return customers;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length != 3) {
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                customers.add(
                    new Customer(id, data[1], data[2])
                );
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Customer load error: "
                    + e.getMessage());
        }

        return customers;
    }

    // Save rental records
    public static void saveRentals(List<Rental> rentals) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(RENTAL_FILE))) {

            for (Rental r : rentals) {
                writer.write(r.getRentalId() + ","
                        + r.getCustomer().getCustomerId() + ","
                        + r.getBike().getBikeId() + ","
                        + r.getRentalDays() + ","
                        + r.getTotalAmount() + ","
                        + r.isReturned());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Rental save error: "
                    + e.getMessage());
        }
    }

}

