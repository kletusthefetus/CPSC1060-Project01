/*
    James Maner
    CPSC 1060: Project Part 1
    Class Section 020
    2/16/2023
*/
//Import scanner class
import java.util.Scanner;

public class Pizzeria {
    public static void main(String[] args) {
        //Initialize scanner
        Scanner scnr = new Scanner(System.in);

        //Establish all needed variables
        String customerName;
        String pizzaName;
        int numPies;
        int pizzaSize;
        double totalCost = 0;
        double pizzaArea;
        char pepperChar;
        char displayTotal;

        //Establish all needed constants
        final double cheeseCostPSI = .0068;
        final double sauceCostPSI = .0079;
        final double doughCostPSI = .0057;
        final double pepperoniCostPSI = .0071;

        //Prompt user for username
        System.out.println("Welcome to Adkins Pizzeria!");
        System.out.println("Can I have your name please?");
        customerName = scnr.nextLine();

        //Prompt user for number of pizzas
        System.out.println("Ok, let's start your order, " + customerName + ".");
        System.out.println("How many pizzas would you like to order?");
        System.out.println("Our oven can only accommodate 5 pizzas per customer.");
        numPies = scnr.nextInt();
        System.out.println();

        //Loop until user enters a number of pizzas between 1 and 5
        while (numPies > 5 || numPies < 1)
        {
            System.out.println("Please order a number of pizzas between 1 and 5.");
            numPies = scnr.nextInt();
        }

        //Loop for each pizza
        for (int i = 1; i <= numPies; i++)
        {
            //Prompt user for the name of the pizza
            System.out.println("What would you like to name pizza number " + i + "?");
            pizzaName = scnr.next();

            //Prompt user for the size of the pizza
            System.out.println("What size would you like " + pizzaName + " to be?");
            System.out.println("We can handle pizzas between 8 and 48 inches.");
            pizzaSize = scnr.nextInt();

            //Loop until the user enters a pizza size between 8 and 48
            while (pizzaSize > 48 || pizzaSize < 8)
            {
                System.out.println("Please order a pizza between 8 and 48 inches");
                pizzaSize = scnr.nextInt();
            }

            //Calculate the area of the pizza and the total cost of the pizza without pepperoni 
            pizzaArea = Math.pow(pizzaSize / 2.00, 2.00) * Math.PI;
            totalCost += (cheeseCostPSI * pizzaArea) + (doughCostPSI * pizzaArea) + (sauceCostPSI * pizzaArea);

            //Prompt the user if they would like pepperoni on this pizza
            System.out.println("Would you like pepperonis on this pizza?");
            System.out.println("Please type Y for Yes or N for No.");
            pepperChar = scnr.next().charAt(0);

            //Loop until user enters Y for yes or N for no to decide on pepperoni
            while (pepperChar != 'Y' && pepperChar != 'N')
            {
                System.out.println("Invalid input. Please type Y for Yes or N for No.");
                pepperChar = scnr.next().charAt(0);
            }

            //Add the cost of the pepperoni on this pizza to the total cost
            if (pepperChar == 'Y')
            {
                totalCost += (pepperoniCostPSI * pizzaArea);
            }

            //Prompt the user for the option to display the current total
            System.out.println("Would you like to display the total so far? (Y/N)");
            displayTotal = scnr.next().charAt(0);

            //Loop until the user enters Y for yes or N for no to decide to show the total
            while (displayTotal != 'Y' && displayTotal != 'N')
            {
                System.out.println("Invalid input. Please type Y to display the total and N to continue.");
                displayTotal = scnr.next().charAt(0);
            }

            //If the user decides to show the total, display the total rounded to 2 decimal points
            if (displayTotal == 'Y')
            {
                System.out.println();
                System.out.printf("Total: $%.2f", totalCost);
                System.out.println();
            }
        }
        
        //Print out the final statement depending on the number of pizzas, displaying the total, the username, and the number of pizzas
        System.out.println("");
        if (numPies == 1) {
            System.out.printf("Ok, %s your total for your 1 pizza will come out to $%.2f.", customerName, totalCost);
        }
        else {
            System.out.printf("Ok, %s your total for your %d pizzas will come out to $%.2f.", customerName, numPies, totalCost);
        }

    }
}