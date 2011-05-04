package hawox.guiquestmaker;

public class Item {
	int id;
	String display;
	int amount;
	int durability;
	
	Item(int id, String display, int amount, int durability){
		this.display = display;
		this.id = id;
		this.amount = amount;
		this.durability = durability;
	}
}
