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
	NameComboBoxPanel objType;
	NameComboBoxPanel rewardType;
	NameTextPanel objID;
	NameTextPanel objDisplay;
	NameTextPanel amount;
	
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
//		setType(EditAreaMore.TYPE_OBJECTIVE);
		
		objID = new NameTextPanel("Objective ID", backgroundColor);
		objID.getLabel().setForeground(textColor);
		objDisplay = new NameTextPanel("Display Name", backgroundColor);
		objDisplay.getLabel().setForeground(textColor);
		amount = new NameTextPanel("Amount", backgroundColor);
		amount.getLabel().setForeground(textColor);


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
			
		}else{
			//everything else shares the same nodes
			this.add(objID);
			this.add(objDisplay);
			this.add(amount);
		}
		
		this.add(doneButton);
		this.validate();
		this.repaint();
	}
	public void setUpReward(Reward r){
		setType(TYPE_REWARD);
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
			this.add(rewardType);
			this.setTitle("Reward");
		}
		
		this.validate();
		this.repaint();
	}
	
	public class DoneButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(savedInfo.type == StoredInfo.OBJECTIVE){
				Objective obj = (Objective) savedInfo.storedObject;
				//gather is different from the others
				if(obj.getType().equalsIgnoreCase("gather")){
					
				}else{
					obj.setType((String)objType.getComboBox().getSelectedItem());
					obj.setAmount(amount.getText().getText());
					obj.setDisplay(objDisplay.getText().getText());
					obj.setId(objID.getText().getText());
					gui.getObjectives().set(savedInfo.id, obj);
				}
			}
			setType(EditAreaMore.TYPE_NONE);
		}
	}
	/*	NameTextPanel objID;
	NameTextPanel objDisplay;
	NameTextPanel amount;*/
	
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
