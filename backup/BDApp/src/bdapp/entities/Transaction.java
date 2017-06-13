package bdapp.entities;

public class Transaction {

    public int id;
    public String buyer;
    public String seller;
    public String date;
    public String state;
    

    public Transaction(int id, String buyer, String seller, String date, String state) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.date = date;
        this.state = state;
    }
}
