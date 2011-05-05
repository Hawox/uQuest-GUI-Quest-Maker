package hawox.guiquestmaker;

public class Item {
	String id;
	String display;
	String amount;
	String durability;
	
	Item(String id, String display, String amount, String durability){
		this.display = display;
		this.id = id;
		this.amount = amount;
		this.durability = durability;
	}
}
