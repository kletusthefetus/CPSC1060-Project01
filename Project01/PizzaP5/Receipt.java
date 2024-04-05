/*
    James Maner
    CPSC 1060: Project Part 5
    Class Section 020
    4/20/2023
*/
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Receipt {
    //Declare all relevant private fields
    private Customer cus;
    private ArrayList<Pizza> pizzas;
    private boolean usingRewards = false;
    private int tipPercent;
    private double subTotal;
    
    //Initialize constants
    private final double salesTax = 0.06; 
    
    /*
        @param cusname - the name of the customer the receipt object is created for
        @return void - return nothing
    */
    //Constructor method to assign the name of the customer and initialize the arraylist of pizzas
    public Receipt(Customer cus) {
        pizzas = new ArrayList<>();
        this.cus = cus;
    }

    /*
        @param tipPercent - the percentage of extra money given as a tip
        @return void - return nothing
    */
    public void setTip(int tipPercent)
    {
        this.tipPercent = tipPercent;
    }

    /**
     * Description- method to change the state of the user using their reward points
     * @param usingRewards- if the user is using their rewards, this will be true
     */
    public void setUsingPoints(boolean usingRewards)
    {
        this.usingRewards = usingRewards;
    }

    /**
     * Description- Getter method to return true if the user is using their reward points
     */
    public boolean getUsingPoints()
    {
        return this.usingRewards;
    }

    /**
     * Description- Returns all the pizzas in this receipt
     */
    public ArrayList<Pizza> getPizzas()
    {
        return this.pizzas;
    }

    //Calculate the amount of the cost which comes from tax
    public double getTaxAmount()
    {
        //Calculate the subtotal without rounding
        this.subTotal = 0;
        for (Pizza pizza: this.pizzas)
        {
            this.subTotal += pizza.getCost();
        }

        //Round the product according to the standards of the project
        return (double) Math.round(this.subTotal * salesTax * 100) / 100;
    }

    //Calculate the amound of the cost which comes from tipping
    public double getTipAmount()
    {
        //Calculate the subtotal without rounding
        this.subTotal = 0;
        for (Pizza pizza: this.pizzas)
        {
            this.subTotal += pizza.getCost();
        }
        double tipAmount = this.tipPercent / 100.0;

        //Round the product according to the standards of the project
        return (double) Math.round(this.subTotal * tipAmount* 100) / 100;
    }

    //Adds a pizza to the list of pizzas on the receipt
    public void addPizza(Pizza newPizza)
    {
        this.pizzas.add(newPizza);
    }

    //Calculate the subtotal of all the pizzas combined
    public double subTotal()
    {
        this.subTotal = 0;
        for (Pizza pizza: this.pizzas)
        {
            subTotal += pizza.getCost();
        }
        return (double) Math.round(subTotal * 100) / 100;
    }

    //Combine the subtotal, the tax amount, and the tip amount
    public double getTotal()
    {
        double total = this.subTotal() + this.getTipAmount() + this.getTaxAmount();
        return total;
    }

    public double rewardPoints()
    {
        double points = 0;
        if (usingRewards)
        {
            if (this.getTotal() < cus.getRewardsPoints())
            {
                return this.getTotal();
            }
            else
            {
                points = cus.getRewardsPoints();
            }
        }

        return points;
    }

    //Print all of the information for the customer's receipt
    public void printInfo(PrintWriter printWork)
    {
        double cusSubTotal = this.subTotal();
        printWork.println("Receipt for customer: " + cus.getName());
        printWork.println();
        int pizzaNum = 0;
        for (Pizza pie: pizzas)
        {
            printWork.println("Pizza: " + ++pizzaNum);
            printWork.println("\tName: " + pie.getName());
            printWork.println("\tSize: " + pie.getSize());
            printWork.print("\t  Toppings: " + pie.printToppings());
            printWork.println();
            printWork.printf("\tTotal: $%.2f\n", pie.getCost());
        }
        printWork.printf("Subtotal: $%.2f\n", this.subTotal());
        printWork.print("Tax(6%): ");
        printWork.printf("$%.2f\n", this.getTaxAmount());
        printWork.printf("Tip(%d", tipPercent);
        printWork.print("%): $");
        printWork.printf("%.2f\n", this.getTipAmount());
        if (usingRewards)
        {
            printWork.printf("Rewards Points Used: %.2f\n", this.rewardPoints());
        }
        printWork.printf("Total: $%.2f\n", this.getTotal() - this.rewardPoints());
        cus.useRewardsPoints(this.rewardPoints());
    }
}
