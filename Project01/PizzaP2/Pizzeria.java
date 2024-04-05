/*
    James Maner
    CPSC 1060: Project Part 2
    Class Section 020
    2/21/2023
*/
//Import scanner class
import java.util.Scanner;
import java.util.ArrayList;

public class Pizzeria {
    public static void main(String[] args) {
        //Initialize scanner
        Scanner scnr = new Scanner(System.in);

        //Establish all needed variables
        String customerName;
        String toppingName;
        int numPies = 0;
        int pizzaSize;
        int toppingNum;
        double totalCost = 0;
        double totalTotal = 0;
        double pizzaArea;
        char customerSelection = ' ';
        boolean validTopping = false;
        ArrayList<Double> costs = new ArrayList<>();
        ArrayList<String> ToppingList = new ArrayList<>();
        ArrayList<String> PizzaNames = new ArrayList<>();
        ArrayList<String> ChosenToppings = new ArrayList<>();

        //Initialize all needed topping names
        ToppingList.add("Pepperoni");
        ToppingList.add("Mushroom");
        ToppingList.add("Chicken");
        ToppingList.add("Ham");
        ToppingList.add("Pineapple");
        ToppingList.add("Sausage");
        ToppingList.add("Basil");
        ToppingList.add("Olive");

        //Establish all needed constants
        final double CHEESE_COST_PSI = .0272;
        final double SAUCE_COST_PSI = .0316;
        final double DOUGH_COST_PSI = .0228;
        final double TOPPING_COST_PSI = .0284;

        //Prompt user for username
        System.out.println("Welcome to Adkins Pizzeria!");
        System.out.println("Can I have your name please?");
        customerName = scnr.nextLine();
        System.out.println();
        System.out.println("Ok, let's start your order, " + customerName + ".");


        //Continue this loop until user quits
        while (customerSelection != 'q')
        {
        //Prompt user for input action
        System.out.println("Here is what you can do next:");
        System.out.println("MENU");
        System.out.println("a - Add a pizza");
        System.out.println("t - print the total");
        System.out.println("r - read the order");
        System.out.println("q - quit");
        System.out.println("Please make a selection:");
        customerSelection = scnr.next().charAt(0);

        //Loop until user enters a valid option
        while (customerSelection != 'a' && customerSelection != 't' && customerSelection != 'r' && customerSelection != 'q')
        {
            System.out.println("Please input a proper selection:");
            //System.out.println("Here is what you can do next:");
            // System.out.println("MENU");
            // System.out.println("a - Add a pizza");
            // System.out.println("t - print the total");
            // System.out.println("r - read the order");
            // System.out.println("q - quit");
            // System.out.println("Please make a selection:");
            customerSelection = scnr.next().charAt(0);
        }

        //Display the total in the event of the user asking for the total
        if (customerSelection == 't')
        {
            totalTotal = 0;
            //Calculate the overall total based on the costs accumulated from past orders
            for (int i = 0; i < costs.size(); i++)
            {
                totalTotal += costs.get(i);
            }
            System.out.println();
            System.out.printf("Total $%.2f", totalTotal);
            System.out.println(".");
            System.out.println();
        }
        //Read the order out to the user if they ask for the order to be read.
        else if (customerSelection == 'r')
        {

            //Account for if the user asks to read the order before anything is ordered
            if (numPies == 0)
            {
                System.out.println("You have not ordered any pizzas yet.");
                System.out.println();
            }
            //List all ordered pizza names and the total cost.
            else
            {
                totalTotal = 0;
                for (int i = 0; i < costs.size(); i++)
                {
                totalTotal += costs.get(i);
                }
                System.out.println();
                System.out.println("You ordered the following pizzas:");
                for (int i = 0; i < numPies; i++)
                {
                    if ( i != numPies - 1)
                    {
                        System.out.print(PizzaNames.get(i) + ", ");
                    }
                    else
                    {
                        System.out.println(PizzaNames.get(i));
                    }
                }
                System.out.println();

                //Change the grammar if there is only one pizza versus many
                if (numPies != 1)
                {
                    System.out.printf("Ok, %s your total for your %d pizzas will come out to $%.2f.\n", customerName, numPies, totalTotal);
                }
                else
                {
                    System.out.printf("Ok, %s your total for your 1 pizza will come out to $%.2f.\n", customerName, totalTotal);
                }
            }

        }
        //Add a pizza to the order when the user asks to
        else if (customerSelection == 'a')
        {
            //reset all appropriate variables and prompt the user for the pizza name
            ChosenToppings.clear();
            totalCost = 0;
            numPies++;
            System.out.println();
            System.out.println("What would you like to name pizza number " + numPies + "?");
            scnr.nextLine();
            PizzaNames.add(scnr.nextLine());
            System.out.println();

            //Prompt the user for the size of the pizza
            System.out.println("What size would you like " + PizzaNames.get(numPies - 1) + " to be?");
            System.out.println("We can handle pizzas between 8 and 48 inches.");
            pizzaSize = scnr.nextInt();
            System.out.println();

            //Loop until the user enters a valid pizza size 
            while ( pizzaSize < 8 || pizzaSize > 48 )
            {
                System.out.println("Please order a pizza between 8 and 48 inches.");
                pizzaSize = scnr.nextInt();
            }

            //Caclulate the cost of the pizza before toppings
            pizzaArea = Math.pow(pizzaSize / 2.0, 2) * Math.PI;
            totalCost += (pizzaArea * (CHEESE_COST_PSI + SAUCE_COST_PSI + DOUGH_COST_PSI) );

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

            //Calculate the total cost of this pizza with the toppings in question
            totalCost += (pizzaArea * TOPPING_COST_PSI * toppingNum);

            //Add the cost of this pizza to the total cost overall
            costs.add(totalCost);

            for (int i = 0; i < toppingNum; i++)
            {
                //Prompt the user for each topping in accordance to the number of toppings they specified
                validTopping = false;
                System.out.println();
                System.out.println("What topping would you like?");
                System.out.println("Your options are: Pepperoni, Mushroom, Chicken, Ham, Pineapple, Sausage, Basil, Olive.");
                toppingName = scnr.next();

                //Check the topping name for comparison with topping options
                for (int j = 0; j < ToppingList.size(); j++)
                {

                    if (toppingName.equalsIgnoreCase(ToppingList.get(j)))
                    {
                        validTopping = true;
                        ChosenToppings.add(toppingName);
                        break;
                    }
                    
                }
                
                //Loop until a valid topping name is entered
                while (!validTopping)
                {
                    System.out.println("Chosen topping is not valid. Please input a different topping:");
                    scnr.nextLine();
                    toppingName = scnr.next();

                    for (int j = 0; j < ToppingList.size(); j++)
                {

                    if (toppingName.equalsIgnoreCase(ToppingList.get(j)))
                    {
                        validTopping = true;
                        ChosenToppings.add(toppingName);
                        break;
                    }
                    
                }
                }

                System.out.println("Chosen topping " + toppingName + " added.");
            }

            //List the topping selection back to the user
            System.out.println();
            if (toppingNum != 0)
            {
                System.out.println("You have ordered " + PizzaNames.get(numPies - 1) + " with toppings: ");
            }
            else
            {
                System.out.println("You have ordered " + PizzaNames.get(numPies - 1) + " with no toppings.");
            }

            for (int i = 0; i < toppingNum; i++)
            {
                if ( i != toppingNum - 1)
                    {
                        System.out.print(ChosenToppings.get(i) + ", ");
                    }
                    else
                    {
                        System.out.println(ChosenToppings.get(i) + ".");
                    }
            }

            //List the total cost of the pizza back to the user
            System.out.printf("This pizza costs: $%.2f", totalCost);
            System.out.print(".");
            System.out.println();

        }
       

    }

    }
}