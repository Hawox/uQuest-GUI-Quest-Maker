package hawox.guiquestmaker;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI {
	
	JFrame frame;
	DrawPanel editArea;
		NameTextPanel questName;
		NameTextPanel startInfo;
		NameTextPanel finishInfo;
//		NameComboBoxPanel objectivesPanel;
			Object[] objectives = {  };
//			JButton addObjective;
//		NameComboBoxPanel rewardsPanel;
			Object[] rewards = { };
//			JButton addReward;
		NameBoxWithButton objectivesPanel;
		NameBoxWithButton rewardsPanel;
			
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
		
		startUpGui(false);
	}
	
	
	public void startUpGui(Boolean usingApplet){
		//Make the frame
		frame.setSize(800,600);
		frame.setVisible(true);
		
		editArea = new DrawPanel();
		editArea.setLayout(new BoxLayout(editArea,  BoxLayout.Y_AXIS));
			questName = new NameTextPanel("Quest Name:",editArea.getBackground());
			startInfo = new NameTextPanel("Start Text:",editArea.getBackground());
			finishInfo = new NameTextPanel("Finish Text:",editArea.getBackground());
//			objectivesPanel = new NameComboBoxPanel("Objectives:", objectives, editArea.getBackground());
//			addObjective = new JButton("Add Objective");
//			rewardsPanel = new NameComboBoxPanel("Rewards:", rewards, editArea.getBackground());
//			addReward = new JButton("Add Reward");
			objectivesPanel = new NameBoxWithButton("Objectives:", objectives, editArea.getBackground(), "Add Objective", 0);
			rewardsPanel = new NameBoxWithButton("Rewards:", rewards, editArea.getBackground(), "Add Reward", 1);
			editAreaMore = new EditAreaMore(editArea.getBackground());
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
	
		frame.validate();
		frame.repaint();
	}
	
	public class ClearAllButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
		}
	}
	public class CompileButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String output = "#:";
			output += "\n  Name: " + questName.getText().getText();
			output += "\n  Start_Info: " + startInfo.getText().getText();
			output += "\n  Finish_Info: " + finishInfo.getText().getText();
			output += "\n  Rewards: ";
			output += "\n  Objectives: ";

			questTextArea.getText().setText(output);
		}
	}
}
