package hawox.guiquestmaker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EditAreaMore extends JPanel{
	static final int TYPE_NONE = 0;
	static final int TYPE_OBJECTIVE = 1;
	static final int TYPE_REWARD = 2;
	GUI gui;

	String[] objectiveTypes = {"None", "Gather", "Block_Destroy", "Block_Damage", "Block_Place", "Kill", "Move"};
	String[] rewardTypes = {"Money", "Item"};
	JLabel title;
	//objectives
	NameComboBoxPanel objType;
	NameTextPanel objID;
	NameTextPanel objDisplay;
	
	//rewards
	NameComboBoxPanel rewardType;
	
	//shared
	NameTextPanel amount;
	
	
	
	
	//item stuff, I want to convert some of these to comboboxs someday
	NameTextPanel itemAmount;
	NameTextPanel itemID;
	NameTextPanel itemDisplayName;
	NameTextPanel itemDurability;

	
	//the reward or objective. Referenced as an object so it can be either.
	StoredInfo savedInfo;
	
	
	JButton doneButton;
	
	Color textColor = new Color(255,255,255);
	
	EditAreaMore(Color backgroundColor,GUI gui){
		this.gui = gui;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		backgroundColor = new Color(0,0,0);
		this.setBackground(backgroundColor);
		objType = new NameComboBoxPanel("Type",objectiveTypes,backgroundColor);
		objType.getLabel().setForeground(textColor);
		rewardType = new NameComboBoxPanel("Type",rewardTypes,backgroundColor);
		rewardType.getLabel().setForeground(textColor);
		title = new JLabel("None");
		title.setForeground(textColor);
		doneButton = new JButton("Done Editing");
		doneButton.addActionListener(new DoneButtonListener());
//		objType.getComboBox().addActionListener(new ComboBoxListener());
//		rewardType.getComboBox().addActionListener(new ComboBoxListener());
//		setType(EditAreaMore.TYPE_OBJECTIVE);
		
		itemID = new NameTextPanel("Item ID", backgroundColor);
		itemID.getLabel().setForeground(textColor);
		itemAmount = new NameTextPanel("Item Amount", backgroundColor);
		itemAmount.getLabel().setForeground(textColor);
		itemDisplayName = new NameTextPanel("Item Display Name", backgroundColor);
		itemDisplayName.getLabel().setForeground(textColor);
		itemDurability = new NameTextPanel("Item Durability", backgroundColor);
		itemDurability.getLabel().setForeground(textColor);
		
		
		objID = new NameTextPanel("Objective ID", backgroundColor);
		objID.getLabel().setForeground(textColor);
		objDisplay = new NameTextPanel("Display Name", backgroundColor);
		objDisplay.getLabel().setForeground(textColor);
		amount = new NameTextPanel("Amount", backgroundColor);
		amount.getLabel().setForeground(textColor);


		this.setType(TYPE_NONE);
		this.setVisible(true);
		this.validate();
		this.repaint();
	}
	
	public void setUpObjective(Objective obj, int arrayID){
		setType(TYPE_OBJECTIVE);
		this.savedInfo = new StoredInfo(1,arrayID,obj);
		this.add(objType);
		objType.getComboBox().setSelectedItem(obj.getType());
		//gather is setup differently
		if(obj.getType().equalsIgnoreCase("gather")){
			try{
				itemAmount.getText().setText(obj.getItem().amount);
				itemID.getText().setText(obj.getItem().id);
				itemDisplayName.getText().setText(obj.getItem().display);
				itemDurability.getText().setText(obj.getItem().durability);
			}catch(NullPointerException npe){
				//changed to a gather quest so these will be broken. I'll find a fix for this later.
			}
			this.add(itemID);
			this.add(itemDisplayName);
			this.add(itemAmount);
			this.add(itemDurability);
		}else{
			//everything else shares the same nodes
			objID.getText().setText(obj.getId());
			objDisplay.getText().setText(obj.getDisplay());
			amount.getText().setText(obj.getAmount());
			this.add(objID);
			this.add(objDisplay);
			this.add(amount);
		}
		
		this.add(doneButton);
		this.validate();
		this.repaint();
	}
	public void setUpReward(Reward r, int arrayID){
		setType(TYPE_REWARD);
		this.savedInfo = new StoredInfo(2,arrayID,r);
		this.add(rewardType);
		rewardType.getComboBox().setSelectedItem(r.getType());
		if(r.getType().equalsIgnoreCase("money")){
			amount.getText().setText(r.getAmount());
			this.add(amount);
		}else
		if(r.getType().equalsIgnoreCase("item")){
			//item
			try{
				itemAmount.getText().setText(r.getItem().amount);
				itemID.getText().setText(r.getItem().id);
				itemDisplayName.getText().setText(r.getItem().display);
				itemDurability.getText().setText(r.getItem().durability);
			}catch(NullPointerException npe){
				//changed to an item reward so these will be broken. I'll find a fix for this later.
			}
			this.add(itemID);
			this.add(itemDisplayName);
			this.add(itemAmount);
			this.add(itemDurability);
		} 
		this.add(doneButton);
		this.validate();
		this.repaint();

	}
	
	public void setType(int type){
		//Clean up info from past edits
		this.savedInfo = null;
		this.removeAll();
		this.add(title);
		if(type == EditAreaMore.TYPE_NONE){
			this.setTitle("None");
		}else
		if(type == EditAreaMore.TYPE_OBJECTIVE){
			this.setTitle("Objective");
		}else
		if(type == EditAreaMore.TYPE_REWARD){
			this.setTitle("Reward");
		}
		
		this.validate();
		this.repaint();
	}
	
	public class DoneButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(savedInfo.type == StoredInfo.OBJECTIVE){
				Objective obj = (Objective) savedInfo.storedObject;
				obj.setType((String)objType.getComboBox().getSelectedItem());
				//gather is different from the others
				if(obj.getType().equalsIgnoreCase("gather")){
					try{
						Item theItem = new Item(itemID.getText().getText(),itemDisplayName.getText().getText(),itemAmount.getText().getText(),itemDurability.getText().getText());
						obj.setDisplay(itemDisplayName.getText().getText());
						obj.setItem(theItem);
						gui.getObjectives().set(savedInfo.id, obj);
					}catch(NullPointerException npe){
						//Don't save it because it was converted to a gather from something. Find a fix for this later.
					}

				}else{
					obj.setAmount(amount.getText().getText());
					obj.setDisplay(objDisplay.getText().getText());
					obj.setId(objID.getText().getText());
					gui.getObjectives().set(savedInfo.id, obj);
				}
			}else
			if(savedInfo.type == StoredInfo.REWARD){
				Reward r = (Reward) savedInfo.storedObject;
				r.setType((String)rewardType.getComboBox().getSelectedItem());
				if(r.getType().equalsIgnoreCase("money")){
					r.setAmount(amount.getText().getText());
					gui.getRewards().set(savedInfo.id, r);
				}else
				if(r.getType().equalsIgnoreCase("item")){
					try{
						Item theItem = new Item(itemID.getText().getText(),itemDisplayName.getText().getText(),itemAmount.getText().getText(),itemDurability.getText().getText());
						r.setItem(theItem);
						gui.getRewards().set(savedInfo.id, r);
					}catch(NullPointerException npe){
						//Don't save it because it was converted to a item from something. Find a fix for this later.
					}
				}
			
			}
			gui.updateTheArrays();
			setType(EditAreaMore.TYPE_NONE);
		}
	}
	
	/* This might be running when the edit area is made... Causing issues with it's creating. Disabled for now... 
	  public class ComboBoxListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	if(savedInfo.storedObject instanceof Reward){
	    		setUpObjective((Objective) savedInfo.storedObject,savedInfo.id);
	    	}else
	    	if(savedInfo.storedObject instanceof Object){
	    		setUpReward((Reward) savedInfo.storedObject,savedInfo.id);
	    	}
	    }
	}*/
	
	//Where we'll store the object we are editing
	public class StoredInfo{
		//Where?
		static final int OBJECTIVE = 1;
		static final int REWARD = 2;
		int type;
		
		Object storedObject;
		
		//what array id?
		int id;
		StoredInfo(int type, int array, Object obj){
			this.type = type;
			this.id = array;
			this.storedObject = obj;
		}
	}

	
	//getters and setters
	public String[] getObjectiveTypes() {
		return objectiveTypes;
	}

	public void setObjectiveTypes(String[] objectiveTypes) {
		this.objectiveTypes = objectiveTypes;
	}

	public String[] getRewardTypes() {
		return rewardTypes;
	}

	public void setRewardTypes(String[] rewardTypes) {
		this.rewardTypes = rewardTypes;
	}

	public String getTitle() {
		return title.getText();
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public NameComboBoxPanel getObjType() {
		return objType;
	}

	public void setObjType(NameComboBoxPanel objType) {
		this.objType = objType;
	}

	public NameComboBoxPanel getRewardType() {
		return rewardType;
	}

	public void setRewardType(NameComboBoxPanel rewardType) {
		this.rewardType = rewardType;
	}

}
