/*
    James Maner
    CPSC 1060: Project Part 3
    Class Section 020
    3/29/2023
*/
import java.util.ArrayList;

public class Topping {
    //Declare all needed private fields
    private String name;
    private double costPerInch;
    private ArrayList<String> toppingTypes = new ArrayList<String>();
    private double[] toppingCosts = {0.047, 0.005, 0.01, 0.03, 0.04, 0.052, 0.032, 0.002};

    /*
        @param name - the name of the topping being created to add to a pizza
        @return void - return nothing
    */
    //A constructor method to create a topping for a pizza
    public Topping(String name) {
        this.name = name;
        //Populate the toppingTypes arrayList
        this.toppingTypes.add("Pepperoni");
        this.toppingTypes.add("Mushroom");
        this.toppingTypes.add("Chicken");
        this.toppingTypes.add("Ham");
        this.toppingTypes.add("Pineapple");
        this.toppingTypes.add("Sausage");
        this.toppingTypes.add("Basil");
        this.toppingTypes.add("Olive");

        //Assign the topping object with a price based on its name and the corresponding price
        for (int i = 0; i < toppingTypes.size(); i++)
        {
            if (this.name.equalsIgnoreCase(toppingTypes.get(i)))
            {
                this.costPerInch = toppingCosts[i];
            }
        }
    }

    //Validate a topping by comparing it to the list of topping options
    public boolean validTopping()
    {
        boolean valid = false;
        for (String type: toppingTypes)
        {
            if (this.name.equalsIgnoreCase(type))
            {
                valid = true;
            }
        }

        return valid;
    }

    /*
        @param name - the new name of the topping being created to add to a pizza
        @return void - return nothing
    */
    //Change the name of the topping if the first name was not a valid option
    public void setName(String name)
    {
        this.name = name;
        for (int i = 0; i < toppingTypes.size(); i++)
        {
            if (this.name.equalsIgnoreCase(toppingTypes.get(i)))
            {
                this.costPerInch = toppingCosts[i];
            }
        }
    }

    //Getter method that returns the name of the topping object
    public String getName()
    {
        return this.name;
    }

    //Getter method that returns the cost of the topping object
    public double getCost()
    {
        return this.costPerInch;
    }
}
