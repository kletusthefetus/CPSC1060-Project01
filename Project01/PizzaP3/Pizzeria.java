/*
    James Maner
    CPSC 1060: Project Part 3
    Class Section 020
    3/29/2023
*/
//Import scanner class
import java.util.Scanner;
import java.util.ArrayList;

public class Pizzeria {
    public static void main(String[] args) {
        //Initialize scanner
        Scanner scnr = new Scanner(System.in);

        //Declare needed variables
        String customerName;
        String pizzaName;
        String toppingName;
        char customerSelection = ' ';
        Topping[] toppingList;
        int numPies = 0;
        int tipPercent;
        int diameter;
        int toppingNum;

        //Greet and prompt the user for their name
        System.out.println("Welcome to Adkins Pizzeria!");
        System.out.println("Can I have your name please?");
        customerName = scnr.nextLine();
        System.out.println();
        System.out.println("Ok, let's start your order, " + customerName + ".");

        //Initialize the receipt to store all relevant information
        Receipt cusReceipt = new Receipt(customerName);

        //Loop until the user decides to quit
        while (customerSelection != 'q')
        {
            //Prompt user for input action
            System.out.println("Here is what you can do next:");
            System.out.println("MENU");
            System.out.println("a - Add a pizza");
            System.out.println("t - print the total");
            System.out.println("r - Receipt");
            System.out.println("q - quit");
            System.out.println("Please make a selection:");
            customerSelection = scnr.next().charAt(0);

            //Loop until the user inputs a valid character
            while (customerSelection != 'a' && customerSelection != 't' && customerSelection != 'r' && customerSelection != 'q')
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
                diameter = scnr.nextInt();
                System.out.println();

                //Loop until the user enters a valid pizza size 
                while ( diameter < 8 || diameter > 48 )
                {
                    System.out.println("Please order a pizza between 8 and 48 inches.");
                    diameter = scnr.nextInt();
                }

                //Prompt the user for the amount of toppings they would like on this pizza
                System.out.println("How many toppings would you like on this pizza?");
                System.out.println("You can have between 0-8 toppings.");
                toppingNum = scnr.nextInt();

                //Loop until the user enters a valid topping amount
                while (toppingNum < 0 || toppingNum > 8)
                {   
                    System.out.println("Please choose a number of toppings between 0 and 8.");
                    toppingNum = scnr.nextInt();
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
                }
                else
                {
                    //Prompt the user for the tip amount
                    System.out.println("What percent tip would you like to leave?");
                    tipPercent = scnr.nextInt();

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
                            tipPercent = scnr.nextInt();
                            if (tipPercent <= 100 && tipPercent >= 0)
                            {
                                cusReceipt.setTip(tipPercent);
                            }
                        }
                    }

                    //Print all the stored information in the reciept object
                    cusReceipt.printInfo();
                }
            }
        }

    }
}