import java.util.Scanner;
import java.util.StringTokenizer;

public class CarRentalTest {
    
    // To be replaced with the maximum digit of my Student ID, minimum value is 4
    private static final int N = 7;
    
    private static final String DOLLAR = "$";
    private static final String FIELD_SEP = "@";
    private static final String RECORD_SEP = "#";
    
    public static void main(String[] args) {
        /**
        *   Concatenation of all rentals
        *   Each rental is separated by RECORD_SEP
        *   Each field is separated by FIELD_SEP
        */
        StringBuilder allRentals = new StringBuilder();
        
        // To track the maximum and minimum rental
        double minCharge = Double.MAX_VALUE;
        double maxCharge = Double.MIN_VALUE;
        CarRental mostSpentRental = null;
        CarRental leastSpentRental = null;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("\n\n\t\tWelcome to use CarRental calculator");
        for (int i = 0; i < N; i++) {
            String name = null;
            int days = 0;
            boolean offerApplicable = false;
            double charge = 0.0;
            
            // Input data
            while (true) {
                System.out.printf("\n\nEnter customer name %d: ", i + 1);
                name = in.nextLine();
                
                if (!name.isEmpty()) // Positive case
                    break;
                else
                    System.out.println("\nI hope you got a name");
            }
            
            while (true) {
                System.out.print("\nEnter the number of days: ");
                days = Integer.parseInt(in.nextLine());
                
                if (isDaysValid(days)) // Positive case
                    break;
                else
                    System.out.println("\nInvalid days. Please enter anywhere in [1-365]");
            }
            
            while (true) {
                System.out.print("\nEnter yes or no to indicate a special offer: ");
                String answer = in.nextLine();
                
                if (isValidYesOrNo(answer)) { // Positive case
                    offerApplicable = yesOrNo(answer);
                    break;
                }
                else
                    System.out.println("\nInvalid answer. Please answer either Yes or No");
            }
            
            // Create CarRental objects with the input data
            CarRental rental = new CarRental(name, days, offerApplicable);
            
            rental.calculateRental(); // Mandatory call, otherwise charge wont be calculated
            rental.displayInfo();
            
            charge = rental.getCharge();
            
            // appending the current rental in defined format
            allRentals.append(name);
            allRentals.append(FIELD_SEP);
            allRentals.append(days);
            allRentals.append(FIELD_SEP);
            allRentals.append(offerApplicable);
            allRentals.append(FIELD_SEP);
            allRentals.append(String.format("%.2f",charge));
            allRentals.append(RECORD_SEP);
            
            // Track the maximum and minimum charge rentals
            if (rental.getCharge() > maxCharge) {
                maxCharge = rental.getCharge();
                mostSpentRental = rental;
            } 
            
            if (rental.getCharge() < minCharge) {
                minCharge = rental.getCharge();
                leastSpentRental = rental;
            }
            
        }
        
        // Call a convenient method that takes care of displaying the summary
        displaySummary(allRentals.toString(), mostSpentRental, leastSpentRental);
    }
    
    private static boolean isDaysValid(int days) {
        return days >= 1 && days <= 365;
    }
    
    // Checks if the answer is either Yes or No
    private static boolean isValidYesOrNo(String answer) {
        return answer.equalsIgnoreCase("Yes") || 
                answer.equalsIgnoreCase("No");
    }
    
    // Converts Yes or No to boolean
    private static boolean yesOrNo(String answer) {
        return answer.equalsIgnoreCase("Yes");
    }
    
    private static void displaySummary(String allRentals, 
                                        CarRental mostSpentRental, 
                                        CarRental leastSpentRental) {
        String lessThanSeven = "";
        String greaterThanSeven = "";
        StringTokenizer recordSplitter = null;
        StringTokenizer fieldSplitter = null;
        
        System.out.println("\n\n\t\tSummary of Car Rentals\n");
        System.out.println("===============================================================");
        System.out.printf("%-20s %-15s %-15s %10s%n", "Name", "Days", "SpecialOffer", "Charge");
        System.out.println("---------------------------------------------------------------");
        
        recordSplitter = new StringTokenizer(allRentals, RECORD_SEP);
        while (recordSplitter.hasMoreTokens()) {
            String rental = recordSplitter.nextToken();
            
            fieldSplitter = new StringTokenizer(rental, FIELD_SEP);
            
            String name             = fieldSplitter.nextToken();
            String days             = fieldSplitter.nextToken();
            String offerApplicable  = Boolean.valueOf(fieldSplitter.nextToken()) ? "Yes" : "No";
            String charge           = DOLLAR + fieldSplitter.nextToken();
            
            System.out.printf("%-20s %-15s %-15s %10s%n", name, days, offerApplicable, charge);
            
            if (Integer.parseInt(days) < 7)
                lessThanSeven += "*";
            else
                greaterThanSeven += "*";
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------\n");
        System.out.printf("The customer spending most rental is %s $%.2f%n%n", mostSpentRental.getName(), mostSpentRental.getCharge());
        System.out.printf("The customer spending least rental is %s $%.2f%n%n", leastSpentRental.getName(), leastSpentRental.getCharge());
        
        System.out.println("The rental days < 7   : " + lessThanSeven);
        System.out.println("The rental days >= 7  : " + greaterThanSeven);
    }
}