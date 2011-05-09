package hawox.guiquestmaker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class NameBoxWithButton extends NameComboBoxPanel{
	JButton addOne;
	JButton edit;
	GUI gui;
	public NameBoxWithButton(String labelName, Object[] comboBoxContents, Color backgroundColor, String buttonName, int id, GUI gui) {
		super(labelName, comboBoxContents, backgroundColor);
		this.gui = gui;
		addOne = new JButton(buttonName);
		edit = new JButton("Edit");
		switch(id){
		case 0:
			addOne.addActionListener(new addButtonListenerObjective());
//			edit.addActionListener(new editButtonListenerObjective());
			break;
		case 1:
			addOne.addActionListener(new addButtonListenerReward());
//			edit.addActionListener(new editButtonListenerReward());
			break;
		}
		edit.addActionListener(new editButtonListener());
		this.add(addOne);
		this.add(edit);
		
	}

	public class addButtonListenerObjective implements ActionListener{
		public void actionPerformed(ActionEvent event){
			gui.getObjectives().add(new Objective("Kill","Things Killed","kill_any", "1"));
			gui.updateTheArrays();
		}
	}
	public class addButtonListenerReward implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Item item = new Item("3","1","Dirt","0");
			gui.getRewards().add(new Reward("Item", item));
			gui.updateTheArrays();
		}
	}
	public class editButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if( getComboBox().getSelectedItem() instanceof Objective ){
				Objective obj = (Objective) getComboBox().getSelectedItem();
				gui.getEditAreaMore().setUpObjective(obj,getComboBox().getSelectedIndex());
			}else
			if( getComboBox().getSelectedItem() instanceof Reward ){
				Reward r = (Reward) getComboBox().getSelectedItem();
				gui.getQuestTextArea().getText().setText(r.getType());
				gui.getEditAreaMore().setUpReward(r,getComboBox().getSelectedIndex());
			}
		}
	}
	/*
	public class editButtonListenerObjective implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Objective obj = (Objective) getComboBox().getSelectedItem();
			gui.getEditAreaMore().setUpObjective(obj);
		}
	}
	public class editButtonListenerReward implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Reward r = (Reward) getComboBox().getSelectedItem();
			gui.getEditAreaMore().setUpReward(r);
		}
	}*/
}
