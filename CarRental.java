public class CarRental {
    
    private static String allRentalSummary = new String();
    
    private static CarRental mostSpentRental = null;
    private static CarRental leastSpentRental = null;
    private static double minCharge = Double.MAX_VALUE;
    private static double maxCharge = Double.MIN_VALUE;
    
    private static final int    BASE_PERIOD         = 5;
    private static final int    INTERMEDIATE_PERIOD = 10;
    private static final double BASE_FARE           = 25.0;
    private static final double INTERMEDIATE_FARE   = 22.5;
    private static final double LONG_FARE           = 21.0;
    
    private static final String lineSeparator = System.lineSeparator();
    
    private String  name;
    private int     days;
    private boolean offerApplicable;
    private double  charge;
    
    public CarRental(String name, int days, boolean offerApplicable) {
        this.name = name;
        this.days = days;
        this.offerApplicable = offerApplicable;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDays() {
        return days;
    }
    
    public boolean isOfferApplicable() {
        return offerApplicable;
    }
    
    public double getCharge() {
        return charge;
    }
    
    public void calculateRental() {
        if (days <= BASE_PERIOD)
            charge = days * BASE_FARE;
        else if (days <= INTERMEDIATE_PERIOD)
            charge = days * INTERMEDIATE_FARE;
        else
            charge = days * LONG_FARE;
        
        // If offer is applicable charge is reduced by 10%
        if (offerApplicable)
            charge -= (charge * 10) / 100;
            
        if (charge > maxCharge) {
            maxCharge = charge;
            mostSpentRental = this;
        } 
        
        if (charge < minCharge) {
            minCharge = charge;
            leastSpentRental = this;
        }
    }
    
    public void displayInfo() {
        System.out.printf("\n\tThe rental of car hire from %s is $%.2f%n", name, charge);
        System.out.println("--------------------------------------------------------------------");
    }
    
    public void addToSummary(String oneRental) {
        allRentalSummary += lineSeparator + oneRental;
    }
}