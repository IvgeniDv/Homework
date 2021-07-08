/*
 **************************************************************************
 * 2021 March
 * Writen by Ivgeni Dvorkin
 * Maman 11 question 2
 * The Number class prints a number which was manipulated according to the users choice
 **************************************************************************
 */
import java.util.Scanner;

public class Number{
    
    /*
     **************************************************************************
     * The method reads user input of a number that consists of 4 digits
     * the program then manipulates the number according to the users desire
     * (out of 3 possible options) and returns the result
     * otherwise the programs displays errors accordingly
     **************************************************************************
     */
    
    public static void main(String[] args){
    
        // var declarations
        
        final int POS_MIN_VALUE = 999; // input number must be greater than 3 digits
        final int POS_MAX_VALUE = 10000; // input number must be less than 5 digits
        final int NEG_MAX_VALUE = -999; // this and the following variable help determine 
                                        // if the inputed num is a negative 4 digit number           
        final int NEG_MIN_VALUE = -10000;
        final int REVERSE = -1;  // a var to reverse the sign of a given number
        final int ONES_TENS = 10;  // this var helps calculat the first two digits of the users number
        final int HUNDREDS = 100; // this var helps calculat the hundreds digit of the users number
        final int THOUSANDS = 1000; // this var helps calculat the thousands digit of the users number
        Scanner scan = new Scanner(System.in);
        int num;  // holds users number input
        int choice;  // holds users option choice
        int one; // the following variables will hold the calculations needed to reverse a given number
        int two;
        int three;
        int four;
        int newNum; // used to create a reversed number
        
        
        
        // user interaction and input
        System.out.println("Please enter a 4 digit number:");
        num = scan.nextInt();
        
       
        if (!((num > POS_MIN_VALUE && num < POS_MAX_VALUE) || (num > NEG_MIN_VALUE && num < NEG_MAX_VALUE))){   // check of correct number input 
            System.out.println("Illegal number - you must enter a 4 digit number");
        }
        else{  
            System.out.println("1. Reverse sign.");
            System.out.println("2. Reverse number.");
            System.out.println("3. Reverse sign and number.");
            System.out.println("Please choose an option:");
            choice = scan.nextInt(); 
            
            if (choice > 3 || choice < 1){  // check of correct option input 
                System.out.println("Illigal option - you must choose 1, 2 or 3");
            }
            else{
                // calculations to reverse the number given by the user
                one = ((Math.abs(num) % ONES_TENS) * THOUSANDS); //gets the first digit and turns it to fourth
                two = (((Math.abs(num) / ONES_TENS) % ONES_TENS) * HUNDREDS); // gets the second and truns it to third
                three = (((Math.abs(num) / HUNDREDS) % ONES_TENS) * ONES_TENS); //gets the third digit and turns it to second
                four = (Math.abs(num) / THOUSANDS); //gets the fourth digit
                newNum = one + two + three + four;
                System.out.println("The result is");
                if (choice == 1){
                System.out.println(num * REVERSE);  // reverses the sign of the users number
                }
                else if (choice == 2){
                    if (num > 0){
                        System.out.print(newNum);
                    }
                    else{
                        System.out.print(newNum * REVERSE); 
                    }
                }
                else{
                    if (num > 0){
                        System.out.print(newNum * REVERSE);
                    }
                    else{
                    System.out.print(newNum);
                    }
                }
            }
        }
        
    
    }  // end of method: main
} // end of class: Number