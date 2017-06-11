package bdapp.entities;

public class Offer {

    public int wareID;
    public String wareName;
    public double wareAmount;
    public double wareCostPerOne;
    public String wareCategory;

    public Offer(int wareID, String wareName, double wareAmount, double wareCostPerOne, String wareCategory) {
        this.wareID = wareID;
        this.wareName = wareName;
        this.wareAmount = wareAmount;
        this.wareCostPerOne = wareCostPerOne;
        this.wareCategory = wareCategory;
    }
}
