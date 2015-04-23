public class CarRental {
    
    // List of all constants to be used
    private static final int    BASE_PERIOD         = 5;
    private static final int    INTERMEDIATE_PERIOD = 10;
    private static final double BASE_FARE           = 25.0;
    private static final double INTERMEDIATE_FARE   = 22.5;
    private static final double LONG_FARE           = 21.0;
    private static final char   NEW_LINE            = '\n';
    
    
    /**
    *   Concatenated list of all rental details in the following format
    *
    *       John Smith      10      Yes     $225.00
    *       Josh Bloch      5       Yes     $125.00
    *
    */
    private static String allRentalSummary = new String();
    
    // Set of references and primitive variables to track most/least spent rental
    private static CarRental mostSpentRental = null;
    private static CarRental leastSpentRental = null;
    private static double minCharge = Double.MAX_VALUE;
    private static double maxCharge = Double.MIN_VALUE;
    
    // To track rentals that are taken for less than a week or more
    private static String lessThanSeven = "";
    private static String greaterThanSeven = "";
    
    // Attributes of this (CarRental) class
    private String  name;
    private int     days;
    private boolean offerApplicable;
    private double  charge;
    
    public CarRental(String tempName, int tempDays, boolean tempOfferApplicable) {
        name = tempName;
        days = tempDays;
        offerApplicable = tempOfferApplicable;
        
        if (days < 7)
            lessThanSeven += "*";
        else
            greaterThanSeven += "*";
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
            charge -= charge * (10 / 100);
            
        // Keep track of most/least spent rental on the fly
        if (charge > maxCharge) {
            maxCharge = charge;
            CarRental.mostSpentRental = this;
        }
        
        if (charge < minCharge) {
            minCharge = charge;
            CarRental.leastSpentRental = this;
        }
        
    }
    
    // Displays the current rental in prescribed format. Called after getting each input
    public void displayInfo() {
        System.out.printf("\n\tThe rental of car hire from %s is $%.2f%n", name, charge);
        System.out.println("--------------------------------------------------------------------");
    }
    
    // Concatenates one rental to the summary with a newline
    public static void addToSummary(String oneRental) {
        allRentalSummary += oneRental + NEW_LINE;
    }
    
    // Displays summary in the prescribed format
    public static void displaySummary() {
        System.out.println("\n\n\t\tSummary of Car Rentals\n");
        System.out.println("===============================================================");
        System.out.printf("%-20s %-15s %-15s %10s%n", "Name", "Days", "SpecialOffer", "Charge");
        System.out.println("---------------------------------------------------------------");
        
        System.out.println(allRentalSummary);
        
        System.out.println();
        System.out.println("---------------------------------------------------------------\n");
        System.out.printf("The customer spending most rental is %s $%.2f%n%n", mostSpentRental.getName(), mostSpentRental.getCharge());
        System.out.printf("The customer spending least rental is %s $%.2f%n%n", leastSpentRental.getName(), leastSpentRental.getCharge());
        
        System.out.println("The rental days < 7   : " + lessThanSeven);
        System.out.println("The rental days >= 7  : " + greaterThanSeven);
    }
}
