package package1;


public abstract class Bike {
    private int bikeId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean available;

    public Bike(int bikeId, String brand,
                String model, double rentPerDay) {
        this.bikeId = bikeId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.available = true;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateDailyRent();

    public void displayBike() {
        System.out.println("Bike ID: " + bikeId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent Per Day: Rs. "
                + calculateDailyRent());
        System.out.println("Available: " + available);
    }
}
