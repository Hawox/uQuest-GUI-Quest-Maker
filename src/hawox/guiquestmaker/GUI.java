package hawox.guiquestmaker;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI {
	
	//applet stuff
	public boolean app = false;
	AppletLauncher appFrame;
	
	//Elements
	JFrame frame;
	DrawPanel editArea;
		NameTextPanel questName;
		NameTextPanel startInfo;
		NameTextPanel finishInfo;
		NameBoxWithButton objectivesPanel;
			ArrayList<Objective> objectives = new ArrayList<Objective>();
		NameBoxWithButton rewardsPanel;
			ArrayList<Reward> rewards = new ArrayList<Reward>();

		EditAreaMore editAreaMore;
	
	JPanel toolBar;
		JButton clearAllButton;
		JButton compileButton;
		
	QuestTextArea questTextArea;
	
	//Initial setup
	public void go(){
		//Make the frame
		frame = new JFrame("uQuest Quest Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);
		
		startUpGui(false);
	}
	
	//Initial setup if we're using an applet!
	public void go(AppletLauncher jframe){
		appFrame = jframe;
		app = true;
		
		startUpGui(true);
	}
	
	
	public void startUpGui(Boolean usingApplet){
		//added in again so it clears with the clear button! =D
		rewards = new ArrayList<Reward>();
		objectives = new ArrayList<Objective>();
		
		//add in a blank obj and reward to stop nulls
//		objectives[0] = new Objective("Kill","Pigs Killed","Pig",1);
//		rewards[0] = new Reward("Money", 10);
//		this.updateTheArrays();
		this.getRewards().add(new Reward("money","10"));
		this.getObjectives().add(new Objective("Kill","Things Killed","kill_any", "1"));
		
		editArea = new DrawPanel();
		editArea.setLayout(new BoxLayout(editArea,  BoxLayout.Y_AXIS));
			questName = new NameTextPanel("Quest Name:",editArea.getBackground());
			startInfo = new NameTextPanel("Start Text:",editArea.getBackground());
			finishInfo = new NameTextPanel("Finish Text:",editArea.getBackground());
//			objectivesPanel = new NameComboBoxPanel("Objectives:", objectives, editArea.getBackground());
//			addObjective = new JButton("Add Objective");
//			rewardsPanel = new NameComboBoxPanel("Rewards:", rewards, editArea.getBackground());
//			addReward = new JButton("Add Reward");
			objectivesPanel = new NameBoxWithButton("Objectives:", objectives.toArray(), editArea.getBackground(), "Add Objective", 0,this);
			rewardsPanel = new NameBoxWithButton("Rewards:", rewards.toArray(), editArea.getBackground(), "Add Reward", 1,this);
			editAreaMore = new EditAreaMore(editArea.getBackground(),this);
			editArea.add(questName);
			editArea.add(startInfo);
			editArea.add(finishInfo);
			editArea.add(objectivesPanel);
			editArea.add(rewardsPanel);
			editArea.add(BorderLayout.SOUTH, editAreaMore);
		
		toolBar = new JPanel();
			toolBar.setLayout(new BorderLayout());
			clearAllButton = new JButton("Clear All");
			toolBar.add(BorderLayout.WEST,clearAllButton);
			compileButton = new JButton("Compile");
			toolBar.add(BorderLayout.EAST,compileButton);
			
		questTextArea = new QuestTextArea(editArea.getBackground());
	
		//listeners
		clearAllButton.addActionListener(new ClearAllButtonListener());
		compileButton.addActionListener(new CompileButtonListener());
	
		//frame setup
		frame.getContentPane().add(BorderLayout.NORTH, toolBar);
		frame.getContentPane().add(BorderLayout.CENTER, editArea);
		frame.getContentPane().add(BorderLayout.EAST, questTextArea);
		frame.setVisible(true);
		
		//Add to our appframe instead
		if(app){
			appFrame.getContentPane().add(BorderLayout.NORTH, toolBar);
			appFrame.getContentPane().add(BorderLayout.CENTER, editArea);
			appFrame.getContentPane().add(BorderLayout.EAST, questTextArea);
			appFrame.setVisible(true);
		}
		update();
	}
	
	public class ClearAllButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//Damn it's not that simple. Validating the frame is not enough... :< I'll fix this later. It's okay for a test build.
			startUpGui(false);
		}
	}
	public class CompileButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String output = "#:";
			output += "\n  Name: " + questName.getText().getText();
			output += "\n  Start_Info: " + startInfo.getText().getText();
			output += "\n  Finish_Info: " + finishInfo.getText().getText();
			output += "\n  Rewards: ";
			for(Reward r : getRewards()){
				if(r.getType().equalsIgnoreCase("money")){
					output+="\n    Money: " + r.getAmount();
				}else
				if(r.getType().equalsIgnoreCase("item")){
					output+="\n    Item:";
					output+="\n      Item_ID: " + r.getItem().id;
					output+="\n      Display_Name: " + r.getItem().display;
					output+="\n      Amount: " + r.getItem().amount;
					output+="\n      Durability: " + r.getItem().durability;
				}
			}
			output += "\n  Objectives: ";
			int count = 0;
			for(Objective obj : getObjectives()){
				output += "\n    " + count + ":";
				if(obj.getType().equalsIgnoreCase("gather")){
					output+="\n      Type: Gather";
					output+="\n      Item:";
					output+="\n        Item_ID: " + obj.getItem().id;
					output+="\n        Display_Name: " + obj.getItem().display;
					output+="\n        Amount: " + obj.getItem().amount;
					output+="\n        Durability: " + obj.getItem().durability;
				}else{
					output+="\n      Type: " + obj.getType();
					output+="\n      Display_Name: " + obj.getDisplay();
					output+="\n      Objective_ID: " + obj.getId();
					output+="\n      Amount: " + obj.getAmount();
				}
				count++;
			}
			output += "\n  Extras: ";
			
			questTextArea.getText().setText(output);
		}
	}
	
	
	public void update(){
		if(app){
			appFrame.validate();
			appFrame.repaint();
		}else{
			frame.validate();
			frame.repaint();
		}
	}
	
	
	//keeps objectives and rewards arrays aligned
	public void updateTheArrays(){
//		objectivesPanel.remove(objectivesPanel.getComboBox());
//		for(Objective obj : this.getObjectives())
//			objectivesPanel.getComboBox().add(obj);
//		objectivesPanel.setComboBox(new JComboBox(this.objectives.toArray()));
//		rewardsPanel = new NameBoxWithButton("Rewards:", rewards.toArray(), editArea.getBackground(), "Add Reward", 1,this);
//		objectivesPanel.validate();
//		objectivesPanel.repaint();
//		this.questTextArea.getText().setText(objectives.toString());
		 //subComboBox.setModel( new DefaultComboBoxModel( (String[])o ) );
		 objectivesPanel.getComboBox().setModel( new DefaultComboBoxModel( objectives.toArray() ));
		 rewardsPanel.getComboBox().setModel( new DefaultComboBoxModel( rewards.toArray() ));
		 
		 //repaint our frames just in case
		 update();
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public DrawPanel getEditArea() {
		return editArea;
	}


	public void setEditArea(DrawPanel editArea) {
		this.editArea = editArea;
	}


	public NameTextPanel getQuestName() {
		return questName;
	}


	public void setQuestName(NameTextPanel questName) {
		this.questName = questName;
	}


	public NameTextPanel getStartInfo() {
		return startInfo;
	}


	public void setStartInfo(NameTextPanel startInfo) {
		this.startInfo = startInfo;
	}


	public NameTextPanel getFinishInfo() {
		return finishInfo;
	}


	public void setFinishInfo(NameTextPanel finishInfo) {
		this.finishInfo = finishInfo;
	}

	public NameBoxWithButton getObjectivesPanel() {
		return objectivesPanel;
	}


	public void setObjectivesPanel(NameBoxWithButton objectivesPanel) {
		this.objectivesPanel = objectivesPanel;
	}


	public NameBoxWithButton getRewardsPanel() {
		return rewardsPanel;
	}


	public void setRewardsPanel(NameBoxWithButton rewardsPanel) {
		this.rewardsPanel = rewardsPanel;
	}


	public EditAreaMore getEditAreaMore() {
		return editAreaMore;
	}


	public void setEditAreaMore(EditAreaMore editAreaMore) {
		this.editAreaMore = editAreaMore;
	}


	public JPanel getToolBar() {
		return toolBar;
	}


	public void setToolBar(JPanel toolBar) {
		this.toolBar = toolBar;
	}


	public JButton getClearAllButton() {
		return clearAllButton;
	}


	public void setClearAllButton(JButton clearAllButton) {
		this.clearAllButton = clearAllButton;
	}


	public JButton getCompileButton() {
		return compileButton;
	}


	public void setCompileButton(JButton compileButton) {
		this.compileButton = compileButton;
	}


	public QuestTextArea getQuestTextArea() {
		return questTextArea;
	}


	public void setQuestTextArea(QuestTextArea questTextArea) {
		this.questTextArea = questTextArea;
	}


	public ArrayList<Objective> getObjectives() {
		return objectives;
	}


	public void setObjectives(ArrayList<Objective> objectives) {
		this.objectives = objectives;
	}


	public ArrayList<Reward> getRewards() {
		return rewards;
	}


	public void setRewards(ArrayList<Reward> rewards) {
		this.rewards = rewards;
	}
}
