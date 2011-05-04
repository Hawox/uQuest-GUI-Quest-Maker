package hawox.guiquestmaker;

public class Objective {
	String type;
	String display;
	String id;
	int amount;
	
	//only used for a gather quest
	Item item;
	
	Objective(String type, String display, String id, int amount){
		this.type = type;
		this.display = display;
		this.id = id;
		this.amount = amount;
	}
	Objective(String type, Item item){
		this.type = type;
		this.item = item;
	}
	
	//What the combobox sees
	public String toString(){
		return this.display;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}
