/*
    James Maner
    CPSC 1060: Project Part 5
    Class Section 020
    4/20/2023
*/

public class Pizza {
    //Declare all needed private fields
    private int diameter;
    private Topping[] toppings;
    private String name;
    private double area;
    private double pizzaCost;

    //Initialize all needed constants
    final double cheeseCostPerSquareInch = .0272;
    final double sauceCostPerSquareInch = .0316;
    final double doughCostPerSquareInch = .0228;

    /*
        @param diameter - the size of the pizza
        @param toppings - an array of all toppings on the pizza
        @param name - the name of the pizza
        @return void - return nothing
    */
    //Constructor to initialize all data about the pizza in question
    public Pizza(int diameter, Topping[] toppings, String name) {
        this.diameter = diameter;
        this.toppings = toppings;
        this.name = name;
        double radius = diameter / 2.0;
        this.area = (Math.PI * Math.pow(radius, 2));
    }

    //Getter method to return the size of the pizza
    public int getSize()
    {
        return this.diameter;
    }

    //Getter method to return the array of toppings 
    public Topping[] getToppings()
    {
        return this.toppings;
    }

    //Getter method to return the area of the pizza
    public double getArea()
    {
        return this.area;
    }

    //Getter method to return the name of the pizza
    public String getName()
    {
        return this.name;
    }

    //Printing method used to make printing all of the toppings more convenient
    public String printToppings()
    {
        String toppingString = "";
        //No toppings will be printed if there are no toppings
        if (this.toppings.length == 0)
        {
            toppingString = "NONE.";
        }
        else
        {
            //Print each topping with commas and spaces between them 
            for (int i = 0; i < this.toppings.length; i++)
            {
                if (i < this.toppings.length - 1)
                {
                    toppingString += this.toppings[i].getName() + ", ";
                }
                else 
                {
                    toppingString += this.toppings[i].getName() + ".";
                }
            }
        }

        return toppingString;
    }

    //Getter method to calculate and return the cost of the pizza object
    public double getCost()
    {
        //Calculate the cost of the pizza and add the cost of the toppings on the pizza
        this.pizzaCost = 0;
        this.pizzaCost += (this.area * cheeseCostPerSquareInch) + (this.area * sauceCostPerSquareInch) + (this.area * doughCostPerSquareInch);
        for (Topping top: toppings)
        {
            this.pizzaCost += (top.getCost() * this.area);
        }
        //Round the cost according to the standards of the project
        this.pizzaCost = (double) Math.round((this.pizzaCost) * 100) / 100;
        return this.pizzaCost;
    }

    //Printing method to print the individual information of the pizza
    public void printInfo()
    {
        System.out.print("You have ordered " + this.name + " with toppings: ");
        if (this.toppings.length == 0)
        {
            System.out.println("NONE.");
        }
        else
        {
            for (int i = 0; i < this.toppings.length; i++)
            {
                if (i < this.toppings.length - 1)
                {
                    System.out.print(toppings[i].getName() + ", ");
                }
                else 
                {
                    System.out.println(toppings[i].getName() + ".");
                }
            }
        }

        System.out.printf("This pizza costs $%.2f", this.getCost());
        System.out.println(".");
    }

}
