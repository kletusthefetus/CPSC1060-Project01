/*
    James Maner
    CPSC 1060: Project Part 5
    Class Section 020
    4/20/2023
*/
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Pizzeria {

    /**
     * Description- Self-recurring try-catch block in order to stop the user from entering invalid number values
     * @param scnr- the scanner used to take input from the user
     * @param start- the beginning integer used for the warning if the exception is thrown
     * @param end- the ending integer used for the warning if the exception is thrown
     */
    public static int getVal(Scanner scnr, int start, int end)
    {
        int val = 0;
        try
        {
            val = scnr.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Please input a value between " + start + " and " + end + ".");
            scnr.nextLine();
            val = getVal(scnr, start, end);
        }

        return val;
    }

    public static void main(String[] args) {
        //Initialize scanner
        Scanner scnr = new Scanner(System.in);

        //Declare needed variables
        Register reg = new Register();
        Customer cus;
        String cusNumber;
        String cusEmail;
        String rewardChoice;
        String pointsUse;
        String cusFileHead = " ";
        String customerName;
        String pizzaName;
        String toppingName;
        String closingChoice;
        char customerSelection = ' ';
        Topping[] toppingList;
        int numPies = 0;
        int tipPercent;
        int diameter;
        int toppingNum;

        //Loop until the user decides to close the shop
        while (customerSelection != 'c')
        {
            //Greet and prompt the user for their name
            customerName = "";
            customerSelection = ' ';
            numPies = 0;
            System.out.println("Next in line, please!");
            System.out.println("Welcome to Adkins Pizzeria!");
            System.out.println("Can I have your name please?");
            customerName = scnr.nextLine();
            if (customerName.equals(""))
            {
                customerName = scnr.nextLine();
            }
            System.out.println();
            cus = new Customer(customerName);
            System.out.println("Ok, let's start your order, " + customerName + ".");

            //Create the file name accounting for the spaces in their name.
            if (customerName.contains(" "))
            {
                cusFileHead = customerName.replace(' ','_');
            }
            else 
            {
                cusFileHead = customerName;
            }
            String filename = String.format("%sReceipt.txt" , cusFileHead);
            FileOutputStream outStream = null;

            //Throw an exception if the filename cannot be found
            try
            {
                outStream = new FileOutputStream(filename);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Cannot find " + filename);
            }

            //Create a new printwriter to print to the new printed receipt
            PrintWriter printReceipt = new PrintWriter(outStream);

            //Initialize the receipt to store all relevant information
            Receipt cusReceipt = new Receipt(cus);

            //Loop until the user decides to quit, close the shop, or read the receipt
            while (customerSelection != 'q' && customerSelection != 'c' && customerSelection != 'r')
            {
                //Prompt user for input action
                System.out.println("Here is what you can do next:");
                System.out.println("MENU");
                System.out.println("a - Add a pizza");
                System.out.println("t - Print the total");
                System.out.println("r - Receipt");
                System.out.println("q - Quit");
                System.out.println("c - Close the store");
                System.out.println("Please make a selection:");
                customerSelection = scnr.next().charAt(0);

                //Loop until the user inputs a valid character
                while (customerSelection != 'a' && customerSelection != 't' && customerSelection != 'r' && customerSelection != 'q' && customerSelection != 'c')
                {
                    System.out.println("Please input a proper selection:");
                    customerSelection = scnr.next().charAt(0);
                }

                //Actions for each command character
                if (customerSelection == 'a')
                {
                    //Prompt the user for the name of the pizza
                    numPies++;
                    Pizza newPizza;
                    System.out.println("What would you like to name pizza number " + numPies + "?");
                    scnr.nextLine();
                    pizzaName = scnr.nextLine();
                    System.out.println();

                    //Prompt the user for the size of the pizza
                    System.out.println("What size would you like " + pizzaName + " to be?");
                    System.out.println("We can handle pizzas between 8 and 48 inches.");
                    diameter = getVal(scnr, 8, 48);
                    System.out.println();

                    //Loop until the user enters a valid pizza size 
                    while ( diameter < 8 || diameter > 48 )
                    {
                        System.out.println("Please order a pizza between 8 and 48 inches.");
                        diameter = getVal(scnr, 8, 48);
                    }

                    //Prompt the user for the amount of toppings they would like on this pizza
                    System.out.println("How many toppings would you like on this pizza?");
                    System.out.println("You can have between 0-8 toppings.");
                    toppingNum = getVal(scnr, 0, 8);

                    //Loop until the user enters a valid topping amount
                    while (toppingNum < 0 || toppingNum > 8)
                    {   
                        System.out.println("Please choose a number of toppings between 0 and 8.");
                        toppingNum = getVal(scnr, 0, 8);
                    }

                    toppingList = new Topping[toppingNum];

                    for (int i = 0; i < toppingNum; i++)
                    {
                        //Prompt the user for each topping in accordance to the number of toppings they specified
                        System.out.println("What topping would you like?");
                        System.out.println("Your options are: Pepperoni, Mushroom, Chicken, Ham, Pineapple, Sausage, Basil, Olive.");
                        toppingName = scnr.next();
                        Topping newTopping = new Topping(toppingName);

                        //Check if the topping is a valid option
                        if (newTopping.validTopping())
                        {
                            toppingList[i] = newTopping;
                        }
                        
                        //Loop until a valid topping name is entered
                        while (!newTopping.validTopping())
                        {
                            System.out.println("Chosen topping is not valid. Please input a different topping:");
                            scnr.nextLine();
                            toppingName = scnr.next();
                            newTopping.setName(toppingName);

                            if (newTopping.validTopping())
                            {
                                toppingList[i] = newTopping;
                                break;
                            }
                        
                        }

                        System.out.println("Chosen topping " + toppingName + " added.");
                    }

                    //Add all entered information into the pizza object and add that pizza to the receipt
                    newPizza = new Pizza(diameter, toppingList, pizzaName);
                    cusReceipt.addPizza(newPizza);
                    newPizza.printInfo();
                    System.out.println();
                }
                else if (customerSelection == 't')
                {
                    //Print the total price so far
                    System.out.printf("Total $%.2f.\n", cusReceipt.subTotal());
                }
                else if (customerSelection == 'r')
                {
                    
                    //Recognize if the user has not yet entered any pizzas
                    if (numPies == 0)
                    {
                        System.out.println("You have not ordered any pizzas yet.");
                        customerSelection = ' ';
                    }
                    else
                    {
                        //Prompt the user if they would like to enter the rewards program
                        System.out.println("Before we give you your receipt, would you like to join our rewards program?");
                        rewardChoice = scnr.next();

                        //Loop until the user enters yes or no
                        while (!(rewardChoice.equalsIgnoreCase("Yes") || rewardChoice.equalsIgnoreCase("No")))
                        {
                            System.out.println("Please input yes or no.");
                            rewardChoice = scnr.next();
                        }

                        if (rewardChoice.equalsIgnoreCase("Yes"))
                        {
                            //Prompt the customer for their information and calculate their rewards points
                            cus.setRewardsPoints(20);
                            cus.setRewardsMember(true);
                            System.out.println("What is your email?");
                            cusEmail = scnr.next();
                            cus.setEmail(cusEmail);
                            System.out.println("\nWhat is your phone number?");
                            cusNumber = scnr.next();
                            cus.setPhoneNumber(cusNumber);
                            cus.addRewardsPoints(cusReceipt.subTotal());
                            System.out.printf("\nYou now have %.2f rewards points.\n", cus.getRewardsPoints());
                            System.out.println("Would you like to spend them?");
                            pointsUse = scnr.next();

                            while (!(pointsUse.equalsIgnoreCase("Yes") || pointsUse.equalsIgnoreCase("No")))
                            {
                                System.out.println("Please input yes or no.");
                                pointsUse = scnr.next();
                            }

                            //If they choose to use their points, change the receipt to show that
                            if (pointsUse.equalsIgnoreCase("Yes"))
                            {
                                System.out.println("Ok, we will be using your rewards points today.\n");
                                cusReceipt.setUsingPoints(true);
                            }

                        }

                        //Prompt the user for the tip amount
                        System.out.println("What percent tip would you like to leave?");
                        tipPercent = getVal(scnr, 0, 100);

                        //Ensure that the tip percent is within 0-100%
                        if (tipPercent <= 100 && tipPercent >= 0)
                        {
                            cusReceipt.setTip(tipPercent);
                        }
                        else
                        {
                            //Loop until the user enters a tip percent within 0-100%
                            while (!(tipPercent <= 100 && tipPercent >= 0))
                            {
                                System.out.println("Please input a valid tip percent between 0 and 100 percent.");
                                tipPercent = getVal(scnr, 0, 100);
                                if (tipPercent <= 100 && tipPercent >= 0)
                                {
                                    cusReceipt.setTip(tipPercent);
                                }
                            }
                        }

                        //Print all the stored information in the reciept object to the new receipt file
                        cusReceipt.printInfo(printReceipt);
                        printReceipt.close();
                        reg.addReceipt(cusReceipt);
                    }
                }
            }
            System.out.println();
        }

        //Prompt the user if they would like to view the total information of all of the day's sales
        System.out.println("Would you like a detailed review of today's transactions?");
        closingChoice = scnr.next();

        while (!(closingChoice.equalsIgnoreCase("Yes") || closingChoice.equalsIgnoreCase("No")))
        {
            System.out.println("Please input yes or no.");
            closingChoice = scnr.next();
        }
        
        //Print all relevant information if chosen yes
        if (closingChoice.equalsIgnoreCase("Yes"))
        {
            reg.printInfo();
        }
        else
        {
            System.out.println("Adkins Pizzeria is now closed!");
        }

    }
}