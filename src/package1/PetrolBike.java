package package1;


public class PetrolBike extends Bike {

    public PetrolBike(int bikeId, String brand,
                      String model, double rentPerDay) {
        super(bikeId, brand, model, rentPerDay);
    }

    @Override
    public double calculateDailyRent() {
        return getRentPerDay();
    }
}

