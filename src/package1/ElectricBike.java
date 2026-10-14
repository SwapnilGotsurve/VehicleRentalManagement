package package1;


public class ElectricBike extends Bike {

    public ElectricBike(int bikeId, String brand,
                        String model, double rentPerDay) {
        super(bikeId, brand, model, rentPerDay);
    }

    @Override
    public double calculateDailyRent() {
        return getRentPerDay() + 100;
    }
}

