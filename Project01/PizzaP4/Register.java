/*
    James Maner
    CPSC 1060: Project Part 4
    Class Section 020
    4/18/2023
*/
import java.util.ArrayList;

public class Register {
    //Declare all needed private variables
    private ArrayList<Receipt> recList;
    private double totalPizzaArea = 0;
    private double totalPepperoniArea;
    private double totalMushroomArea;
    private double totalChickenArea;
    private double totalHamArea;
    private double totalPineappleArea;
    private double totalSausageArea;
    private double totalBasilArea;
    private double totalOliveArea;
    private double totalTax;
    private double totalTips;
    private double totalRev;
    private double totalGoods;

    /**
     * Description- Constructor for the register object that initializes the array list of receipts
     */
    public Register()
    {
        recList = new ArrayList<>();
    }

    /**
     * Description- method to add a receipt's information to the register
     * @param rec- the receipt being added to the register
     */
    public void addReceipt(Receipt rec)
    {
        recList.add(rec);
    }

    /**
     * Description- Method to calculate all of the total information needed by the register at the end of the program
     */
    public void calcTotalAreas()
    {
        //Reset all variables to 0 for potential repeat use of this method
        totalPizzaArea = 0;
        totalPepperoniArea = 0;
        totalMushroomArea = 0;
        totalChickenArea = 0;
        totalHamArea = 0;
        totalPineappleArea = 0;
        totalSausageArea = 0;
        totalBasilArea = 0;
        totalOliveArea = 0;
        totalTax = 0;
        totalTips = 0;
        totalRev = 0;
        totalGoods = 0;

        //Iterate through every receipt stored in the register and add the needed information
        for (Receipt rec: recList)
        {
            totalGoods += rec.subTotal();
            totalTax += rec.getTaxAmount();
            totalTips += rec.getTipAmount();
            totalRev += (rec.subTotal() / 10.0);

            //Iterate through every pizza of this receipt and add all the needed information
            for (Pizza pie: rec.getPizzas())
            {
                totalPizzaArea += pie.getArea();

                //Iterate through each topping of this pizza and update the areas of each accordingly
                for (Topping top: pie.getToppings())
                {
                    if (top.getName().equalsIgnoreCase("Pepperoni"))
                    {
                        totalPepperoniArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Mushroom"))
                    {
                        totalMushroomArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Chicken"))
                    {
                        totalChickenArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Ham"))
                    {
                        totalHamArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Pineapple"))
                    {
                        totalPineappleArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Sausage"))
                    {
                        totalSausageArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Basil"))
                    {
                        totalBasilArea += pie.getArea();
                    }
                    else if (top.getName().equalsIgnoreCase("Olive"))
                    {
                        totalOliveArea += pie.getArea();
                    }
                }
            }
        }
    }

    /**
     * Description- Print all of the info relevant to the closing of the register at the end of the day
     */
    public void printInfo()
    {
        this.calcTotalAreas();
        System.out.println("Amount of materials used for today (per square inch):");
        System.out.printf("Sauce/Cheese/Dough: %.2f\n", totalPizzaArea);
        System.out.printf("Pepperoni: %.2f\n", totalPepperoniArea);
        System.out.printf("Mushroom: %.2f\n", totalMushroomArea);
        System.out.printf("Chicken: %.2f\n", totalChickenArea);
        System.out.printf("Ham: %.2f\n", totalHamArea);
        System.out.printf("Pineapple: %.2f\n", totalPineappleArea);
        System.out.printf("Sausage: %.2f\n", totalSausageArea);
        System.out.printf("Basil: %.2f\n", totalBasilArea);
        System.out.printf("Olive: %.2f\n", totalOliveArea);
        System.out.println("Financial data for today:\n");
        System.out.printf("Total Amount of Goods Sold: $%.2f\n", totalGoods);
        System.out.printf("Tip Money Today: $%.2f\n", totalTips);
        System.out.printf("Taxes Paid Today: $%.2f\n", totalTax);
        System.out.printf("Revenue Made Today: $%.2f\n", totalRev);
        System.out.println("Adkins Pizzeria is now closed!");
    }
}
