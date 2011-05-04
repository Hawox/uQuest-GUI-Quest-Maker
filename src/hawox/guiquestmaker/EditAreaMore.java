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

	String[] objectiveTypes = {"None", "Gather Blocks/Items", "Destroy Blocks", "Punch Blocks", "Place Blocks", "Kill Creatures", "Move"};
	String[] rewardTypes = {"Money", "Item"};
	JLabel title;
	NameComboBoxPanel objType;
	NameComboBoxPanel rewardType;
	NameTextPanel objID;
	NameTextPanel objDisplay;
	NameTextPanel amount;
	
	//the reward or objective. Referenced as an object so it can be either.
	Object savedInfo;
	
	
	JButton doneButton;
	
	Color textColor = new Color(255,255,255);
	
	EditAreaMore(Color backgroundColor){
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
	
	public void setUpObjective(Objective obj){
		this.savedInfo = obj;
		this.removeAll();
		setType(TYPE_OBJECTIVE);
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
