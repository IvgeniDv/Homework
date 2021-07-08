/*
 * **********************************************************************
 * 2021 March
 * MAMAN 11
 * Ivgeni Dvorkin
 * The programs recieves a persons age 
 * returns his recomended heart rate min and max values while exercising
 * **********************************************************************
 */
import java.util.Scanner;

public class HeartRate{
    public static void main(String[] args){
        
        // Var declaration
        Scanner scan = new Scanner(System.in);
        final int CONST = 220;
        final int MAX_HEART_RATE = 85;
        final int MIN_HEART_RATE = 65;
        final int PERCENTAGE = 100;
        int age;
        
        // User interaction
        System.out.println("This program calculates your target heart" 
                            + "rate while exercising.");
        System.out.print("Enter your age: ");
        age = scan.nextInt();  
    
        // Calculations and output 
        System.out.println("Your estimated target heart rate zone is " 
                            + (((CONST - age) * MIN_HEART_RATE) / PERCENTAGE)
                            + " - "
                            + (((CONST - age) * MAX_HEART_RATE) / PERCENTAGE) 
                            + " beats per minute.");
        
       
    }   // end of main method
}   // end of HeartRate class