/*
    James Maner
    CPSC 1060: Project Part 5
    Class Section 020
    4/20/2023
*/
public class Customer {
    private String name;
    private boolean isRewardsMember;
    private double rewardsPoints;
    private String phoneNumber;
    private String email;

    /**
     * Description- Constructor for the customer object that initializes rewards member to false
     * @param name- the name of the customer
     */
    public Customer(String name) {
        this.name = name;
        this.isRewardsMember = false;
        this.rewardsPoints = 0;
    }

    /**
     * Description- Constructor for the customer object that takes in all information for rewards member,
     * initializes rewardsPoints to 20
     * @param name- the name of the customer
     * @param phoneNumber- the phone number of the customer
     * @param email- the email of the customer
     */
    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.isRewardsMember = true;
        this.rewardsPoints = 20;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRewardsMember() {
        return isRewardsMember;
    }

    public void setRewardsMember(boolean isRewardsMember) {
        this.isRewardsMember = isRewardsMember;
    }

    public double getRewardsPoints() {
        return rewardsPoints;
    }

    public void setRewardsPoints(double rewardsPoints) {
        this.rewardsPoints = rewardsPoints;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Description- adds rewards points to the customer
     * @param total- the total of a given order, specifically the subtotal of all of the pizzas
     */
    public void addRewardsPoints(double total) {
        this.rewardsPoints = (double) Math.round((this.rewardsPoints + (total * 0.2)) * 100) / 100;
    }

    /**
     * Description- removes rewards points from the customer
     * @param points- the amount of points the customer will use
     * points must be less than order total
     */
    public void useRewardsPoints(double points) {
        this.rewardsPoints -= points;
    }
}
