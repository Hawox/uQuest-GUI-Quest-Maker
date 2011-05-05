package hawox.guiquestmaker;

public class Reward {
	String type;
	
	//for money
	String amount;
	
	//item
	Item item;
	
	Reward(String type, Item item){
		this.type = type;
		this.item = item;
	}
	
	Reward(String type, String amount){
		this.type = type;
		this.amount = amount;
	}

	//What the combobox sees
	public String toString(){
		return this.type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
