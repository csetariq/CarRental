import java.util.Scanner;

public class CarRentalTest {
    
    private static final int N = 8;
    
    private static final String DOLLAR = "$";
    
    public static void main(String[] args) {
              
        Scanner in = new Scanner(System.in);
        
        System.out.println("\n\n\t\tWelcome to use CarRental calculator");
        
        for (int i = 0; i < N; i++) {
            String  name            = null;
            int     days            = 0;
            boolean offerApplicable = false;
            double  charge          = 0.0;
            
            String  currentRental   = new String();
            
            // Input data
            // Repeat infinitely until proper input is given
            while (true) {
                System.out.printf("\n\nEnter customer name %d: ", i + 1);
                name = in.nextLine();
                
                if (!name.isEmpty()) // Positive case - Name is not empty
                    break;
                else
                    System.out.println("\nName cannot be empty");
            }
            
            while (true) {
                System.out.print("\nEnter the number of days: ");
                days = Integer.parseInt(in.nextLine());
                
                if (isDaysValid(days)) // Positive case - days is between 1 and 365 inclusive
                    break;
                else
                    System.out.println("\nInvalid days. Please enter anywhere in [1-365]");
            }
            
            while (true) {
                System.out.print("\nEnter yes or no to indicate a special offer: ");
                String answer = in.nextLine();
                
                if (isValidYesOrNo(answer)) { // Positive case - given answer is either "yes" or "no" irrespective of the case
                    offerApplicable = yesOrNo(answer); // Convert "yes" or "no" to boolean 
                    break;
                }
                else
                    System.out.println("\nInvalid answer. Please answer either Yes or No");
            }
            
            // Create CarRental object with the input data
            CarRental rental = new CarRental(name, days, offerApplicable);
            
            rental.calculateRental(); // Mandatory call, otherwise charge wont be calculated
            rental.displayInfo();
            
            charge = rental.getCharge();
            String formattedCharge = DOLLAR + String.format("%.2f", charge); // Format the charge as String, to have 2 decimals precision and have preceded by '$' symbol e.g. $225.50
            
            currentRental += String.format("%-20s ", name);
            currentRental += String.format("%-15d ", days);
            currentRental += String.format("%-10s ", boolToYesOrNo(offerApplicable));
            currentRental += String.format("%10s",formattedCharge);
            
            CarRental.addToSummary(currentRental);
        }
        
        // Call a convenient method that takes care of displaying the summary
        CarRental.displaySummary();
    }
    
    private static boolean isDaysValid(int days) {
        return days >= 1 && days <= 365;
    }
    
    // Validates if the answer is either Yes or No
    private static boolean isValidYesOrNo(String answer) {
        return answer.equalsIgnoreCase("Yes") || 
                answer.equalsIgnoreCase("No");
    }
    
    // Converts Yes or No to boolean
    private static boolean yesOrNo(String answer) {
        return answer.equalsIgnoreCase("Yes");
    }
    
    // Converts boolean to Yes or No String
    private static String boolToYesOrNo(boolean value) {
        if (value)
            return "Yes";
        else
            return "No";
    }    
}
