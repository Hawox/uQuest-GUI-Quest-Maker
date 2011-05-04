package hawox.guiquestmaker;

import java.awt.Color;

import javax.swing.BoxLayout;
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
		setType(EditAreaMore.TYPE_OBJECTIVE);

		this.setVisible(true);
		this.validate();
		this.repaint();
	}
	
	public void setType(int type){
		this.removeAll();
		this.add(title);
		if(type == EditAreaMore.TYPE_NONE){
			this.setTitle("None");
		}else
		if(type == EditAreaMore.TYPE_OBJECTIVE){
			this.add(objType);
			this.setTitle("Objective");
		}else
		if(type == EditAreaMore.TYPE_OBJECTIVE){
			this.add(rewardType);
			this.setTitle("Reward");
		}
		
		this.validate();
		this.repaint();
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
