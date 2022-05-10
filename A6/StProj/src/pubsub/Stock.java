package pubsub;

import java.io.Serializable;

public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;
    private String nameOfCompany;
    private Integer globalId, price, amount, traderId;

    public Stock(String name, Integer gid, Integer price, Integer amount, Integer tid) {
        this.nameOfCompany = name;
        this.globalId = gid;
        this.price = price;
        this.amount = amount;
        this.traderId = tid;
    }

    public String toString() {
        return "Name of Company: " + nameOfCompany + "\n "
        		+ "Global Id: " + globalId + "\n"
        		+ "Price: " + price + "\n"
        		+ "Amount: " + amount + "\n"
        		+ "Trader Id: " + traderId + "\n";
    }
}
